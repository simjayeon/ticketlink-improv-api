package com.kbo.ticketlinkimprovapi.domain.service;

import com.kbo.ticketlinkimprovapi.domain.entity.Game;
import com.kbo.ticketlinkimprovapi.domain.repository.IGameRepository;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ResGame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final IGameRepository gameRepository;

    public List<ResGame> getGameList(Integer teamId) {
        List<Game> gameList;
        ZonedDateTime now = ZonedDateTime.now();
        if (teamId != null) {
            gameList = gameRepository.findByTeamIdAndGameAtAfter(teamId, now);
        } else {
            gameList = gameRepository.findByGameAtAfter(now);
        }
        return gameList.stream()
                .map(ResGame::of)
                .toList();
    }
}
