package com.kbo.ticketlinkimprovapi.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수 입력입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    private String password;

    private Date birthday;

    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
    private List<Ticket> buyerTickets;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Ticket> sellerTickets;
}
