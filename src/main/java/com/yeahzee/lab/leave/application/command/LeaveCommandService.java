package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.api.dto.BatchUpdateLeaveStatusRequestDTO;
import com.yeahzee.lab.api.dto.LeaveStatusDTO;
import com.yeahzee.lab.common.event.CommandPublisher;
import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.application.assembler.LeaveBaseInfoAssembler;
import com.yeahzee.lab.leave.application.command.cmd.UpdateLeaveStatusCmd;
import com.yeahzee.lab.leave.application.dto.CreateLeaveRequestDTO;
import com.yeahzee.lab.leave.application.dto.SubmitApprovalRequestDTO;
import com.yeahzee.lab.leave.application.dto.UpdateLeaveBaseRequestDTO;
import com.yeahzee.lab.leave.application.dto.UpdateLeaveInfoRequestDTO;
import com.yeahzee.lab.leave.application.validate.LeaveRequestValidate;
import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;
import com.yeahzee.lab.leave.domain.person.IPersonDomainService;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.rule.IApprovalRuleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveCommandService {

    @Autowired
    ILeaveDomainService leaveDomainService;
    @Autowired
    IPersonDomainService personDomainService;
    @Autowired
    IApprovalRuleDomainService approvalRuleDomainService;
    @Autowired
    CommandPublisher commandPublisher;

    /**
     * 创建一个请假申请并为审批人生成任务
     * @param createLeaveRequestDTO
     */
    public String createLeaveInfo(CreateLeaveRequestDTO createLeaveRequestDTO){
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(createLeaveRequestDTO.getApplicantDTO().getApplicantType(),
                createLeaveRequestDTO.getLeaveType(), createLeaveRequestDTO.getDuration());
        //find next approver
        Person approver = personDomainService.findFirstApprover(createLeaveRequestDTO.getApplicantDTO().getPersonId(), leaderMaxLevel);
        // TODO 获取全局ID
        String leaveId = "1";
        Leave leave = LeaveAssembler.fromDTO(createLeaveRequestDTO);
        leave.setId(leaveId);
        leaveDomainService.createLeave(leave, leaderMaxLevel, Approver.fromPerson(approver));
        return leaveId;
    }

    /**
     * 更新请假单信息
     */
    public void updateLeaveInfo(UpdateLeaveInfoRequestDTO updateLeaveInfoRequestDTO)
    {
        leaveDomainService.updateLeaveInfo(LeaveAssembler.fromDTO(updateLeaveInfoRequestDTO));
    }

    /**
     * 更新请假单基本信息
     */
    public void updateLeaveBaseInfo(UpdateLeaveBaseRequestDTO updateLeaveBaseRequestDTO)
    {
        LeaveRequestValidate.check(updateLeaveBaseRequestDTO);
        LeaveBaseInfo leaveBaseInfo = LeaveBaseInfoAssembler.fromDTO(updateLeaveBaseRequestDTO);
        leaveDomainService.updateLeaveBaseInfo(leaveBaseInfo);
    }

    /**
     * 提交审批，更新请假单信息
     * @param submitApprovalRequestDTO
     */
    public void submitApproval(SubmitApprovalRequestDTO submitApprovalRequestDTO) {
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(submitApprovalRequestDTO.getApplicantType(),
                submitApprovalRequestDTO.getLeaveType(), submitApprovalRequestDTO.getLeaveDuration());
        //find next approver
        Person approver = personDomainService.findNextApprover(submitApprovalRequestDTO.getApproverDTO().getPersonId(),
                leaderMaxLevel);
        leaveDomainService.submitApproval(LeaveAssembler.fromDTO(submitApprovalRequestDTO), Approver.fromPerson(approver));
    }

    /**
     * 批量更新请假单状态，两种方式：
     *
     * 1. 在应用层以for循环调用领域服务的方式，同步更新。
     * 2. 以异步消息的方式异步更新。
     * @param batchUpdateLeaveStatusRequestDTO
     */
    public void batchUpdateLeaveStatus(BatchUpdateLeaveStatusRequestDTO batchUpdateLeaveStatusRequestDTO) {
        // 1. for循环同步更新
        List<LeaveStatusDTO> leaveStatusDTOList = batchUpdateLeaveStatusRequestDTO.getLeaveStatusDTOList();
        for (LeaveStatusDTO leaveStatusDTO : leaveStatusDTOList) {
            leaveDomainService.updateLeaveStatus(leaveStatusDTO.getLeaveId(), leaveStatusDTO.getStatus());
        }

        // 2. 异步消息的方式异步更新
        for (LeaveStatusDTO leaveStatusDTO : leaveStatusDTOList) {
            this.commandPublisher.publish(new UpdateLeaveStatusCmd(leaveStatusDTO.getLeaveId(),
                    leaveStatusDTO.getStatus()));
        }
    }

    /**
     * 更新单个请假单的状态
     */
    public void updateLeaveStatus(LeaveStatusDTO leaveStatusDTO) {
        leaveDomainService.updateLeaveStatus(leaveStatusDTO.getLeaveId(), leaveStatusDTO.getStatus());
    }
}