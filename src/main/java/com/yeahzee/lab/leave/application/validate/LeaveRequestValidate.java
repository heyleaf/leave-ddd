package com.yeahzee.lab.leave.application.validate;

import com.yeahzee.lab.leave.application.dto.CreateLeaveRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetLeaveRequestDTO;
import com.yeahzee.lab.leave.application.dto.SubmitApprovalRequestDTO;
import com.yeahzee.lab.leave.application.dto.UpdateLeaveBaseInfoRequestDTO;
import org.springframework.util.StringUtils;

public class LeaveRequestValidate {
    public static void check(CreateLeaveRequestDTO createLeaveRequestDTO) {
        if (null == createLeaveRequestDTO.getApplicantDTO()) {
            throw new IllegalArgumentException("申请人不能为空！");
        }
    }

    public static void check(SubmitApprovalRequestDTO submitApprovalRequestDTO) {
        if (StringUtils.isEmpty(submitApprovalRequestDTO.getApprovalInfoId())) {
            throw new IllegalArgumentException("审批ID不能为空");
        }

    }

    public static void check(UpdateLeaveBaseInfoRequestDTO updateLeaveBaseInfoRequestDTO) {
        if (StringUtils.isEmpty(updateLeaveBaseInfoRequestDTO.getLeaveId())) {
            throw new IllegalArgumentException("请假单ID不能为空");
        }
    }
    public static void check(GetLeaveRequestDTO getLeaveRequestDTO) {
        if (StringUtils.isEmpty(getLeaveRequestDTO.getLeaveId())) {
            throw new IllegalArgumentException("请假单Id不能为空");
        }
    }
}
