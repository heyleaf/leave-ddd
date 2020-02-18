package com.yeahzee.lab.leave.domain.command;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.leave.application.assembler.PersonAssembler;
import com.yeahzee.lab.leave.domain.person.IPersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class PersonCommandService {

    @Autowired
    IPersonDomainService personDomainService;

    public void create(PersonDTO personDTO) throws ParseException {
        personDomainService.create(PersonAssembler.toDO(personDTO));
    }

    public void update(PersonDTO personDTO) throws ParseException {
        personDomainService.update(PersonAssembler.toDO(personDTO));
    }

    public void deleteById(String personId) {
        personDomainService.deleteById(personId);
    }

}
