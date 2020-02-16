package com.yeahzee.lab.leave.application.dto;

import com.yeahzee.lab.api.dto.ApplicantDTO;
import com.yeahzee.lab.api.dto.ApprovalInfoDTO;
import com.yeahzee.lab.api.dto.ApproverDTO;

import java.util.List;

public class CreateLeaveResponseDTO {
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
