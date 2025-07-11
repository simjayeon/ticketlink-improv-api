package com.kbo.ticketlinkimprovapi.domain.enums;

import lombok.Getter;

@Getter
public enum TicketTransactionStatus {
    COMPLETED("거래 완료"),
    CANCELLED("거래 취소");

    private final String status;

    TicketTransactionStatus(String status) {
        this.status = status;
    }
}
