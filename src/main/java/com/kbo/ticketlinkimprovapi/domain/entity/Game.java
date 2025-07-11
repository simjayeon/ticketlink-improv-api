package com.kbo.ticketlinkimprovapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
public class Game extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ZonedDateTime gameAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @OneToMany(mappedBy = "game")
    private List<Ticket> tickets;
}
