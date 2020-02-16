package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.*;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.command.LeaveCommandService;
import com.yeahzee.lab.leave.application.dto.CreateLeaveRequestDTO;
import com.yeahzee.lab.leave.application.dto.SubmitApprovalRequestDTO;
import com.yeahzee.lab.leave.application.dto.UpdateLeaveInfoRequestDTO;
import com.yeahzee.lab.leave.application.validate.LeaveRequestValidate;
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

    /**
     * 更新请假单信息
     *  FIXME 不应该出现类似没有明确性的场景
     */
    @PutMapping
    public Response updateLeaveInfo(UpdateLeaveInfoRequestDTO updateLeaveInfoRequestDTO){
        LeaveRequestValidate.check(updateLeaveInfoRequestDTO);
        leaveCommandService.updateLeaveInfo(updateLeaveInfoRequestDTO);
        return Response.ok();
    }

    /**
     * 更新请假单基本主信息
     * @param leaveBaseUpdateDTO
     * @return
     */
    @PostMapping(value = "/leave/updateBaseInfo")
    public Response updateLeaveBaseInfo(LeaveBaseUpdateDTO leaveBaseUpdateDTO){
        leaveCommandService.updateLeaveBaseInfo(leaveBaseUpdateDTO);
        return Response.ok();
    }

    /**
     * 提交审批信息
     */
    @PostMapping("/submit")
    public Response submitApproval(SubmitApprovalRequestDTO submitApprovalRequestDTO){
        leaveCommandService.submitApproval(submitApprovalRequestDTO);
        return Response.ok();
    }


    /**
     * 新建请假单
     */
    @PostMapping(value = "/leave/createLeave")
    public Response createLeave(@RequestBody CreateLeaveRequestDTO createLeaveRequestDTO) {
        // 校验参数
        LeaveRequestValidate.check(createLeaveRequestDTO);
        String leaveId = this.leaveCommandService.createLeaveInfo(createLeaveRequestDTO);
        return Response.ok(this.leaveQueryService.getLeaveInfo(leaveId));
    }

    /**
     * 根据leaveQueryDTO中的查询条件查询请假单
     * @param leaveQueryDTO
     * @return
     */
    @PostMapping("/query/leave/getLeave")
    public Response getLeave(@RequestBody LeaveQueryDTO leaveQueryDTO){
        LeaveDTO leaveDTO = leaveQueryService.getLeaveInfo(leaveQueryDTO.getLeaveId());
        return Response.ok(leaveDTO);
    }

    /**
     * 根据申请人查询所有请假单
     * @param applicantDTO
     * @return
     */
    @PostMapping("/query/applicant/getLeaveByApplicant")
    public Response queryByApplicant(@RequestBody ApplicantDTO applicantDTO){
        List<LeaveDTO> leaveDTOList = leaveQueryService.queryLeaveInfosByApplicant(applicantDTO.getPersonId());
        return Response.ok(leaveDTOList);
    }

    /**
     * 根据审批人id查询待审批请假单（待办任务）
     * @param approverDTO
     * @return
     */
    // TODO 我只需要approverId，需要用PersonDTO作为参数吗？
    @PostMapping("/query/approver/getLeaveByApprover")
    public Response queryByApprover(@RequestBody ApproverDTO approverDTO){
        List<LeaveDTO> leaveDTOList = leaveQueryService.queryLeaveInfosByApprover(approverDTO.getPersonId());
        return Response.ok(leaveDTOList);
    }

    /**
     * 批量更新请假单状态
     */
    @PostMapping("/leave/batchUpdateLeaveStatus")
    public Response batchUpdateLeaveStatus(@RequestBody BatchUpdateLeaveStatusRequestDTO batchUpdateLeaveStatusRequestDTO) {
        leaveCommandService.batchUpdateLeaveStatus(batchUpdateLeaveStatusRequestDTO);
        return Response.ok();
    }

}
