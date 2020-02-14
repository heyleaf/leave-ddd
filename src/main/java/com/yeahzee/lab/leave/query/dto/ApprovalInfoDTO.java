package com.yeahzee.lab.leave.query.dto;

import com.yeahzee.lab.leave.application.dto.ApproverDTO;
import lombok.Data;

@Data
public class ApprovalInfoDTO {

    String approvalInfoId;
    ApproverDTO approverDTO;
    String msg;
    long time;
}
