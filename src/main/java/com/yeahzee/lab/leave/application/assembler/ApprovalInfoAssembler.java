package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.leave.application.dto.ApprovalInfoDTO;
import com.yeahzee.lab.leave.domain.leave.entity.ApprovalInfo;

public class ApprovalInfoAssembler {

    public static ApprovalInfo toDO(ApprovalInfoDTO dto){
        ApprovalInfo approvalInfo = new ApprovalInfo();
        approvalInfo.setApprovalInfoId(dto.getApprovalInfoId());
        approvalInfo.setMsg(dto.getMsg());
        approvalInfo.setApprover(ApproverAssembler.toDO(dto.getApproverDTO()));
        return approvalInfo;
    }

    public static ApprovalInfoDTO toDTO(ApprovalInfo approvalInfo){
        ApprovalInfoDTO dto = new ApprovalInfoDTO();
        dto.setApprovalInfoId(approvalInfo.getApprovalInfoId());
        dto.setMsg(approvalInfo.getMsg());
        dto.setTime(approvalInfo.getTime());
        return dto;
    }
}
