package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchUpdateLeaveStatusRequestDTO {
    private List<LeaveStatus> leaveStatusList;
    @Data
    public static class LeaveStatus {
        String leaveId;
        String leaveStatus;
    }
}
