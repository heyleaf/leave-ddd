package com.yeahzee.lab.leave.infrastructure.repository.leave.dao;

import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDao {
    LeavePO selectLeaveById(Integer id);
    void save(LeavePO leavePO);
}
