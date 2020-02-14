package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.application.command.LeaveCommandService;
import com.yeahzee.lab.leave.application.dto.LeaveDTO;
import com.yeahzee.lab.leave.application.query.LeaveQueryService;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response getLeave(@RequestBody Integer leaveId) {
        return Response.ok(this.leaveQueryService.getLeaveById(leaveId));
    }

    /**
     * 参数为DTO的情况
     */
    @PostMapping(value = "/leave/create")
    public Response createLeave(@RequestBody LeaveDTO leaveDTO) {
        Integer leaveId = this.leaveCommandService.createLeave(leaveDTO);
        return Response.ok(this.leaveQueryService.getLeaveById(leaveId));
    }

//
//    @PostMapping("/{leaveId}")
//    public Response findById(@PathVariable String leaveId){
//        Leave leave = leaveCommandService.getLeaveInfo(leaveId);
//        return Response.ok(LeaveAssembler.toDTO(leave));
//    }
//
//    /**
//     * 根据申请人查询所有请假单
//     * @param applicantId
//     * @return
//     */
//    @PostMapping("/query/applicant/{applicantId}")
//    public Response queryByApplicant(@PathVariable String applicantId){
//        List<Leave> leaveList = leaveCommandService.queryLeaveInfosByApplicant(applicantId);
//        List<LeaveDTO> leaveDTOList = leaveList.stream().map(leave -> LeaveAssembler.toDTO(leave)).collect(Collectors.toList());
//        return Response.ok(leaveDTOList);
//    }
//
//    /**
//     * 根据审批人id查询待审批请假单（待办任务）
//     * @param approverId
//     * @return
//     */
//    @PostMapping("/query/approver/{approverId}")
//    public Response queryByApprover(@PathVariable String approverId){
//        List<Leave> leaveList = leaveCommandService.queryLeaveInfosByApprover(approverId);
//        List<LeaveDTO> leaveDTOList = leaveList.stream().map(leave -> LeaveAssembler.toDTO(leave)).collect(Collectors.toList());
//        return Response.ok(leaveDTOList);
//    }
}
