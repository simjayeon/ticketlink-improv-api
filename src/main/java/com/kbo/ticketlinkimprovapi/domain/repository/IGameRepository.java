package com.kbo.ticketlinkimprovapi.domain.repository;

import com.kbo.ticketlinkimprovapi.domain.entity.Game;

import java.time.ZonedDateTime;
import java.util.List;

public interface IGameRepository {
    List<Game> findByTeamIdAndGameAtAfter(Integer teamId, ZonedDateTime now);

    List<Game> findByGameAtAfter(ZonedDateTime now);
}
