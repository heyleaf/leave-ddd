package com.yeahzee.lab.leave.infrastructure.queryrepository;

import com.yeahzee.lab.leave.application.query.repository.ILeaveRepository;
import com.yeahzee.lab.leave.infrastructure.repository.leave.dao.LeaveDao;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeaveRepository implements ILeaveRepository {

    @Autowired
    private LeaveDao leaveDao;

    @Override
    public LeaveDTO queryLeaveById(Integer leaveId) {
        LeavePO leavePO = this.leaveDao.selectLeaveById(leaveId);
        LeaveDTO leaveDTO = new LeaveDTO();
        leaveDTO.setDuration(leavePO.getDuration());
        leaveDTO.setEndTime(leavePO.getEndTime());
        leaveDTO.setStartTime(leavePO.getStartTime());
        leaveDTO.setLeaveType(leavePO.getLeaveType());
        return leaveDTO;
    }
}
