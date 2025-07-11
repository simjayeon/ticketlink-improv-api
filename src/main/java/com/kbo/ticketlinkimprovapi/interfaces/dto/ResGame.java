package com.kbo.ticketlinkimprovapi.interfaces.dto;

import com.kbo.ticketlinkimprovapi.domain.entity.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class ResGame {
    private Integer Id;
    private ZonedDateTime gameAt;
    private String homeTeam;
    private String awayTeam;
    private String location;

    public static ResGame of(Game game) {
        return ResGame.builder()
                .Id(game.getId())
                .gameAt(game.getGameAt())
                .homeTeam(game.getHomeTeam().getName())
                .awayTeam(game.getAwayTeam().getName())
                .location(game.getHomeTeam().getLocation())
                .build();
    }
}
