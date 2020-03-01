package com.yeahzee.lab.leave.domain.command.facade;

import com.yeahzee.lab.leave.domain.command.cmd.CreatePersonCmd;

import java.text.ParseException;

public interface IPersonCmdHandler {
    void handle(CreatePersonCmd cmd) throws ParseException;
}
