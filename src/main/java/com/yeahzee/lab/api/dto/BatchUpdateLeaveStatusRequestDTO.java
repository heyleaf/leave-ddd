package com.yeahzee.lab.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchUpdateLeaveStatusRequestDTO {
    List<LeaveStatusDTO> leaveStatusDTOList;
}
