/**
 * 事件处理模块
 *
 * 主要职责：
 * 1. 负责领域事件、命令事件的订阅及处理；
 * 2. 负责命令事件的发布（如命令异步处理场景）；
 * 3. 对于接收到的领域事件，调用应用层领域事件处理service处理；
 * 4. 对于接收到的
 */
package com.yeahzee.lab.framework.application.event;