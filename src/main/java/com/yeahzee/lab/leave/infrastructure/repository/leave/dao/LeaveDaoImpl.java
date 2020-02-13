package com.yeahzee.lab.leave.infrastructure.repository.leave.dao;

import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.stereotype.Repository;

/**
 * 自行模拟的一个实现，实际工程中会是mybatis实现。
 */
@Repository
public class LeaveDaoImpl implements LeaveDao {
    @Override
    public LeavePO selectLeaveById(Integer id) {
        return null;
    }

    @Override
    public void save(LeavePO leavePO) {
    }
}
