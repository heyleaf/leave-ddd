package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.util.List;

@Data
public class LeaveDTO {
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

}
