package com.yeahzee.lab.leave.application.facade;

import com.yeahzee.lab.leave.domain.leave.event.LeaveCreatedEvent;

public interface ILeaveEventHandleService {
    void handle(LeaveCreatedEvent leaveCreatedEvent);
}
