package com.yeahzee.lab.leave.domain.leave.repository;

import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEvent;

import java.util.List;

/**
 * 定义操作基础设施层的仓储中聚合内部数据的接口
 *
 * 1. 由基础设施层实现该接口
 * 2. TODO 如何定义一个数据结构承接传递给基础设施层的参数，直接用entity目录下定义的实体？
 * 3. TODO 如何定义一个数据结构承接从基础设施层返回的数据，直接返回entity目录下定义的实体？
 */
public interface ILeaveRepository {
    void save(Leave leave);

    void saveEvent(LeaveEvent leaveEvent);

    Leave findById(String id);

    List<Leave> queryByApplicantId(String applicantId);

    List<Leave> queryByApproverId(String approverId);
}
