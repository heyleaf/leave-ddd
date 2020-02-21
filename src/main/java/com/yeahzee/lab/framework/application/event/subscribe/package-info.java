/**
 * 事件订阅模块
 *
 * 主要职责：
 * 1. 订阅领域事件，调用IDomainEventService服务进行处理。
 * 2. 订阅命令事件，调用领域层的commandHandler进行处理。
 */
package com.yeahzee.lab.framework.application.event.subscribe;