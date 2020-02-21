package com.yeahzee.lab.leave.application.client;

import com.yeahzee.lab.leave.interfaces.controller.Response;
import org.springframework.stereotype.Service;

@Service
public interface AuthFeignClient {

    Response login(String username, String password);
}
