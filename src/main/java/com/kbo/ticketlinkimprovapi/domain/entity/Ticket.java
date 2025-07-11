package com.kbo.ticketlinkimprovapi.domain.entity;

import com.kbo.ticketlinkimprovapi.domain.enums.TicketStatus;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    private Integer totalPrice;

    private Integer numberOfTickets;

    private ZonedDateTime purchaseAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchaser_id")
    private User purchaser;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

}
