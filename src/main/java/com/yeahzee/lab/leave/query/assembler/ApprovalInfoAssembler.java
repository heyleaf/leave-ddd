package com.yeahzee.lab.leave.query.assembler;


import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.query.dto.ApprovalInfoDTO;

public class ApprovalInfoAssembler {

    public static ApprovalInfoDTO toDTO(ApprovalInfoPO approvalInfoPO){
        ApprovalInfoDTO dto = new ApprovalInfoDTO();
        dto.setApprovalInfoId(approvalInfoPO.getApprovalInfoId());
        dto.setMsg(approvalInfoPO.getMsg());
        dto.setTime(approvalInfoPO.getTime());
        return dto;
    }
}
