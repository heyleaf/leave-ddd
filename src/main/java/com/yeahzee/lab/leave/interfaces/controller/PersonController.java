package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.FindApproverDTO;
import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.command.PersonCommandService;
import com.yeahzee.lab.leave.query.PersonQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    @Autowired
    PersonCommandService personCommandService;
    @Autowired
    PersonQueryService personQueryService;

    @PostMapping
    public Response create(PersonDTO personDTO) {
        try {
            personCommandService.create(personDTO);
            return Response.ok();
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
    }

    @PutMapping
    public Response update(PersonDTO personDTO) {
        try {
            personCommandService.update(personDTO);
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
        return Response.ok();
    }

    @DeleteMapping("/deletePersonById")
    public Response delete(@RequestBody PersonDTO personDTO) {
        personCommandService.deleteById(personDTO.getPersonId());
        return Response.ok();
    }

    @GetMapping("/query/getPersonById")
    public Response get(@PathVariable PersonDTO dto) {
        PersonDTO personDTO = personQueryService.findById(dto.getPersonId());
        return Response.ok(personDTO);
    }

    @GetMapping("/query/findFirstApprover")
    public Response findFirstApprover(@RequestBody FindApproverDTO findApproverDTO) {
        PersonDTO personDTO = personQueryService.findFirstApprover(findApproverDTO.getApplicantId(),
                findApproverDTO.getLeaderMaxLevel());
        return Response.ok(personDTO);
    }
}
