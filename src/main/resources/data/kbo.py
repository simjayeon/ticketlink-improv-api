from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.select import Select
import pandas as pd
import pymysql
import re
from datetime import datetime, date


class GameCalCrawler:

    def __init__(self):
        self.url = "https://www.koreabaseball.com/Schedule/Schedule.aspx"
        self.conn = pymysql.connect(
            host='127.0.0.1',
            port=3306,
            user='root',
            password='qwer1234',
            database='ticket_link',
            charset='utf8mb4'
        )

    def get_team_id(self, cursor, team_name):
        cursor.execute("SELECT id FROM team WHERE name = %s", (team_name,))
        result = cursor.fetchone()
        if not result:
            raise ValueError(f"팀 '{team_name}'을 찾을 수 없습니다.")
        return result[0]

    def parse_date_time(self, date_str, time_str):
        """
        '07.01(화)' + '18:30' -> '2025-07-01 18:30'
        """
        month, day = date_str.split('(')[0].split('.')
        return f"2025-{month.zfill(2)}-{day.zfill(2)} {time_str}"

    def extract_team_names(self, match_str):
        """
        점수 포함/미포함 경기명 처리
        'LG3vs2롯데' -> ('LG', '롯데')
        'LGvs롯데' -> ('LG', '롯데')
        """
        # 점수 포함형
        m = re.match(r"([가-힣A-Z]+)\d*vs\d*([가-힣A-Z]+)", match_str)
        if m:
            return m.group(1), m.group(2)

        # 점수 미포함형
        m = re.match(r"([가-힣A-Z]+)vs([가-힣A-Z]+)", match_str)
        if m:
            return m.group(1), m.group(2)

        raise ValueError(f"경기 형식 오류: {match_str}")

    def crawling(self, month):
        options = Options()
        options.add_argument("--headless")
        options.add_argument("--no-sandbox")

        driver = webdriver.Chrome(options=options)
        driver.get(self.url)

        # 월 선택
        select = Select(driver.find_element(By.ID, "ddlMonth"))
        select.select_by_value(month)

        # 테이블 로드
        table = driver.find_element(By.CLASS_NAME, "tbl-type06")
        schedule = table.text
        driver.quit()

        lines = schedule.strip().split('\n')
        if lines[1] == '데이터가 없습니다.':
            return '0'

        header = lines[0].split()
        rows = []

        # 데이터 라인 처리
        for line in lines[1:]:
            if line.split()[0].endswith(')'):
                date_text = line.split(' ')[0]
                rows.append(line.split(' '))
            else:
                temp = line.split()
                temp.insert(0, date_text)
                rows.append(temp)

        rows.insert(0, header)
        df = pd.DataFrame(rows)
        df = df.rename(columns=df.iloc[0])
        df = df.drop(df.index[0])
        df = df.fillna('-')

        # 하이라이트/TV 정리
        if df['하이라이트'][1] != '하이라이트':
            df['라디오'] = df['TV']
            df['TV'] = df['하이라이트']
        df = df.drop(['게임센터', '하이라이트', '구장'], axis=1)

        today = date.today()

        # DB Insert
        with self.conn.cursor() as cursor:
            for _, row in df.iterrows():
                try:
                    # 날짜/시간 파싱
                    game_at_str = self.parse_date_time(row['날짜'], row['시간'])
                    game_at = datetime.strptime(game_at_str, "%Y-%m-%d %H:%M")

                    # 오늘 이전 경기 skip
                    if game_at.date() < today:
                        continue

                    # 팀명 파싱
                    home_name, away_name = self.extract_team_names(row['경기'])

                    # 팀 ID
                    home_id = self.get_team_id(cursor, home_name)
                    away_id = self.get_team_id(cursor, away_name)

                    # INSERT
                    cursor.execute("""
                        INSERT INTO game (game_at, home_team_id, away_team_id)
                        VALUES (%s, %s, %s)
                    """, (game_at, home_id, away_id))

                except Exception as e:
                    print(f"❌ 오류 발생: {e} | 데이터: {row.to_dict()}")

        self.conn.commit()
        return '1'

    def close(self):
        self.conn.close()


if __name__ == "__main__":
    crawler = GameCalCrawler()
    crawler.crawling('07')
    crawler.close()