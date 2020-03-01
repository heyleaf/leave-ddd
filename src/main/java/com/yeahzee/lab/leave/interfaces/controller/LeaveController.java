package com.yeahzee.lab.leave.interfaces.controller;

import com.yeahzee.lab.leave.application.dto.*;
import com.yeahzee.lab.leave.application.facade.ILeaveService;
import com.yeahzee.lab.leave.application.validate.LeaveRequestValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/leave")
@Slf4j
public class LeaveController {

    @Autowired
    ILeaveService leaveService;

    /**
     * 新建请假单
     */
    @PostMapping(value = "/leave/createLeave")
    public Response createLeave(@RequestBody CreateLeaveRequestDTO createLeaveRequestDTO) {
        // 校验参数
        LeaveRequestValidate.check(createLeaveRequestDTO);
        CreateLeaveResponseDTO responseDTO = this.leaveService.createLeave(createLeaveRequestDTO);
        return Response.ok(responseDTO);
    }

    /**
     * 更新请假单基本主信息
     * @param updateLeaveBaseInfoRequestDTO
     * @return
     */
    @PostMapping(value = "/leave/updateBaseInfo")
    public Response updateLeaveBaseInfo(UpdateLeaveBaseInfoRequestDTO updateLeaveBaseInfoRequestDTO){
        LeaveRequestValidate.check(updateLeaveBaseInfoRequestDTO);
        try {
            this.leaveService.updateLeaveBaseInfo(updateLeaveBaseInfoRequestDTO);
            return Response.ok();
        } catch (ParseException e) {
            e.printStackTrace();
            return Response.ok();
        }
    }

    /**
     * 提交审批信息
     */
    @PostMapping("/submit")
    public Response submitApproval(SubmitApprovalRequestDTO submitApprovalRequestDTO){
        LeaveRequestValidate.check(submitApprovalRequestDTO);
        leaveService.submitApproval(submitApprovalRequestDTO);
        return Response.ok();
    }

    /**
     * 批量更新请假单状态
     */
    @PostMapping("/leave/batchUpdateLeaveStatus")
    public Response batchUpdateLeaveStatus(@RequestBody BatchUpdateLeaveStatusRequestDTO batchUpdateLeaveStatusRequestDTO) {
        leaveService.batchUpdateLeaveStatus(batchUpdateLeaveStatusRequestDTO);
        return Response.ok();
    }


    /**
     * 根据leaveQueryDTO中的查询条件查询请假单
     * @param getLeaveRequestDTO
     * @return
     */
    @PostMapping("/query/leave/getLeave")
    public Response getLeave(@RequestBody GetLeaveRequestDTO getLeaveRequestDTO){
        LeaveRequestValidate.check(getLeaveRequestDTO);
        GetLeaveResponseDTO responseDTO = leaveService.getLeaveInfo(getLeaveRequestDTO);
        return Response.ok(responseDTO);
    }

    /**
     * 根据申请人查询所有请假单
     * @param requestDTO
     * @return
     */
    @PostMapping("/query/applicant/getLeaveByApplicant")
    public Response getLeaveByApplicant(@RequestBody GetLeaveByApplicantRequestDTO requestDTO){
        GetLeaveByApplicantResponseDTO responseDTO = leaveService.queryLeaveInfosByApplicant(requestDTO);
        return Response.ok(responseDTO);
    }
}
