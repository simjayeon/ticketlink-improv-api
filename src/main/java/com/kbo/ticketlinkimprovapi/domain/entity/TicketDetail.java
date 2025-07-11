package com.kbo.ticketlinkimprovapi.domain.entity;

import jakarta.persistence.*;

@Entity
public class TicketDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    private Integer price;

    private Integer charge;
}
