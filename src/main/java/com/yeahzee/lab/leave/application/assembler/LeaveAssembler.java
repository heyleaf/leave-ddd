package com.yeahzee.lab.leave.application.assembler;


import com.yeahzee.lab.leave.application.dto.CreateLeaveResponseDTO;
import com.yeahzee.lab.leave.application.dto.GetLeaveByApplicantResponseDTO;
import com.yeahzee.lab.leave.application.dto.GetLeaveResponseDTO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;

import java.util.List;
import java.util.stream.Collectors;

public class LeaveAssembler {

    /**
     * 组装CreateLeaveResponseDTO
     */

    private static CreateLeaveResponseDTO.ApproverDTO genApproverDTO(ApprovalInfoPO approvalInfoPO) {
        CreateLeaveResponseDTO.ApproverDTO approverDTO = new CreateLeaveResponseDTO.ApproverDTO();
        approverDTO.setPersonId(approvalInfoPO.getApproverId());
        approverDTO.setPersonName(approvalInfoPO.getApproverName());
        return approverDTO;
    }

    private static CreateLeaveResponseDTO.ApprovalInfoDTO genApprovalInfoDTO(ApprovalInfoPO approvalInfoPO) {
        CreateLeaveResponseDTO.ApprovalInfoDTO approvalInfoDTO = new CreateLeaveResponseDTO.ApprovalInfoDTO();
        approvalInfoDTO.setApprovalInfoId(approvalInfoPO.getApprovalInfoId());
        approvalInfoDTO.setMsg(approvalInfoPO.getMsg());
        approvalInfoDTO.setTime(approvalInfoPO.getTime());
        approvalInfoDTO.setApproverDTO(genApproverDTO(approvalInfoPO));
        return approvalInfoDTO;
    }

    public static CreateLeaveResponseDTO genCreateLeaveResponseDTO(LeavePO leavePO, ApprovalInfoPO approvalInfoPO) {
        CreateLeaveResponseDTO responseDTO = new CreateLeaveResponseDTO();
        responseDTO.setLeaveId(leavePO.getId());
        responseDTO.setLeaveType(leavePO.getLeaveType().toString());
        responseDTO.setDuration(leavePO.getDuration());
        responseDTO.setStartTime(leavePO.getStartTime().toString());
        responseDTO.setEndTime(leavePO.getEndTime().toString());
        // 申请人信息
        CreateLeaveResponseDTO.ApplicantDTO applicantDTO = new CreateLeaveResponseDTO.ApplicantDTO();
        applicantDTO.setPersonId(leavePO.getApplicantId());
        applicantDTO.setPersonName(leavePO.getApplicantName());
        applicantDTO.setPersonType(leavePO.getApplicantType().toString());
        responseDTO.setApplicantDTO(applicantDTO);
        // 审批人信息
        responseDTO.setApproverDTO(genApproverDTO(approvalInfoPO));
        // 审批历史列表
        List<CreateLeaveResponseDTO.ApprovalInfoDTO> historyApprovalInfoDTOList
                = leavePO.getHistoryApprovalInfoPOList()
                .stream()
                .map(historyApprovalInfoPO -> genApprovalInfoDTO(historyApprovalInfoPO))
                .collect(Collectors.toList());
        responseDTO.setHistoryApprovalInfoDTOList(historyApprovalInfoDTOList);
        // 当前审批信息
        responseDTO.setCurrentApprovalInfoDTO(genApprovalInfoDTO(approvalInfoPO));
        return responseDTO;
    }

    // =====================
    // TODO 待重构
    // =====================

    /**
     * 组装CreateLeaveResponseDTO
     */

    private static GetLeaveResponseDTO.ApproverDTO genGetLeaveApproverDTO(ApprovalInfoPO approvalInfoPO) {
        GetLeaveResponseDTO.ApproverDTO approverDTO = new GetLeaveResponseDTO.ApproverDTO();
        approverDTO.setPersonId(approvalInfoPO.getApproverId());
        approverDTO.setPersonName(approvalInfoPO.getApproverName());
        return approverDTO;
    }

    private static GetLeaveResponseDTO.ApprovalInfoDTO genGetLeaveApprovalInfoDTO(ApprovalInfoPO approvalInfoPO) {
        GetLeaveResponseDTO.ApprovalInfoDTO approvalInfoDTO = new GetLeaveResponseDTO.ApprovalInfoDTO();
        approvalInfoDTO.setApprovalInfoId(approvalInfoPO.getApprovalInfoId());
        approvalInfoDTO.setMsg(approvalInfoPO.getMsg());
        approvalInfoDTO.setTime(approvalInfoPO.getTime());
        approvalInfoDTO.setApproverDTO(genGetLeaveApproverDTO(approvalInfoPO));
        return approvalInfoDTO;
    }

