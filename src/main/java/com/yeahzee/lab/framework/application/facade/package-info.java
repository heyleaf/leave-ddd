/**
 * 应用层service对应的的接口定义目录
 *
 * 主要职责：
 * 1. 应用层的唯一入口，定义应用层service对应的接口，供interfaces层调用。
 * 2. 建议将新建/删除/修改操作接口，查询/搜索操作接口，领域事件处理接口独立分开，以使代码职责更清晰，更好维护。
 * 3. 读请求处理接口(I***QueryService)：输入参数及返回值均为DTO。
 * 4. 写请求处理接口（I***SetService）：输入参数为DTO，返回值为DTO或void。因为此处的写接口不是严格意义上的CQRS，因此不用CommandService命名。
 * 5. 领域事件处理接口（I***DomainEventService）：输入参数为DomainEvent，返回值为void
 */
package com.yeahzee.lab.framework.application.facade;