package com.yeahzee.lab.leave.infrastructure.event.publish;

import com.yeahzee.lab.leave.domain.leave.event.ILeaveEventPublisher;
import com.yeahzee.lab.leave.domain.leave.event.LeaveCreatedEvent;

/**
 * 实现领域层定义的具体领域事件发布接口
 *
 * TODO 可抽象为更为通用的实现
 */
public class LeaveEventPublisher implements ILeaveEventPublisher {


    @Override
    public boolean publish(LeaveCreatedEvent event) {
        // ... ...
        return false;
    }
}
