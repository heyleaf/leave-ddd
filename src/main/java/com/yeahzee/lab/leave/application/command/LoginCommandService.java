package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.client.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginCommandService {

    @Autowired
    AuthFeignClient authService;

    public Response login(PersonDTO personDTO){
        //调用鉴权微服务
        return authService.login(personDTO);
    }
}