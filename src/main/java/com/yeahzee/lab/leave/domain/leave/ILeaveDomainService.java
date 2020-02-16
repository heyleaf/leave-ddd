package com.yeahzee.lab.leave.domain.leave;

import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;

/**
 * 领域服务接口定义
 *
 * 1. 应用层只能通过该接口调用领域层该聚合提供的功能
 * 2. 领域服务接口命名规范：I{聚合根名称}DomainService
 */
public interface ILeaveDomainService {

    String createLeave(Leave leave);
    void createLeave(Leave leave, int leaderMaxLevel, Approver approver);
    void updateLeaveInfo(Leave leave);
    void submitApproval(Leave leave, Approver approver);
    void updateLeaveBaseInfo(LeaveBaseInfo leaveBaseInfo);
    void updateLeaveStatus(String leaveId, String status);

}