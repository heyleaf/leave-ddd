package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.application.dto.LeaveDTO;
import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveCommandService {

    @Autowired
    private ILeaveDomainService leaveDomainService;

    public Integer createLeave(LeaveDTO leaveDTO) {
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        return this.leaveDomainService.createLeave(leave);
    }
}
