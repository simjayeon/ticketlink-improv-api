package com.kbo.ticketlinkimprovapi.interfaces.dto;

import com.kbo.ticketlinkimprovapi.domain.entity.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResTeam {
    private Integer Id;
    private String name;

    public static ResTeam of(Team team) {
        return ResTeam.builder()
                .Id(team.getId())
                .name(team.getName())
                .build();
    }
}
