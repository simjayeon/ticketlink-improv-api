package com.kbo.ticketlinkimprovapi.interfaces.dto;

import com.kbo.ticketlinkimprovapi.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ResUserInfo {
    private String email;
    private String name;
    private String phoneNumber;
    private Date birthDay;
    private String teamName;

    public static ResUserInfo of(User user) {
        return ResUserInfo.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .birthDay(user.getBirthday())
                .teamName(user.getTeam() != null ? user.getTeam().getName() : null)
                .build();
    }
}
