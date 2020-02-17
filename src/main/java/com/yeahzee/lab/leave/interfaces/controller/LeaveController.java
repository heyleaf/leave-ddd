package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.api.dto.BatchUpdateLeaveStatusRequestDTO;
import com.yeahzee.lab.common.api.Response;
import com.yeahzee.lab.leave.application.command.LeaveCommandService;
import com.yeahzee.lab.leave.application.dto.CreateLeaveRequestDTO;
import com.yeahzee.lab.leave.application.dto.CreateLeaveResponseDTO;
import com.yeahzee.lab.leave.application.dto.SubmitApprovalRequestDTO;
import com.yeahzee.lab.leave.application.dto.UpdateLeaveBaseRequestDTO;
import com.yeahzee.lab.leave.application.validate.LeaveRequestValidate;
import com.yeahzee.lab.leave.query.LeaveQueryService;
import com.yeahzee.lab.leave.query.dto.*;
import com.yeahzee.lab.leave.query.validate.LeaveQueryValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController {

    @Autowired
    LeaveCommandService leaveCommandService;
    @Autowired
    private LeaveQueryService leaveQueryService;

//    /**
//     * 更新请假单信息
//     *  FIXME 不应该出现类似没有明确性的场景
//     */
//    @PutMapping
//    public Response updateLeaveInfo(UpdateLeaveInfoRequestDTO updateLeaveInfoRequestDTO){
//        LeaveRequestValidate.check(updateLeaveInfoRequestDTO);
//        leaveCommandService.updateLeaveInfo(updateLeaveInfoRequestDTO);
//        return Response.ok();
//    }
    /**
     * 新建请假单
     */
    @PostMapping(value = "/leave/createLeave")
    public Response createLeave(@RequestBody CreateLeaveRequestDTO createLeaveRequestDTO) {
        // 校验参数
        LeaveRequestValidate.check(createLeaveRequestDTO);
        String leaveId = this.leaveCommandService.createLeaveInfo(createLeaveRequestDTO);
        GetLeaveRequestDTO queryDto = new GetLeaveRequestDTO();
        queryDto.setLeaveId(leaveId);
        GetLeaveResponseDTO queryResultDTO = this.leaveQueryService.getLeaveInfo(queryDto);
        // TODO 疑问：
        //  这里要将 GetLeaveResponseDTO 再转为 CreateLeaveResponseDTO？
        //  如果要转：1. 工作量大；2. 两个DTO中有同名的其他DTO如何处理？
        //  如果不转：返回值的名称和接口不一致。

        CreateLeaveResponseDTO responseDTO = new CreateLeaveResponseDTO();
        // reponseDTO = Assembler.toCreateLeaveResponseDTO(queryResultDTO);
        return Response.ok(responseDTO);
    }

    /**
     * 更新请假单基本主信息
     * @param updateLeaveBaseRequestDTO
     * @return
     */
    @PostMapping(value = "/leave/updateBaseInfo")
    public Response updateLeaveBaseInfo(UpdateLeaveBaseRequestDTO updateLeaveBaseRequestDTO){
        LeaveRequestValidate.check(updateLeaveBaseRequestDTO);
        leaveCommandService.updateLeaveBaseInfo(updateLeaveBaseRequestDTO);
        return Response.ok();
    }

    /**
     * 提交审批信息
     */
    @PostMapping("/submit")
    public Response submitApproval(SubmitApprovalRequestDTO submitApprovalRequestDTO){
        LeaveRequestValidate.check(submitApprovalRequestDTO);
        leaveCommandService.submitApproval(submitApprovalRequestDTO);
        return Response.ok();
    }


    /**
     * 根据leaveQueryDTO中的查询条件查询请假单
     * @param getLeaveRequestDTO
     * @return
     */
    @PostMapping("/query/leave/getLeave")
    public Response getLeave(@RequestBody GetLeaveRequestDTO getLeaveRequestDTO){
        LeaveQueryValidate.check(getLeaveRequestDTO);
        GetLeaveResponseDTO responseDTO = leaveQueryService.getLeaveInfo(getLeaveRequestDTO);
        return Response.ok(responseDTO);
    }

    /**
     * 根据申请人查询所有请假单
     * @param requestDTO
     * @return
     */
    @PostMapping("/query/applicant/getLeaveByApplicant")
    public Response getLeaveByApplicant(@RequestBody GetLeaveByApplicantRequestDTO requestDTO){
        GetLeaveByApplicantResponseDTO responseDTO = leaveQueryService.queryLeaveInfosByApplicant(requestDTO);
        return Response.ok(responseDTO);
    }

    /**
     * 根据审批人id查询待审批请假单（待办任务）
     * @param requestDTO
     * @return
     */
    // TODO 我只需要approverId，需要用PersonDTO作为参数吗？
    @PostMapping("/query/approver/getLeaveByApprover")
    public Response getLeaveByApprover(@RequestBody GetLeaveByApproverRequestDTO requestDTO){
        GetLeaveByApproverResponseDTO responseDTO = leaveQueryService.queryLeaveInfosByApprover(requestDTO);
        return Response.ok(responseDTO);
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
