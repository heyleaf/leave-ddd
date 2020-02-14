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
     * @param leave
     */
    public void createLeaveInfo(Leave leave){
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(leave.getApplicant().getPersonType(), leave.getType().toString(), leave.getDuration());
        //find next approver
        Person approver = personDomainService.findFirstApprover(leave.getApplicant().getPersonId(), leaderMaxLevel);
        leaveDomainService.createLeave(leave, leaderMaxLevel, Approver.fromPerson(approver));
    }

    /**
     * 更新请假单基本信息
     * @param leave
     */
    public void updateLeaveInfo(Leave leave){
        leaveDomainService.updateLeaveInfo(leave);
    }

    /**
     * 提交审批，更新请假单信息
     * @param leave
     */
    public void submitApproval(Leave leave){
        //find next approver
        Person approver = personDomainService.findNextApprover(leave.getApprover().getPersonId(), leave.getLeaderMaxLevel());
        leaveDomainService.submitApproval(leave, Approver.fromPerson(approver));
    }
}