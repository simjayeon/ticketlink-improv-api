package com.kbo.ticketlinkimprovapi.domain.repository;

import com.kbo.ticketlinkimprovapi.domain.entity.Team;

public interface ITeamRepository {

    Team findTeamById(Integer id);

}
