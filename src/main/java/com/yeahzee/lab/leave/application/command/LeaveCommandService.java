package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.api.dto.LeaveDTO;
import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.person.IPersonDomainService;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.rule.service.ApprovalRuleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveCommandService {

    @Autowired
    ILeaveDomainService leaveDomainService;
    @Autowired
    IPersonDomainService personDomainService;
    @Autowired
    ApprovalRuleDomainService approvalRuleDomainService;

    public String createLeave(LeaveDTO leaveDTO) {
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        return this.leaveDomainService.createLeave(leave);
    }

    /**
     * 创建一个请假申请并为审批人生成任务
     * @param leaveDTO
     */
    public void createLeaveInfo(LeaveDTO leaveDTO){
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(leaveDTO.getApplicantDTO().getApplicantType(),
                leaveDTO.getLeaveType(), leaveDTO.getDuration());
        //find next approver
        Person approver = personDomainService.findFirstApprover(leaveDTO.getApplicantDTO().getPersonId(), leaderMaxLevel);
        leaveDomainService.createLeave(LeaveAssembler.toDO(leaveDTO), leaderMaxLevel, Approver.fromPerson(approver));
    }

    /**
     * 更新请假单基本信息
     */
    public void updateLeaveInfo(LeaveDTO leaveDTO)
    {
        leaveDomainService.updateLeaveInfo(LeaveAssembler.toDO(leaveDTO));
    }

    /**
     * 提交审批，更新请假单信息
     * @param leaveDTO
     */
    public void submitApproval(LeaveDTO leaveDTO){
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(leaveDTO.getApplicantDTO().getApplicantType(),
                leaveDTO.getLeaveType(), leaveDTO.getDuration());
        //find next approver
        Person approver = personDomainService.findNextApprover(leaveDTO.getApproverDTO().getPersonId(),
                leaderMaxLevel);
        leaveDomainService.submitApproval(LeaveAssembler.toDO(leaveDTO), Approver.fromPerson(approver));
    }
}