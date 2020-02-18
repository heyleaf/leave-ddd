package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetLeaveByApplicantResponseDTO implements Serializable {
    List<LeaveDTO> leaveDTOList;

    @Data
    public static class LeaveDTO {
        String leaveId;
        ApplicantDTO applicantDTO;
        ApproverDTO approverDTO;
        String leaveType;
        ApprovalInfoDTO currentApprovalInfoDTO;
        List<ApprovalInfoDTO> historyApprovalInfoDTOList;
        String startTime;
        String endTime;
        Long duration;
        String status;

        @Data
        public static class ApplicantDTO {
            String personId;
            String personName;
            String personType;
        }

        @Data
        public static class ApproverDTO {
            String personId;
            String personName;
        }

        @Data
        public static class ApprovalInfoDTO {
            String approvalInfoId;
            ApproverDTO approverDTO;
            String msg;
            Long time;
        }

    }
}
