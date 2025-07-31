package com.kbo.ticketlinkimprovapi.interfaces.controller;


import com.kbo.ticketlinkimprovapi.domain.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/team")
@CrossOrigin(origins = "*")
public class TeamController {
    private final TeamService teamService;
    
}
