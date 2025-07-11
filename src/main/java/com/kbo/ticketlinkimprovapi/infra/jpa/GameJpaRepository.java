package com.kbo.ticketlinkimprovapi.infra.jpa;

import com.kbo.ticketlinkimprovapi.domain.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface GameJpaRepository extends JpaRepository<Game, Integer> {

    List<Game> findByGameAtAfter(ZonedDateTime now);

    List<Game> findByHomeTeamIdOrAwayTeamIdAndGameAtAfter(Integer homeTeamId, Integer awayTeamId, ZonedDateTime now);
}
