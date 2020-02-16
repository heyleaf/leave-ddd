package com.yeahzee.lab.api.dto;

import lombok.Data;

@Data
public class LeaveBaseUpdateDTO {
    String id;
    String leaveType;
    String startTime;
    String endTime;
    long duration;
}
