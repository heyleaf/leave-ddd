package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.event.LeaveCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 事件处理服务，处理消费者消费到的事件
 *
 * 定义再command目录下的原因：
 * 1. 事件的处理，一定是Command模式
 * 2. 与事件的具体接收方式分离，如可能是RocketMQ，RabbitMQ，甚至可能是上层同步调用
 *
 */
@Service
public class LeaveCreatedEventService {
    @Autowired
    private ILeaveDomainService leaveDomainService;

    public void handle(LeaveCreatedEvent event) {
        leaveDomainService.createLeave(null);
        System.out.println("leaveCreated！" + event.toString());
    }
}
