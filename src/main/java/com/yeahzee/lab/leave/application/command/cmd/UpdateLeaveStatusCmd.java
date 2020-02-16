package com.yeahzee.lab.leave.application.command.cmd;

import com.yeahzee.lab.common.event.Command;
import lombok.Data;

import java.io.Serializable;

/**
 * 更新请假单状态命令
 */
@Data
public class UpdateLeaveStatusCmd extends Command implements Serializable {
    private static final long serialVersionUID = 1L;
    String leaveId;
    String status;

    public UpdateLeaveStatusCmd(String leaveId, String status) {
        this.leaveId = leaveId;
        this.status = status;
    }
}
