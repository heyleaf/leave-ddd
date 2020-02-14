package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.api.dto.ApproverDTO;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;

public class ApproverAssembler {
    public static Approver toDO(ApproverDTO dto){
        Approver approver = new Approver();
        approver.setPersonId(dto.getPersonId());
        approver.setPersonName(dto.getPersonName());
        return approver;
    }

}
