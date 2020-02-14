package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.leave.domain.person.IPersonDomainService;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandService {

    @Autowired
    IPersonDomainService personDomainService;

    public void create(Person person) {
        personDomainService.create(person);
    }

    public void update(Person person) {
        personDomainService.update(person);
    }

    public void deleteById(String personId) {
        personDomainService.deleteById(personId);
    }

}
