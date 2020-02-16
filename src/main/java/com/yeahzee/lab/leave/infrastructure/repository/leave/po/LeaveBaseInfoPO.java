package com.yeahzee.lab.leave.infrastructure.repository.leave.po;

import lombok.Data;

@Data
public class LeaveBaseInfoPO {
    String id;
    String leaveType;
    String startTime;
    String endTime;
    long duration;
}
