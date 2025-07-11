package com.kbo.ticketlinkimprovapi.infra.repository;

import com.kbo.ticketlinkimprovapi.domain.entity.Game;
import com.kbo.ticketlinkimprovapi.domain.repository.IGameRepository;
import com.kbo.ticketlinkimprovapi.infra.jpa.GameJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GameRepositoryImpl implements IGameRepository {

    private final GameJpaRepository gameJpaRepository;

    @Override
    public List<Game> findByTeamIdAndGameAtAfter(Integer teamId, ZonedDateTime now) {
        return gameJpaRepository.findByHomeTeamIdOrAwayTeamIdAndGameAtAfter(teamId, teamId, now);
    }

    @Override
    public List<Game> findByGameAtAfter(ZonedDateTime now) {
        return gameJpaRepository.findByGameAtAfter(now);
    }
}
