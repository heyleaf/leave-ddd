package com.yeahzee.lab.leave.application.dto;

import com.yeahzee.lab.api.dto.ApplicantDTO;
import com.yeahzee.lab.api.dto.ApprovalInfoDTO;
import com.yeahzee.lab.api.dto.ApproverDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateLeaveRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    ApplicantDTO applicantDTO;
    ApproverDTO approverDTO;
    String leaveType;
    ApprovalInfoDTO currentApprovalInfoDTO;
    String startTime;
    String endTime;
    Long duration;
    String status;

}
