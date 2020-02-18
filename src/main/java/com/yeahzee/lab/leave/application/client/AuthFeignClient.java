package com.yeahzee.lab.leave.application.client;

import com.yeahzee.lab.common.api.Response;
import org.springframework.stereotype.Service;

@Service
public interface AuthFeignClient {

    Response login(String username, String password);
}
