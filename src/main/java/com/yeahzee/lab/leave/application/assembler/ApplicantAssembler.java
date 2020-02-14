package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.leave.application.dto.ApplicantDTO;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Applicant;

public class ApplicantAssembler {

    public static ApplicantDTO toDTO(Applicant applicant){
        ApplicantDTO dto = new ApplicantDTO();
        dto.setPersonId(applicant.getPersonId());
        dto.setPersonName(applicant.getPersonName());
        return dto;
    }

    public static Applicant toDO(ApplicantDTO dto){
        Applicant applicant = new Applicant();
        applicant.setPersonId(dto.getPersonId());
        applicant.setPersonName(dto.getPersonName());
        return applicant;
    }

}
