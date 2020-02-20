package com.yeahzee.lab.leave.domain.person;

import com.yeahzee.lab.leave.domain.person.entity.Person;
import org.springframework.stereotype.Service;

@Service
public interface IPersonDomainService {
    void create(Person person);
    void update(Person person);
    void deleteById(String personId);
    Person findById(String userId);
    Person findFirstApprover(String applicantId, int leaderMaxLevel);
    Person findNextApprover(String currentApproverId, int leaderMaxLevel);
}
