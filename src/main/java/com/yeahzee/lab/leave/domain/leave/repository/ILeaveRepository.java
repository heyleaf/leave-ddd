package com.yeahzee.lab.leave.domain.leave.repository;

import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEvent;

import java.util.List;

/**
 * 定义操作基础设施层的仓储中聚合内部数据的接口
 *
 * 1. 由基础设施层实现该接口
 */
public interface ILeaveRepository {
    void save(Leave leave);

    void saveLeaveBaseInfo(LeaveBaseInfo leaveBaseInfo);

    void saveEvent(LeaveEvent leaveEvent);

    Leave findById(String id);

    List<Leave> queryByApplicantId(String applicantId);

    List<Leave> queryByApproverId(String approverId);
}
