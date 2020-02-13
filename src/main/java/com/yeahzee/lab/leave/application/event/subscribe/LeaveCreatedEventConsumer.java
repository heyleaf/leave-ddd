package com.yeahzee.lab.leave.application.event.subscribe;

import com.yeahzee.lab.leave.application.command.LeaveCreatedEventService;
import com.yeahzee.lab.leave.domain.leave.event.LeaveCreatedEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "leave_created_event", consumerGroup = "leave-group")
public class LeaveCreatedEventConsumer implements RocketMQListener {

    @Autowired
    private LeaveCreatedEventService leaveCreatedEventService;

    @Override
    public void onMessage(Object message) {
        // TODO message to Event
        this.leaveCreatedEventService.handle((LeaveCreatedEvent)message);
    }
}
