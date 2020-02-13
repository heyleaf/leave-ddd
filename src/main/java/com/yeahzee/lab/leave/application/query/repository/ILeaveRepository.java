package com.yeahzee.lab.leave.application.query.repository;

import com.yeahzee.lab.leave.application.dto.LeaveDTO;

/**
 * 定义从基础设施层仓储数据操作接口
 *
 * 1. query模式下，可以直接从基础设施层获取DTO。
 */
public interface ILeaveRepository {
    LeaveDTO queryLeaveById(Integer leaveId);
}
