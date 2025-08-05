package com.kbo.ticketlinkimprovapi.interfaces.controller;

import com.kbo.ticketlinkimprovapi.domain.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/security")
@CrossOrigin(origins = "*")
public class SecurityController {

    private final SecurityService securityService;

    @GetMapping("/image")
    public void getCaptchaImage() {
//        return securityService.generateImage();
    }

}
