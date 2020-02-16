package com.yeahzee.lab.leave.application.validate;

import com.yeahzee.lab.api.dto.LeaveBaseUpdateDTO;

public class LeaveBaseUpdateDTOValidate {
    public static void check(LeaveBaseUpdateDTO leaveBaseUpdateDTO) {
        if (leaveBaseUpdateDTO.getStartTime() == null) {
            throw new IllegalArgumentException("startTime 不能为空");
        }
    }
}
