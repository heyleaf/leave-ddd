package com.yeahzee.lab.leave.application.client;

import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import org.springframework.stereotype.Service;

@Service
public interface AuthFeignClient {

    Response login(Person person);
}
