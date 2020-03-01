package com.yeahzee.lab.leave.application.facade;

import com.yeahzee.lab.leave.application.dto.*;

import java.text.ParseException;

public interface ILeaveService {
    GetLeaveByApplicantResponseDTO queryLeaveInfosByApplicant(GetLeaveByApplicantRequestDTO requestDTO);
    CreateLeaveResponseDTO createLeave(CreateLeaveRequestDTO requestDTO);
    void updateLeaveBaseInfo(UpdateLeaveBaseInfoRequestDTO requestDTO) throws ParseException;
    void submitApproval(SubmitApprovalRequestDTO requestDTO);
    void batchUpdateLeaveStatus(BatchUpdateLeaveStatusRequestDTO requestDTO);
    GetLeaveResponseDTO getLeaveInfo(GetLeaveRequestDTO getLeaveRequestDTO);


}
