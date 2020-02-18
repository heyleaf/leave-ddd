package com.yeahzee.lab.leave.domain.command.cmd;

import lombok.Data;

@Data
public class CreateLeaveCmd {

    String leaveId;
    String leaveType;
    String leaveStartTime;
    String leaveEndTime;
    Long leaveDuration;

    String applicantId;
    String applicantName;
    String applicantType;

    public CreateLeaveCmd(String leaveId, String leaveType, String startTime, String endTime,
                          Long duration, String applicantId, String applicantName,
                          String applicantType) {
        this.leaveId = leaveId;
        this.leaveType = leaveType;
        this.leaveStartTime = startTime;
        this.leaveEndTime = endTime;
        this.leaveDuration = duration;
        this.applicantId = applicantId;
        this.applicantName = applicantName;
        this.applicantType = applicantType;
    }
}
