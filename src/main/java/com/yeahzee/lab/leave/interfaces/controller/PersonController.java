package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.leave.application.dto.CreatePersonRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetPersonRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetPersonResponseDTO;
import com.yeahzee.lab.leave.application.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    @Autowired
    PersonService personService;


    @PostMapping(value = "/query/createPerson")
    public Response createPerson(@RequestBody  CreatePersonRequestDTO createPersonRequestDTO) {
        try {
            personService.create(createPersonRequestDTO);
            return Response.ok();
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
    }

    @PostMapping(value = "/query/getPersonById")
    public Response getPerson(@PathVariable GetPersonRequestDTO getPersonRequestDTO) {
        GetPersonResponseDTO responseDTO = personService.getPerson(getPersonRequestDTO);
        return Response.ok(responseDTO);
    }
}
