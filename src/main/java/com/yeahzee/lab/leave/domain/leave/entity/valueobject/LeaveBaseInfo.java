package com.yeahzee.lab.leave.domain.leave.entity.valueobject;

import lombok.Data;

/**
 * 请假单的基础主信息
 */
@Data
public class LeaveBaseInfo {
    String id;
    String leaveType;
    String startTime;
    String endTime;
    long duration;
}
