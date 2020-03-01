package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.leave.application.dto.LoginRequestDTO;
import com.yeahzee.lab.leave.application.facade.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    IAuthService authService;

    @PostMapping("/login")
    public Response login(LoginRequestDTO loginRequestDTO){
        return Response.ok(authService.login(loginRequestDTO));
    }
}
