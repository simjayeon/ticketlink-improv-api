package com.kbo.ticketlinkimprovapi.infra.jpa;

import com.kbo.ticketlinkimprovapi.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketJpaRepository extends JpaRepository<Ticket, Integer> {
}
