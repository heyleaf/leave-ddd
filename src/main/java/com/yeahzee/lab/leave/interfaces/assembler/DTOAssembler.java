package com.yeahzee.lab.leave.interfaces.assembler;

import com.yeahzee.lab.leave.application.dto.ApplicantDTO;
import com.yeahzee.lab.leave.application.dto.CreateLeaveResponseDTO;

import com.yeahzee.lab.leave.query.dto.GetLeaveResponseDTO;

public class DTOAssembler {
    // TODO 如果DTO不能共用，感觉代码无法写下去了。。。
    private static ApplicantDTO toCommandDTO(com.yeahzee.lab.leave.query.dto.ApplicantDTO applicantQuerDTO) {
        return null;

    }
    public static CreateLeaveResponseDTO toCreateLeaveResponseDTO(GetLeaveResponseDTO getResponseDTO) {
        CreateLeaveResponseDTO resultDTO =  new CreateLeaveResponseDTO();
        return resultDTO;
    }
}
