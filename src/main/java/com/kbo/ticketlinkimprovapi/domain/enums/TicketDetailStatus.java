package com.kbo.ticketlinkimprovapi.domain.enums;

import lombok.Getter;

@Getter
public enum TicketDetailStatus {
    AVAILABLE("거래 가능"),
    SOLD("거래 완료"),
    CANCELLED("거래 취소");

    private final String status;

    TicketDetailStatus(String status) {
        this.status = status;
    }

}
