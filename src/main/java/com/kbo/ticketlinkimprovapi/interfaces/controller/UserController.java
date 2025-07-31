package com.kbo.ticketlinkimprovapi.interfaces.controller;

import com.kbo.ticketlinkimprovapi.domain.service.UserService;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqLogin;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqSignup;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqTeam;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ResUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody ReqSignup req) {
        userService.signUp(req);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ReqLogin req) {

        return userService.login(req);
    }

    @GetMapping("/{userId}")
    public ResUserInfo getUserInfo(@PathVariable("userId") Integer userId) {
        return userService.getUserInfo(userId);
    }

    @PostMapping("")
    @Operation(
            operationId = "setMyTeam",
            summary = "내팀 설정하기",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "정상 조회됨",
                            content = @Content(
                                    mediaType = "application/json"))
            }
    )
    public void setMyTeam(@RequestBody ReqTeam req) {
        userService.setMyTeam(req);
    }

}
