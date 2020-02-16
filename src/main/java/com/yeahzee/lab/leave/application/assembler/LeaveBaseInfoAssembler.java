package com.yeahzee.lab.leave.application.assembler;

import com.yeahzee.lab.api.dto.LeaveBaseUpdateDTO;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;

public class LeaveBaseInfoAssembler {

    public static LeaveBaseInfo fromDTO(LeaveBaseUpdateDTO leaveBaseUpdateDTO) {
        LeaveBaseInfo leaveBaseInfo = new LeaveBaseInfo();
        leaveBaseInfo.setDuration(leaveBaseUpdateDTO.getDuration());
        leaveBaseInfo.setEndTime(leaveBaseUpdateDTO.getEndTime());
        leaveBaseInfo.setStartTime(leaveBaseUpdateDTO.getStartTime());
        leaveBaseInfo.setLeaveType(leaveBaseUpdateDTO.getLeaveType());
        leaveBaseInfo.setId(leaveBaseUpdateDTO.getId());
        return leaveBaseInfo;
    }
}
