package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.leave.application.dto.ApproverDTO;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;

public class ApproverAssembler {

    public static ApproverDTO toDTO(Approver approver){
        ApproverDTO dto = new ApproverDTO();
        dto.setPersonId(approver.getPersonId());
        dto.setPersonName(approver.getPersonName());
        return dto;
    }

    public static Approver toDO(ApproverDTO dto){
        Approver approver = new Approver();
        approver.setPersonId(dto.getPersonId());
        approver.setPersonName(dto.getPersonName());
        return approver;
    }

}
