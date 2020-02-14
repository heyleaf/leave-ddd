package com.yeahzee.lab.leave.domain.person.service;

import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.person.repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class PersonDomainService {

    @Autowired
    IPersonRepository personRepository;

    public void create(Person person) {
        // TODO 这里不应该返回Person来判断是否存在，直接定义一个isPersonExist接口
        Person p = personRepository.findById(person.getPersonId());
        if (null == p) {
            throw new RuntimeException("Person already exists");
        }
        person.create();
        personRepository.insert(person);
    }

    public void update(Person person) {
        person.setLastModifyTime(new Date());
        personRepository.update(person);
    }

    public void deleteById(String personId) {
        Person person = personRepository.findById(personId);
        person.disable();
        personRepository.update(person);
    }

    public Person findById(String userId) {
        return personRepository.findById(userId);
    }

    /**
     * find leader with applicant, if leader level bigger then leaderMaxLevel return null, else return Approver from leader;
     *
     * @param applicantId
     * @param leaderMaxLevel
     * @return
     */
    public Person findFirstApprover(String applicantId, int leaderMaxLevel) {
        Person leader = personRepository.findLeaderByPersonId(applicantId);
        if (leader.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return leader;
        }
    }

    /**
     * find leader with current approver, if leader level bigger then leaderMaxLevel return null, else return Approver from leader;
     *
     * @param currentApproverId
     * @param leaderMaxLevel
     * @return
     */
    public Person findNextApprover(String currentApproverId, int leaderMaxLevel) {
        Person leader = personRepository.findLeaderByPersonId(currentApproverId);
        if (leader.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return leader;
        }
    }

}