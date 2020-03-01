package com.yeahzee.lab.leave.domain.command.facade;

import com.yeahzee.lab.leave.domain.command.cmd.CreateLeaveCmd;
import com.yeahzee.lab.leave.domain.command.cmd.SubmitApprovalCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveBaseInfoCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveStatusCmd;

import java.text.ParseException;

public interface ILeaveCmdHandler {
    void handle(CreateLeaveCmd cmd);
    void handle(UpdateLeaveBaseInfoCmd cmd) throws ParseException;
    void handle(SubmitApprovalCmd cmd);
    void handle(UpdateLeaveStatusCmd cmd);
}
