package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.assembler.PersonAssembler;
import com.yeahzee.lab.leave.application.command.LoginCommandService;
import com.yeahzee.lab.leave.application.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthApi {

    @Autowired
    LoginCommandService loginCommandService;

    @PostMapping("/login")
    public Response login(PersonDTO personDTO){
        try {
            return loginCommandService.login(PersonAssembler.toDO(personDTO));
        } catch (ParseException e) {
            e.printStackTrace();
            return new Response();
        }
    }
}
