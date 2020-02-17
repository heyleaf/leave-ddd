package com.yeahzee.lab.leave.application.dto;

import com.yeahzee.lab.api.dto.ApplicantDTO;
import com.yeahzee.lab.api.dto.ApprovalInfoDTO;
import com.yeahzee.lab.api.dto.ApproverDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请假单之后的返回DTO
 */
@Data
public class CreateLeaveResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
