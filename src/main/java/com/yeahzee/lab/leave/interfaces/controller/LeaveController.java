package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.LeaveDTO;
import com.yeahzee.lab.api.dto.PersonDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.command.LeaveCommandService;
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
        leaveCommandService.createLeaveInfo(leaveDTO);
        return Response.ok();
    }

    @PutMapping
    public Response updateLeaveInfo(LeaveDTO leaveDTO){
        leaveCommandService.updateLeaveInfo(leaveDTO);
        return Response.ok();
    }

    @PostMapping("/submit")
    public Response submitApproval(LeaveDTO leaveDTO){
        leaveCommandService.submitApproval(leaveDTO);
        return Response.ok();
    }

    /**
     * 参数为DTO的情况
     */
    @PostMapping(value = "/leave/create")
    public Response createLeave(@RequestBody LeaveDTO leaveDTO) {
        String leaveId = this.leaveCommandService.createLeave(leaveDTO);
        return Response.ok(this.leaveQueryService.getLeaveInfo(leaveId));
    }


    // TODO 我只需要LeaveId，需要用LeaveDTO作为参数吗？这种情况该怎么弄？
    @PostMapping("/query/leave/getLeaveById")
    public Response findById(@RequestBody LeaveDTO dto){
        LeaveDTO leaveDTO = leaveQueryService.getLeaveInfo(dto.getLeaveId());
        return Response.ok(leaveDTO);
    }

    /**
     * 根据申请人查询所有请假单
     * @param personDTO
     * @return
     */
    @PostMapping("/query/applicant/getApplicantById")
    public Response queryByApplicant(@RequestBody PersonDTO personDTO){
        List<LeaveDTO> leaveDTOList = leaveQueryService.queryLeaveInfosByApplicant(personDTO.getPersonId());
        return Response.ok(leaveDTOList);
    }

    /**
     * 根据审批人id查询待审批请假单（待办任务）
     * @param personDTO
     * @return
     */
    // TODO 我只需要approverId，需要用PersonDTO作为参数吗？
    @PostMapping("/query/approver/getApproverById")
    public Response queryByApprover(@RequestBody PersonDTO personDTO){
        List<LeaveDTO> leaveDTOList = leaveQueryService.queryLeaveInfosByApprover(personDTO.getPersonId());
        return Response.ok(leaveDTOList);
    }
}
