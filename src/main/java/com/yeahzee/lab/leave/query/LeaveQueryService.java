package com.yeahzee.lab.leave.query;

import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.ApprovalInfoDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.LeaveDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import com.yeahzee.lab.leave.query.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.query.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveQueryService {
    @Autowired
    private LeaveDAO leaveDao;

    @Autowired
    private ApprovalInfoDAO approvalInfoDao;

    public GetLeaveResponseDTO getLeaveInfo(GetLeaveRequestDTO getLeaveRequestDTO) {
        LeavePO leavePO = leaveDao.findById(getLeaveRequestDTO.getLeaveId())
                .orElseThrow(() -> new RuntimeException("leave not found"));
        ApprovalInfoPO currentApprovalInfoPO = leavePO.getHistoryApprovalInfoPOList().get(0);
        return LeaveAssembler.toGetLeaveResponseDTO(leavePO, currentApprovalInfoPO);
    }

    /**
     * 查询申请人的请假单列表
     * @param requestDTO
     * @return
     */
    public GetLeaveByApplicantResponseDTO queryLeaveInfosByApplicant(GetLeaveByApplicantRequestDTO requestDTO){
        List<LeavePO> leavePOList = leaveDao.queryByApplicantId(requestDTO.getPersonId());
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });

        List<LeaveDTO> leaveDTOList = leavePOList.stream()
                .map(leavePO -> LeaveAssembler.toDTO(leavePO, leavePO.getHistoryApprovalInfoPOList().get(0)))
                .collect(Collectors.toList());
        GetLeaveByApplicantResponseDTO responseDTO = new GetLeaveByApplicantResponseDTO();
        responseDTO.setLeaveDTOList(leaveDTOList);
        return responseDTO;
    }

    /**
     * 根据审批人查询请假单列表
     * @param requestDTO
     * @return
     */
    // TODO 这样的输入输出参数，绝对是不行的吧？！！！ 其他接口的输入输出参数也一样
    public GetLeaveByApproverResponseDTO queryLeaveInfosByApprover(GetLeaveByApproverRequestDTO requestDTO){
        List<LeavePO> leavePOList = leaveDao.queryByApproverId(requestDTO.getPersonId());
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        List<LeaveDTO> leaveDTOList = leavePOList.stream()
                .map(leavePO -> LeaveAssembler.toDTO(leavePO, leavePO.getHistoryApprovalInfoPOList().get(0)))
                .collect(Collectors.toList());
        GetLeaveByApproverResponseDTO responseDTO = new GetLeaveByApproverResponseDTO();
        responseDTO.setLeaveDTOList(leaveDTOList);
        return responseDTO;
    }
}
