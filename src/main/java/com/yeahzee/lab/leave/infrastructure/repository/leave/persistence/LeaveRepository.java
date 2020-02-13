package com.yeahzee.lab.leave.infrastructure.repository.leave.persistence;

import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.repository.ILeaveRepository;
import com.yeahzee.lab.leave.infrastructure.repository.leave.dao.LeaveDao;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeaveRepository implements ILeaveRepository {

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public void save(Leave leave) {
        LeavePO leavePO = new LeavePO();
        leavePO.setDuration(leave.getDuration());
        leavePO.setEndTime(leave.getEndTime());
        leavePO.setId(leave.getId());
        leavePO.setLeaveType(leave.getLeaveType());
        leavePO.setStartTime(leave.getStartTime());
        leaveDao.save(leavePO);
    }
}
