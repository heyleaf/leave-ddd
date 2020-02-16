package com.yeahzee.lab.leave.query;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.leave.infrastructure.repository.person.mapper.PersonDAO;
import com.yeahzee.lab.leave.infrastructure.repository.person.po.PersonPO;
import com.yeahzee.lab.leave.query.assembler.PersonAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonQueryService {
    @Autowired
    PersonDAO personDao;
    public PersonDTO findById(String personId) {
        return null;
    }

    public PersonDTO findFirstApprover(String applicantId, int leaderMaxLevel) {
        PersonPO leaderPO = personDao.findLeaderByPersonId(applicantId);
        if (leaderPO.getRoleLevel() > leaderMaxLevel) {
            return null;
        } else {
            return PersonAssembler.toDTO(leaderPO);
        }
    }
}
