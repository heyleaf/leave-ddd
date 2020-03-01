package com.yeahzee.lab.leave.application.service;

import com.yeahzee.lab.leave.application.client.AuthFeignClient;
import com.yeahzee.lab.leave.application.dto.LoginRequestDTO;
import com.yeahzee.lab.leave.application.dto.LoginResponseDTO;
import com.yeahzee.lab.leave.application.facade.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Autowired
    AuthFeignClient authFeignClient;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authFeignClient.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setIsSuccess(true);
        return responseDTO;
    }
}
