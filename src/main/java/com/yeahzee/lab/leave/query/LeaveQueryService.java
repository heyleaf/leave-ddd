package com.yeahzee.lab.leave.query;

import com.yeahzee.lab.api.dto.LeaveDTO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.ApprovalInfoDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.LeaveDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import com.yeahzee.lab.leave.query.assembler.LeaveAssembler;
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

    public LeaveDTO getLeaveInfo(String leaveId) {
        LeavePO leavePO = leaveDao.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("leave not found"));
        ApprovalInfoPO currentApprovalInfoPO = leavePO.getHistoryApprovalInfoPOList().get(0);
        return LeaveAssembler.toDTO(leavePO, currentApprovalInfoPO);
    }

    public List<LeaveDTO> queryLeaveInfosByApplicant(String applicantId){
        List<LeavePO> leavePOList = leaveDao.queryByApplicantId(applicantId);
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });

        return leavePOList.stream()
                .map(leavePO -> LeaveAssembler.toDTO(leavePO, leavePO.getHistoryApprovalInfoPOList().get(0)))
                .collect(Collectors.toList());
    }

    public List<LeaveDTO> queryLeaveInfosByApprover(String approverId){
        List<LeavePO> leavePOList = leaveDao.queryByApproverId(approverId);
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        return leavePOList.stream()
                .map(leavePO -> LeaveAssembler.toDTO(leavePO, leavePO.getHistoryApprovalInfoPOList().get(0)))
                .collect(Collectors.toList());
    }
}
