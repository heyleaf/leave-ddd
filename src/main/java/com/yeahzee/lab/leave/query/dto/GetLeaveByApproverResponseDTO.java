package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetLeaveByApproverResponseDTO implements Serializable {
    List<LeaveDTO> leaveDTOList;
}
