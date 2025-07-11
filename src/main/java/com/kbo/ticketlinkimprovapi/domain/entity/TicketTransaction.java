package com.kbo.ticketlinkimprovapi.domain.entity;

import com.kbo.ticketlinkimprovapi.domain.enums.TicketTransactionStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

public class TicketTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ticketDetailId;

    private Integer buyerId;

    private Integer sellerId;

    private ZonedDateTime transactionAt;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private TicketTransactionStatus status;

}
