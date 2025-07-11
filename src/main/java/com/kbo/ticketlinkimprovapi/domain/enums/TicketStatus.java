package com.kbo.ticketlinkimprovapi.domain.enums;

import lombok.Getter;

@Getter
public enum TicketStatus {
    PURCHASED("결제 완료"),
    CANCELLED("결제 취소"),
    PARTIALLY_SOLD("일부 판매됨"),
    SOLD("판매 완료");

    private final String status;

    TicketStatus(String status) {
        this.status = status;
    }
}
