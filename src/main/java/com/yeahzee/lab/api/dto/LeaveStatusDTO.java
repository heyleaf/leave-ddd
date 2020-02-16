package com.yeahzee.lab.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LeaveStatusDTO implements Serializable {

    String leaveId;
    String status;
}
