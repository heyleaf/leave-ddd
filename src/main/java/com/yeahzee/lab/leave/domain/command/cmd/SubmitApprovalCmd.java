package com.yeahzee.lab.leave.domain.command.cmd;

import lombok.Data;

@Data
public class SubmitApprovalCmd extends Command {
    String leaveId;
    String approvalInfoId;
    String msg;
    Long time;
    String approverId;
    String approverName;
    String approvalType;

    public SubmitApprovalCmd(String leaveId, String approvalInfoId, String msg,
                             Long time, String approverId, String approverName,
                             String approvalType) {
        this.leaveId = leaveId;
        this.approvalInfoId = approvalInfoId;
        this.msg = msg;
        this.time = time;
        this.approverId = approverId;
        this.approverName = approverName;
        this.approvalType = approvalType;
    }
}
