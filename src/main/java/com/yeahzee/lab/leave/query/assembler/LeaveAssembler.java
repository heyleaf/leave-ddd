package com.yeahzee.lab.leave.query.assembler;

import com.yeahzee.lab.api.dto.ApprovalInfoDTO;
import com.yeahzee.lab.api.dto.LeaveDTO;
import com.yeahzee.lab.common.util.DateUtil;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;

import java.util.List;
import java.util.stream.Collectors;

public class LeaveAssembler {
    public static LeaveDTO toDTO(LeavePO leavePO, ApprovalInfoPO currentApprovalInfoPO){
        LeaveDTO dto = new LeaveDTO();
        dto.setLeaveId(leavePO.getId());
        dto.setLeaveType(leavePO.getLeaveType().toString());
        dto.setStatus(leavePO.getStatus().toString());
        dto.setStartTime(DateUtil.formatDateTime(leavePO.getStartTime()));
        dto.setEndTime(DateUtil.formatDateTime(leavePO.getEndTime()));
        dto.setCurrentApprovalInfoDTO(ApprovalInfoAssembler.toDTO(currentApprovalInfoPO));
        List<ApprovalInfoDTO> historyApprovalInfoDTOList = leavePO.getHistoryApprovalInfoPOList()
                .stream()
                .map(historyApprovalInfoPO -> ApprovalInfoAssembler.toDTO(historyApprovalInfoPO))
                .collect(Collectors.toList());
        dto.setHistoryApprovalInfoDTOList(historyApprovalInfoDTOList);
        dto.setDuration(leavePO.getDuration());
        return dto;
    }
}
