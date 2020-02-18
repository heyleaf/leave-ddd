package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

@Data
public class SubmitApprovalRequestDTO {
    String leaveId;
    String approvalInfoId;
    ApproverDTO approverDTO;
    String msg;
    Long time;
    String approvalType;

    @Data
    public static class ApproverDTO {
        String personId;
        String personName;
    }
}
