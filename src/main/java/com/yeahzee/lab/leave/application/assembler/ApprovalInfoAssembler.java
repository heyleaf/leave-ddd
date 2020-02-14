package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.api.dto.ApprovalInfoDTO;
import com.yeahzee.lab.leave.domain.leave.entity.ApprovalInfo;

public class ApprovalInfoAssembler {

    public static ApprovalInfo toDO(ApprovalInfoDTO dto){
        ApprovalInfo approvalInfo = new ApprovalInfo();
        approvalInfo.setApprovalInfoId(dto.getApprovalInfoId());
        approvalInfo.setMsg(dto.getMsg());
        approvalInfo.setApprover(ApproverAssembler.toDO(dto.getApproverDTO()));
        return approvalInfo;
    }
}
