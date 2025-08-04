package com.kbo.ticketlinkimprovapi.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResLogin {
    @Getter
    private String accessToken;
    private String refreshToken;

    public ResLogin(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
