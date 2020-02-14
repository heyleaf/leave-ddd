package com.yeahzee.lab.leave.interfaces.assembler;


import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Applicant;
import com.yeahzee.lab.leave.interfaces.dto.ApplicantDTO;

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
