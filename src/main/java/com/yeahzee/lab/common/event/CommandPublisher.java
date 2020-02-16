package com.yeahzee.lab.common.event;

import org.springframework.stereotype.Service;

@Service
public class CommandPublisher {
    public void publish(Command cmd) {
        // send to MQ
        // mq.send(cmd);
    }
}
