package com.yeahzee.lab.leave.interfaces.facade;

import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.command.PersonCommandService;
import com.yeahzee.lab.leave.interfaces.assembler.PersonAssembler;
import com.yeahzee.lab.leave.interfaces.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonApi {

    @Autowired
    PersonCommandService personCommandService;

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

//    @GetMapping("/{personId}")
//    public Response get(@PathVariable String personId) {
//        Person person = personApplicationService.findById(personId);
//        return Response.ok(PersonAssembler.toDTO(person));
//    }
//
//    @GetMapping("/findFirstApprover")
//    public Response findFirstApprover(@RequestParam String applicantId, @RequestParam int leaderMaxLevel) {
//        Person person = personApplicationService.findFirstApprover(applicantId, leaderMaxLevel);
//        return Response.ok(PersonAssembler.toDTO(person));
//    }

}
