package com.yeahzee.lab.leave.domain.leave.entity.valueobject;

import lombok.Data;

import java.util.Date;

/**
 * 请假单的基础主信息
 */
@Data
public class LeaveBaseInfo {
    String id;
    LeaveType leaveType;
    Date startTime;
    Date endTime;
    Long duration;
}
