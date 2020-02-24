package com.yeahzee.lab.leave.application.event;

import com.yeahzee.lab.leave.domain.command.cmd.Command;
import org.springframework.stereotype.Service;

@Service
public class CommandPublisher {
    public void publish(Command cmd) {
        // send msg
    }
}
