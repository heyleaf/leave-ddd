package com.yeahzee.lab.leave.application.client;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.api.Response;
import org.springframework.stereotype.Service;

@Service
public interface AuthFeignClient {

    Response login(PersonDTO personDTO);
}
