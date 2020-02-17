package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetLeaveResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    String leaveId;
    ApplicantDTO applicantDTO;
    ApproverDTO approverDTO;
    String leaveType;
    ApprovalInfoDTO currentApprovalInfoDTO;
    List<ApprovalInfoDTO> historyApprovalInfoDTOList;
    String startTime;
    String endTime;
    long duration;
    String status;

}
