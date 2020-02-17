package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

@Data
public class SubmitApprovalRequestDTO {
    String leaveId;
    String applicantType;
    String leaveType;
    Long leaveDuration;
    String approvalInfoId;
    ApproverDTO approverDTO;
    String msg;
    Long time;
}
