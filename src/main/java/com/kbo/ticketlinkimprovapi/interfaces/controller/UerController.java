package com.kbo.ticketlinkimprovapi.interfaces.controller;

import com.kbo.ticketlinkimprovapi.domain.service.UserService;
import com.kbo.ticketlinkimprovapi.interfaces.dto.ReqSignup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UerController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody ReqSignup req) {
        userService.signUp(req);
        return ResponseEntity.ok("회원가입 성공");
    }

}
