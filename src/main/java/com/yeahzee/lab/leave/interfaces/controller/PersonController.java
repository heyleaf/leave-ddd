package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.assembler.PersonAssembler;
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
            personCommandService.create(PersonAssembler.toDO(personDTO));
            return Response.ok();
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
    }

    @PutMapping
    public Response update(PersonDTO personDTO) {
        try {
            personCommandService.update(PersonAssembler.toDO(personDTO));
        } catch (ParseException e) {
            log.error("", e);
            return Response.failed(e.getMessage());
        }
        return Response.ok();
    }

    @DeleteMapping("/{personId}")
    public Response delete(@PathVariable String personId) {
        personCommandService.deleteById(personId);
        return Response.ok();
    }

    @GetMapping("/{personId}")
    public Response get(@PathVariable String personId) {
        PersonDTO personDTO = personQueryService.findById(personId);
        return Response.ok(personDTO);
    }

    @GetMapping("/findFirstApprover")
    public Response findFirstApprover(@RequestParam String applicantId, @RequestParam int leaderMaxLevel) {
        PersonDTO personDTO = personQueryService.findFirstApprover(applicantId, leaderMaxLevel);
        return Response.ok(personDTO);
    }

}
