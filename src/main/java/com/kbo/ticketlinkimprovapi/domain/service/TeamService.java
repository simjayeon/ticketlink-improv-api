package com.kbo.ticketlinkimprovapi.domain.service;

import com.kbo.ticketlinkimprovapi.domain.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final ITeamRepository teamRepository;
}
