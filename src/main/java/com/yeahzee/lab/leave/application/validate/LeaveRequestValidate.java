package com.yeahzee.lab.leave.application.validate;

import com.yeahzee.lab.leave.application.dto.*;
import org.springframework.util.StringUtils;

public class LeaveRequestValidate {
    public static void check(CreateLeaveRequestDTO createLeaveRequestDTO) {
        if (null == createLeaveRequestDTO.getApplicantDTO()) {
            throw new IllegalArgumentException("申请人不能为空！");
        }
    }

    public static void check(UpdateLeaveInfoRequestDTO updateLeaveInfoRequestDTO) {
        if (StringUtils.isEmpty(updateLeaveInfoRequestDTO.getLeaveId())) {
            throw new IllegalArgumentException("请假单ID不能为空");
        }
    }

    public static void check(SubmitApprovalRequestDTO submitApprovalRequestDTO) {
        if (StringUtils.isEmpty(submitApprovalRequestDTO.getApprovalInfoId())) {
            throw new IllegalArgumentException("审批ID不能为空");
        }

    }

    public static void check(UpdateLeaveBaseRequestDTO updateLeaveBaseRequestDTO) {
        if (StringUtils.isEmpty(updateLeaveBaseRequestDTO.getLeaveId())) {
            throw new IllegalArgumentException("请假单ID不能为空");
        }
    }

    public static void check(GetLeaveRequestDTO getLeaveRequestDTO) {
        if (StringUtils.isEmpty(getLeaveRequestDTO.getLeaveId())) {
            throw new IllegalArgumentException("请假单ID不能为空");
        }
    }
}
