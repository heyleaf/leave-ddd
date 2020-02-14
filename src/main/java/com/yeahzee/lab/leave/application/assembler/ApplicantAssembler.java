package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.api.dto.ApplicantDTO;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Applicant;

public class ApplicantAssembler {
    public static Applicant toDO(ApplicantDTO dto){
        Applicant applicant = new Applicant();
        applicant.setPersonId(dto.getPersonId());
        applicant.setPersonName(dto.getPersonName());
        return applicant;
    }

}
