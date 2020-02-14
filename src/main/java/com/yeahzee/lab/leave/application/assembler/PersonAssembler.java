package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.util.DateUtil;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonStatus;
import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonType;

import java.text.ParseException;

public class PersonAssembler {

    public static Person toDO(PersonDTO dto) throws ParseException {
        Person person = new Person();
        person.setPersonId(dto.getPersonId());
        person.setPersonType(PersonType.valueOf(dto.getPersonType()));
        person.setPersonName(dto.getPersonName());
        person.setStatus(PersonStatus.valueOf(dto.getStatus()));
        person.setCreateTime(DateUtil.parseDateTime(dto.getCreateTime()));
        person.setLastModifyTime(DateUtil.parseDateTime(dto.getLastModifyTime()));
        return person;
    }
}