    public static GetLeaveResponseDTO genGetLeaveResponseDTO(LeavePO leavePO, ApprovalInfoPO approvalInfoPO) {
        GetLeaveResponseDTO responseDTO = new GetLeaveResponseDTO();
        responseDTO.setLeaveId(leavePO.getId());
        responseDTO.setLeaveType(leavePO.getLeaveType().toString());
        responseDTO.setDuration(leavePO.getDuration());
        responseDTO.setStartTime(leavePO.getStartTime().toString());
        responseDTO.setEndTime(leavePO.getEndTime().toString());
        // 申请人信息
        GetLeaveResponseDTO.ApplicantDTO applicantDTO = new GetLeaveResponseDTO.ApplicantDTO();
        applicantDTO.setPersonId(leavePO.getApplicantId());
        applicantDTO.setPersonName(leavePO.getApplicantName());
        applicantDTO.setPersonType(leavePO.getApplicantType().toString());
        responseDTO.setApplicantDTO(applicantDTO);
        // 审批人信息
        responseDTO.setApproverDTO(genGetLeaveApproverDTO(approvalInfoPO));
        // 审批历史列表
        List<GetLeaveResponseDTO.ApprovalInfoDTO> historyApprovalInfoDTOList
                = leavePO.getHistoryApprovalInfoPOList()
                .stream()
                .map(historyApprovalInfoPO -> genGetLeaveApprovalInfoDTO(historyApprovalInfoPO))
                .collect(Collectors.toList());
        responseDTO.setHistoryApprovalInfoDTOList(historyApprovalInfoDTOList);
        // 当前审批信息
        responseDTO.setCurrentApprovalInfoDTO(genGetLeaveApprovalInfoDTO(approvalInfoPO));
        return responseDTO;
    }

    //================================================
    //

    private static GetLeaveByApplicantResponseDTO.LeaveDTO.ApproverDTO
            genByApplicantApproverDTO(ApprovalInfoPO approvalInfoPO) {
        GetLeaveByApplicantResponseDTO.LeaveDTO.ApproverDTO approverDTO
                = new GetLeaveByApplicantResponseDTO.LeaveDTO.ApproverDTO();
        approverDTO.setPersonId(approvalInfoPO.getApproverId());
        approverDTO.setPersonName(approvalInfoPO.getApproverName());
        return approverDTO;

    }

    private static GetLeaveByApplicantResponseDTO.LeaveDTO.ApprovalInfoDTO
            genByApplicantApprovalInfoDTO(ApprovalInfoPO approvalInfoPO) {
        GetLeaveByApplicantResponseDTO.LeaveDTO.ApprovalInfoDTO approvalInfoDTO
                = new GetLeaveByApplicantResponseDTO.LeaveDTO.ApprovalInfoDTO();
        approvalInfoDTO.setApprovalInfoId(approvalInfoPO.getApprovalInfoId());
        approvalInfoDTO.setMsg(approvalInfoPO.getMsg());
        approvalInfoDTO.setTime(approvalInfoPO.getTime());
        approvalInfoDTO.setApproverDTO(genByApplicantApproverDTO(approvalInfoPO));
        return approvalInfoDTO;
    }

    public static GetLeaveByApplicantResponseDTO.LeaveDTO genLeaveDTO(LeavePO leavePO, ApprovalInfoPO approvalInfoPO){
        GetLeaveByApplicantResponseDTO.LeaveDTO dto = new GetLeaveByApplicantResponseDTO.LeaveDTO();
        dto.setLeaveId(leavePO.getId());
        dto.setLeaveType(leavePO.getLeaveType().toString());
        dto.setDuration(leavePO.getDuration());
        dto.setStartTime(leavePO.getStartTime().toString());
        dto.setEndTime(leavePO.getEndTime().toString());
        // 申请人信息
        GetLeaveByApplicantResponseDTO.LeaveDTO.ApplicantDTO applicantDTO
                = new GetLeaveByApplicantResponseDTO.LeaveDTO.ApplicantDTO();
        applicantDTO.setPersonId(leavePO.getApplicantId());
        applicantDTO.setPersonName(leavePO.getApplicantName());
        applicantDTO.setPersonType(leavePO.getApplicantType().toString());
        dto.setApplicantDTO(applicantDTO);
        // 审批人信息
        dto.setApproverDTO(genByApplicantApproverDTO(approvalInfoPO));
        // 审批历史列表
        List<GetLeaveByApplicantResponseDTO.LeaveDTO.ApprovalInfoDTO> historyApprovalInfoDTOList
                = leavePO.getHistoryApprovalInfoPOList()
                .stream()
                .map(historyApprovalInfoPO -> genByApplicantApprovalInfoDTO(historyApprovalInfoPO))
                .collect(Collectors.toList());
        dto.setHistoryApprovalInfoDTOList(historyApprovalInfoDTOList);
        // 当前审批信息
        dto.setCurrentApprovalInfoDTO(genByApplicantApprovalInfoDTO(approvalInfoPO));
        return dto;
    }

    public static GetLeaveByApplicantResponseDTO genGetLeaveByApplicantResponseDTO(List<LeavePO> leavePOList) {
        List<GetLeaveByApplicantResponseDTO.LeaveDTO> leaveDTOList
                = leavePOList.stream()
                    .map(leavePO -> genLeaveDTO(leavePO, leavePO.getHistoryApprovalInfoPOList().get(0)))
                    .collect(Collectors.toList());
        GetLeaveByApplicantResponseDTO responseDTO = new GetLeaveByApplicantResponseDTO();
        responseDTO.setLeaveDTOList(leaveDTOList);
        return responseDTO;
    }

}
