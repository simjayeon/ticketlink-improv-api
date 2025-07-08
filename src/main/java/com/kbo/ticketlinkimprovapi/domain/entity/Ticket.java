package com.kbo.ticketlinkimprovapi.domain.entity;

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

    private ZonedDateTime purchaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String status; // 예: "예매완료", "취소됨", "환불완료" 등

}
