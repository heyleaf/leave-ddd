package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.LeaveDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.application.command.LeaveCommandService;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.query.LeaveQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController {

    @Autowired
    LeaveCommandService leaveCommandService;
    @Autowired
    private LeaveQueryService leaveQueryService;

    @PostMapping
    public Response createLeaveInfo(LeaveDTO leaveDTO){
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveCommandService.createLeaveInfo(leave);
        return Response.ok();
    }

    @PutMapping
    public Response updateLeaveInfo(LeaveDTO leaveDTO){
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveCommandService.updateLeaveInfo(leave);
        return Response.ok();
    }

    @PostMapping("/submit")
    public Response submitApproval(LeaveDTO leaveDTO){
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        leaveCommandService.submitApproval(leave);
        return Response.ok();
    }



    /**
     * 简单参数的情况
     */
    @PostMapping(value = "/leave/getLeave")
    public Response getLeave(@RequestBody String leaveId) {
        return Response.ok(this.leaveQueryService.getLeaveInfo(leaveId));
    }

    /**
     * 参数为DTO的情况
     */
    @PostMapping(value = "/leave/create")
    public Response createLeave(@RequestBody LeaveDTO leaveDTO) {
        String leaveId = this.leaveCommandService.createLeave(leaveDTO);
        return Response.ok(this.leaveQueryService.getLeaveInfo(leaveId));
    }


    @PostMapping("/{leaveId}")
    public Response findById(@PathVariable String leaveId){
        LeaveDTO leaveDTO = leaveQueryService.getLeaveInfo(leaveId);
        return Response.ok(leaveDTO);
    }

    /**
     * 根据申请人查询所有请假单
     * @param applicantId
     * @return
     */
    @PostMapping("/query/applicant/{applicantId}")
    public Response queryByApplicant(@PathVariable String applicantId){
        List<LeaveDTO> leaveDTOList = leaveQueryService.queryLeaveInfosByApplicant(applicantId);
        return Response.ok(leaveDTOList);
    }

    /**
     * 根据审批人id查询待审批请假单（待办任务）
     * @param approverId
     * @return
     */
    @PostMapping("/query/approver/{approverId}")
    public Response queryByApprover(@PathVariable String approverId){
        List<LeaveDTO> leaveDTOList = leaveQueryService.queryLeaveInfosByApprover(approverId);
        return Response.ok(leaveDTOList);
    }
}
