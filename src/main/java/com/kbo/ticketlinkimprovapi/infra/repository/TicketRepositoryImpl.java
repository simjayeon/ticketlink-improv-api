package com.kbo.ticketlinkimprovapi.infra.repository;

import com.kbo.ticketlinkimprovapi.domain.repository.ITicketRepository;
import com.kbo.ticketlinkimprovapi.infra.jpa.TicketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements ITicketRepository {

    private final TicketJpaRepository ticketJpaRepository;
}
