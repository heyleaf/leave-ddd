package com.yeahzee.lab.leave.application.query;

import com.yeahzee.lab.leave.application.dto.LeaveDTO;
import com.yeahzee.lab.leave.application.query.repository.ILeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveQueryService {
    @Autowired
    private ILeaveRepository leaveRepository;

    public LeaveDTO getLeaveById(Integer leaveId) {
        return this.leaveRepository.queryLeaveById(leaveId);
    }
}
