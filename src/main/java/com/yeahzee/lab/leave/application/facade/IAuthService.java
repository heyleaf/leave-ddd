package com.yeahzee.lab.leave.application.facade;

import com.yeahzee.lab.leave.application.dto.LoginRequestDTO;
import com.yeahzee.lab.leave.application.dto.LoginResponseDTO;

public interface IAuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
