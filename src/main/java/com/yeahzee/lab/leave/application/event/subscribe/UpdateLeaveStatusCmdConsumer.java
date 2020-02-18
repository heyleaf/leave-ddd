package com.yeahzee.lab.leave.application.event.subscribe;

import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveStatusCmd;
import com.yeahzee.lab.leave.domain.command.handler.LeaveCmdHandler;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "update_leave_status_cmd", consumerGroup = "leave-group")
public class UpdateLeaveStatusCmdConsumer implements RocketMQListener {

    @Autowired
    LeaveCmdHandler leaveCmdHandler;

    @Override
    public void onMessage(Object message) {
        // TODO message to UpdateLeaveStatusCmd
        this.leaveCmdHandler.handle((UpdateLeaveStatusCmd)message);
    }

}
