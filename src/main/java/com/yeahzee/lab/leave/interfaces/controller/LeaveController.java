package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.leave.application.command.LeaveCommandService;
import com.yeahzee.lab.leave.application.dto.LeaveDTO;
import com.yeahzee.lab.leave.application.query.LeaveQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveController {

    @Autowired
    private LeaveQueryService leaveQueryService;
    @Autowired
    private LeaveCommandService leaveCommandService;

    /**
     * 简单参数的情况
     */
    @PostMapping(value = "/leave/getLeave")
    public LeaveDTO getLeave(@RequestBody Integer leaveId) {
        return this.leaveQueryService.getLeaveById(leaveId);
    }

    /**
     * 参数为DTO的情况
     */
    @PostMapping(value = "/leave/create")
    public LeaveDTO createLeave(@RequestBody LeaveDTO leaveDTO) {
        Integer leaveId = this.leaveCommandService.createLeave(leaveDTO);
        return this.leaveQueryService.getLeaveById(leaveId);
    }
}
