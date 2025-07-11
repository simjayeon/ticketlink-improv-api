package com.kbo.ticketlinkimprovapi.interfaces.controller;


import com.kbo.ticketlinkimprovapi.domain.service.GameService;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ResGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game")
@CrossOrigin(origins = "*")
public class GameController {
    private final GameService gameService;

    @GetMapping("")
    @Operation(
            operationId = "getGameList",
            summary = "경기 목록 조회",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "정상 조회됨",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResGame.class))))
            }
    )
    public List<ResGame> getGameList(@RequestParam(name = "teamId", required = false) Integer teamId) {
        return gameService.getGameList(teamId);
    }
}
