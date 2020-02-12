package com.yeahzee.lab.leave.domain.leave;

/**
 * 领域服务接口定义
 *
 * 1. 应用层只能通过该接口调用领域层该聚合提供的功能
 * 2. 领域服务接口命名规范：I{聚合根名称}DomainService
 */
public interface ILeaveDomainService {

    Integer createLeave();

}