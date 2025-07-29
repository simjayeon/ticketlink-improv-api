package com.kbo.ticketlinkimprovapi.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReqLogin {
    private String email;
    private String password;
}
