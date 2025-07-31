package com.kbo.ticketlinkimprovapi.infra.repository;

import com.kbo.ticketlinkimprovapi.domain.entity.Team;
import com.kbo.ticketlinkimprovapi.domain.repository.ITeamRepository;
import com.kbo.ticketlinkimprovapi.infra.jpa.TeamJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements ITeamRepository {

    private final TeamJpaRepository teamJpaRepository;

    @Override
    public Team findTeamById(Integer id) {
        return teamJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + id));
    }
}
