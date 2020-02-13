package com.yeahzee.lab.leave.application.assembler;

import com.yeahzee.lab.leave.application.dto.LeaveDTO;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;

public class LeaveAssembler {
    public static Leave toDO (LeaveDTO leaveDTO) {
        Leave leave = new Leave();
        leave.setEndTime(leaveDTO.getEndTime());
        leave.setDuration(leaveDTO.getDuration());
        leave.setLeaveType(leaveDTO.getLeaveType());
        leave.setStartTime(leaveDTO.getStartTime());
        return leave;
    }
}
