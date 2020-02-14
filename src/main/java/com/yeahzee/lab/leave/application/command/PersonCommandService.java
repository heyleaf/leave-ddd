package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.person.service.PersonDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandService {

    @Autowired
    PersonDomainService personDomainService;

    public void create(Person person) {
        personDomainService.create(person);
    }

    public void update(Person person) {
        personDomainService.update(person);
    }

    public void deleteById(String personId) {
        personDomainService.deleteById(personId);
    }

//    public Person findById(String personId) {
//        return null;
//    }
//
//    public Person findFirstApprover(String applicantId, int leaderMaxLevel) {
//        return personDomainService.findFirstApprover(applicantId, leaderMaxLevel);
//    }

}
