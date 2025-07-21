package com.kbo.ticketlinkimprovapi.interfaces.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ReqSignup {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Date birthDay;
}
