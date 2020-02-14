package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

@Data
public class ApprovalInfoDTO {

    String approvalInfoId;
    ApproverDTO approverDTO;
    String msg;
    long time;
}
