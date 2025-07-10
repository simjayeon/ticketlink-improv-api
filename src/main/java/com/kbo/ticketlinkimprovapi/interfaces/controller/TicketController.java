package com.kbo.ticketlinkimprovapi.interfaces.controller;

import com.kbo.ticketlinkimprovapi.domain.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
@CrossOrigin(origins = "*")
public class TicketController {
    private final TicketService ticketService;

//    @Operation(
//            operationId = "getStadiumList",
//            summary = "구장 리스트 조회",
//            responses = {
//                    @ApiResponse(responseCode = "200",
//                            description = "정상 조회됨",
//                            content = @Content(
//                                    mediaType = "application/json",
//                                    array = @ArraySchema(schema = @Schema(implementation = ResStadiumList.class))))
//            }
//    )
}
