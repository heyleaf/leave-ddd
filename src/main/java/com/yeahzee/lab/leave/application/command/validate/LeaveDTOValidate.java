package com.yeahzee.lab.leave.application.command.validate;

import com.yeahzee.lab.api.dto.LeaveDTO;
import org.springframework.util.StringUtils;

public class LeaveDTOValidate {
    public static void check(LeaveDTO leaveDTO) {
        if (StringUtils.isEmpty(leaveDTO.getStartTime())) {
            throw new IllegalArgumentException("参数错误！");
        }
    }
}
