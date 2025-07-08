package com.kbo.ticketlinkimprovapi.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String stadium;

    private String location;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<User> users;
}
