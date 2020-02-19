package com.yeahzee.lab.leave.application.service;

import com.yeahzee.lab.common.event.CommandPublisher;
import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.application.dto.*;
import com.yeahzee.lab.leave.domain.command.cmd.CreateLeaveCmd;
import com.yeahzee.lab.leave.domain.command.cmd.SubmitApprovalCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveBaseInfoCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveStatusCmd;
import com.yeahzee.lab.leave.domain.command.handler.LeaveCmdHandler;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.ApprovalInfoDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.LeaveDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {
    @Autowired
    LeaveCmdHandler leaveCmdHandler;
    @Autowired
    LeaveDAO leaveDAO;
    @Autowired
    ApprovalInfoDAO approvalInfoDAO;
    @Autowired
    CommandPublisher commandPublisher;

    public CreateLeaveResponseDTO createLeave(CreateLeaveRequestDTO requestDTO) {
        // 获取创建命令需要的外部数据（领域层未拥有）数据
        // TODO 通过远程服务获取全局ID
        String leaveId = "1";
        // 创建命令
        CreateLeaveCmd createLeaveCmd = new CreateLeaveCmd(
                leaveId, requestDTO.getLeaveType(),
                requestDTO.getStartTime(), requestDTO.getEndTime(),
                requestDTO.getDuration(), requestDTO.getApplicantDTO().getPersonId(),
                requestDTO.getApplicantDTO().getPersonName(),
                requestDTO.getApplicantDTO().getApplicantType());
        // 处理命名
        leaveCmdHandler.handle(createLeaveCmd);
        // 查询创建成功的请假单
        LeavePO leavePO = leaveDAO.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("leave not found"));
        ApprovalInfoPO currentApprovalInfoPO = leavePO.getHistoryApprovalInfoPOList().get(0);
        return LeaveAssembler.genCreateLeaveResponseDTO(leavePO, currentApprovalInfoPO);
    }

    /**
     * 更新请假单基本信息
     * @param requestDTO
     */
    public void updateLeaveBaseInfo(UpdateLeaveBaseInfoRequestDTO requestDTO) throws ParseException {
        // 创建命令
        UpdateLeaveBaseInfoCmd cmd = new UpdateLeaveBaseInfoCmd(requestDTO.getLeaveId(),
                requestDTO.getLeaveType(), requestDTO.getStartTime(),
                requestDTO.getEndTime(), requestDTO.getDuration());
        // 处理命令
        leaveCmdHandler.handle(cmd);
    }

    /**
     * 提交审批信息
     * @param requestDTO
     */
    public void submitApproval(SubmitApprovalRequestDTO requestDTO) {
        // TODO 通过远程服务获取全局ID
        String approvalInfoId = "approvalInfoId";
        // 创建命令
        SubmitApprovalCmd cmd = new SubmitApprovalCmd(requestDTO.getLeaveId(),
                approvalInfoId,
                requestDTO.getMsg(), requestDTO.getTime(),
                requestDTO.getApproverDTO().getPersonId(),
                requestDTO.getApproverDTO().getPersonName(),
                requestDTO.getApprovalType());
        // 处理命令
        leaveCmdHandler.handle(cmd);
    }

    /**
     * 批量更新请假单状态
     */
    public void batchUpdateLeaveStatus(BatchUpdateLeaveStatusRequestDTO requestDTO) {
        List<BatchUpdateLeaveStatusRequestDTO.LeaveStatus> leaveStatusList = requestDTO.getLeaveStatusList();
        List<UpdateLeaveStatusCmd> cmdList = requestDTO.getLeaveStatusList()
                .stream()
                .map(leaveStatus -> new UpdateLeaveStatusCmd(leaveStatus.getLeaveId(), leaveStatus.getLeaveStatus()))
                .collect(Collectors.toList());

        // 1. for循环同步更新
        for (UpdateLeaveStatusCmd cmd : cmdList) {
            leaveCmdHandler.handle(cmd);
        }

        // 2. 异步消息的方式异步更新
        for (UpdateLeaveStatusCmd cmd : cmdList) {
            this.commandPublisher.publish(cmd);
        }
    }


    /**
     * ================== 查询 ====================
     */
    /**
     * 根据LeaveId查询Leave完整信息
     * @param getLeaveRequestDTO
     * @return
     */
    public GetLeaveResponseDTO getLeaveInfo(GetLeaveRequestDTO getLeaveRequestDTO) {
        LeavePO leavePO = leaveDAO.findById(getLeaveRequestDTO.getLeaveId())
                .orElseThrow(() -> new RuntimeException("leave not found"));
        ApprovalInfoPO currentApprovalInfoPO = leavePO.getHistoryApprovalInfoPOList().get(0);
        return LeaveAssembler.genGetLeaveResponseDTO(leavePO, currentApprovalInfoPO);
    }

    /**
     * 查询申请人的请假单列表
     * @param requestDTO
     * @return
     */
    public GetLeaveByApplicantResponseDTO queryLeaveInfosByApplicant(GetLeaveByApplicantRequestDTO requestDTO){
        List<LeavePO> leavePOList = leaveDAO.queryByApplicantId(requestDTO.getPersonId());
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDAO.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });

        return LeaveAssembler.genGetLeaveByApplicantResponseDTO(leavePOList);
    }

}
