package com.yeahzee.lab.leave.application.commandhandler;

import com.yeahzee.lab.leave.application.command.CreateLeaveCommand;
import com.yeahzee.lab.leave.application.dto.LeaveDTO;

/**
 * 创建命令的工厂
 *
 * 将DTO转为Command
 */
public class ComandFactory {
    public CreateLeaveCommand toCommand(LeaveDTO leaveDTO) {
        return new CreateLeaveCommand(leaveDTO.getLeaveType(),
                leaveDTO.getStartTime(),
                leaveDTO.getEndTime(),
                leaveDTO.getDuration());
    }
}
