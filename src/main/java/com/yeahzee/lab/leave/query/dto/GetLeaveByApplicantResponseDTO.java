package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetLeaveByApplicantResponseDTO implements Serializable {
    // TODO DTO共用了。。。但不共用，怎么处理？？
    List<LeaveDTO> leaveDTOList;
}
