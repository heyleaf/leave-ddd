package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.command.LoginCommandService;
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
    LoginCommandService loginCommandService;

    @PostMapping("/login")
    public Response login(PersonDTO personDTO){
        return loginCommandService.login(personDTO);
    }
}
