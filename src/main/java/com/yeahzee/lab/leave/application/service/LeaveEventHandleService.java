package com.yeahzee.lab.leave.application.service;

import com.yeahzee.lab.leave.application.facade.ILeaveEventHandleService;
import com.yeahzee.lab.leave.domain.leave.event.LeaveCreatedEvent;
import org.springframework.stereotype.Service;

/**
 * 处理接收到的事件
 */
@Service
public class LeaveEventHandleService implements ILeaveEventHandleService {
    /**
     * 处理请假单创建成功的领域事件
     * @param leaveCreatedEvent
     */
    @Override
    public void handle(LeaveCreatedEvent leaveCreatedEvent) {
        System.out.println(leaveCreatedEvent.toString());
    }
}
