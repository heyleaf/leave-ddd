package com.yeahzee.lab.leave.query.validate;

import com.yeahzee.lab.leave.query.dto.GetLeaveRequestDTO;
import org.springframework.util.StringUtils;

/**
 * Query模式下的参数检验
 */
public class LeaveQueryValidate {
    public static void check(GetLeaveRequestDTO getLeaveRequestDTO) {
        if (StringUtils.isEmpty(getLeaveRequestDTO.getLeaveId())) {
            throw new IllegalArgumentException("请假单Id不能为空");
        }
    }
}
