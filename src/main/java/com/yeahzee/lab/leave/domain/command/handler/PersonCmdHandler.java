package com.yeahzee.lab.leave.domain.command.handler;

import com.yeahzee.lab.leave.domain.command.facade.IPersonCmdHandler;
import com.yeahzee.lab.util.DateUtil;
import com.yeahzee.lab.leave.domain.command.cmd.CreatePersonCmd;
import com.yeahzee.lab.leave.domain.person.IPersonDomainService;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class PersonCmdHandler implements IPersonCmdHandler {

    @Autowired
    IPersonDomainService personDomainService;

    public void handle(CreatePersonCmd cmd) throws ParseException {
        Person person = new Person();
        person.setCreateTime(DateUtil.parseDateTime(cmd.getCreateTime()));
        person.setLastModifyTime(DateUtil.parseDateTime(cmd.getLastModifyTime()));
        person.setPersonName(cmd.getPersonName());
        person.setPersonType(PersonType.EXTERNAL);
        person.setRoleLevel(Integer.valueOf(cmd.getRoleId()));
        person.setPersonId(cmd.getPersonId());
        personDomainService.create(person);
    }
}
