package com.kbo.ticketlinkimprovapi.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbo.ticketlinkimprovapi.support.util.CaptchaImageUtil;
import com.kbo.ticketlinkimprovapi.support.util.CaptchaKeyUtil;
import com.kbo.ticketlinkimprovapi.support.util.CaptchaUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final String clientId = "zluEY9VajBWnneOXHe2H";
    private final String clientSecret = "p4wCJm8LSC";

    public String generateKey() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=0";
        String response = CaptchaKeyUtil.get(apiURL, requestHeaders);

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.get("key").asText(); // JSON에서 key만 추출
        } catch (Exception e) {
            throw new RuntimeException("캡차 키 파싱 실패", e);
        }
    }

    public void generateImage() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String key = generateKey();
        String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
        
        CaptchaImageUtil.get(apiURL, requestHeaders);
    }

    public String inputCheck(String value) {
        String key = generateKey(); // 캡차 키 발급시 받은 키값
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=1" + "&key=" + key + "&value=" + value;
        return CaptchaUtil.get(apiURL, requestHeaders);
    }

}
