package com.yeahzee.lab.leave.application.assembler;

import com.yeahzee.lab.leave.application.dto.UpdateLeaveBaseRequestDTO;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;

public class LeaveBaseInfoAssembler {

    public static LeaveBaseInfo fromDTO(UpdateLeaveBaseRequestDTO updateLeaveBaseRequestDTO) {
        LeaveBaseInfo leaveBaseInfo = new LeaveBaseInfo();
        leaveBaseInfo.setDuration(updateLeaveBaseRequestDTO.getDuration());
        leaveBaseInfo.setEndTime(updateLeaveBaseRequestDTO.getEndTime());
        leaveBaseInfo.setStartTime(updateLeaveBaseRequestDTO.getStartTime());
        leaveBaseInfo.setLeaveType(updateLeaveBaseRequestDTO.getLeaveType());
        leaveBaseInfo.setId(updateLeaveBaseRequestDTO.getLeaveId());
        return leaveBaseInfo;
    }
}
