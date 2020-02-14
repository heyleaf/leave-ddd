package com.yeahzee.lab.leave.domain.leave.event;

import com.yeahzee.lab.common.event.DomainEvent;

/**
 * 定义领域事件发布接口
 *
 * 1. 由基础设施层实现该接口
 */
public interface ILeaveEventPublisher {
    // TODO 可以改为更通用的方式
    boolean publish(DomainEvent event);
}
