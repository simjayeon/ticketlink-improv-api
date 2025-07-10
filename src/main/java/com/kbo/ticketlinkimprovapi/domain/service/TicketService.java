package com.kbo.ticketlinkimprovapi.domain.service;

import com.kbo.ticketlinkimprovapi.domain.repository.ITicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ITicketRepository ticketRepository;
}
