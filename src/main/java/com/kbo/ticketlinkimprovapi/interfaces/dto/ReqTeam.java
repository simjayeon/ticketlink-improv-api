package com.kbo.ticketlinkimprovapi.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReqTeam {
    private Integer userId;
    private Integer teamId;
}
