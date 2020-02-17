package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateLeaveBaseRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    String leaveId;
    String leaveType;
    String startTime;
    String endTime;
    Long duration;
}
