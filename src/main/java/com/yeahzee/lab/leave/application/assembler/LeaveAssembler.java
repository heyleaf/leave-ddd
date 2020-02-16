package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.leave.application.dto.CreateLeaveRequestDTO;
import com.yeahzee.lab.leave.application.dto.SubmitApprovalRequestDTO;
import com.yeahzee.lab.leave.application.dto.UpdateLeaveInfoRequestDTO;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;

public class LeaveAssembler {
    public static Leave fromDTO(CreateLeaveRequestDTO dto){
        Leave leave = new Leave();
        leave.setApplicant(ApplicantAssembler.toDO(dto.getApplicantDTO()));
        leave.setApprover(ApproverAssembler.toDO(dto.getApproverDTO()));
        leave.setCurrentApprovalInfo(ApprovalInfoAssembler.toDO(dto.getCurrentApprovalInfoDTO()));
        return leave;
    }

    public static Leave fromDTO(UpdateLeaveInfoRequestDTO updateLeaveInfoRequestDTO) {
        Leave leave = new Leave();
        leave.setApplicant(ApplicantAssembler.toDO(updateLeaveInfoRequestDTO.getApplicantDTO()));
        leave.setApprover(ApproverAssembler.toDO(updateLeaveInfoRequestDTO.getApproverDTO()));
        leave.setCurrentApprovalInfo(ApprovalInfoAssembler.toDO(updateLeaveInfoRequestDTO.getCurrentApprovalInfoDTO()));
        leave.setId(updateLeaveInfoRequestDTO.getLeaveId());
        return leave;
    }

    public static Leave fromDTO(SubmitApprovalRequestDTO submitApprovalRequestDTO) {
        Leave leave = new Leave();
        leave.setId(submitApprovalRequestDTO.getLeaveId());
        return leave;
    }

}
