package com.yeahzee.lab.leave.domain.leave.service;

import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;

/**
 * 领域服务
 *
 * * 领域服务放在聚合内，实现领域服务接口
 * * 领域服务必须有对应的聚合根
 * * 领域服务命名规范：{聚合根名称}DomainService
 */
public class LeaveDomainService implements ILeaveDomainService {

    @Override
    public Integer createLeave() {
        return 1;
    }
}