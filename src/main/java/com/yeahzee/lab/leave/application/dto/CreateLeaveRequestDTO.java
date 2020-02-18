package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

/**
 * 新建请假单请求DTO
 */
@Data
public class CreateLeaveRequestDTO {

    private ApplicantDTO applicantDTO;
    private String leaveType;
    private String startTime;
    private String endTime;
    private Long duration;

    @Data
    public static class ApplicantDTO {
        private String personId;
        private String personName;
        private String leaderId;
        private String applicantType;
        private String roleLevel;
    }
}
