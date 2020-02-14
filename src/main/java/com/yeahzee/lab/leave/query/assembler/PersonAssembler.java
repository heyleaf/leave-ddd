package com.yeahzee.lab.leave.query.assembler;

import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.util.DateUtil;
import com.yeahzee.lab.leave.infrastructure.repository.person.po.PersonPO;

public class PersonAssembler {
    public static PersonDTO toDTO(PersonPO personPO){
        PersonDTO dto = new PersonDTO();
        dto.setPersonId(personPO.getPersonId());
        dto.setPersonType(personPO.getPersonType().toString());
        dto.setPersonName(personPO.getPersonName());
        dto.setStatus(personPO.getStatus().toString());
        dto.setCreateTime(DateUtil.formatDateTime(personPO.getCreateTime()));
        dto.setLastModifyTime(DateUtil.formatDateTime(personPO.getLastModifyTime()));
        return dto;
    }
}
