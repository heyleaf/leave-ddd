package com.yeahzee.lab.leave.domain.command.cmd;

import lombok.Data;

@Data
public class UpdateLeaveBaseInfoCmd {
    String leaveId;
    String leaveType;
    String startTime;
    String endTime;
    Long duration;

    public UpdateLeaveBaseInfoCmd(String leaveId, String leaveType, String startTime,
                                  String endTime, Long duration) {
        this.leaveId = leaveId;
        this.leaveType = leaveType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
}
