package com.yeahzee.lab.leave.domain.command.handler;

import com.yeahzee.lab.leave.domain.command.cmd.CreateLeaveCmd;
import com.yeahzee.lab.leave.domain.command.cmd.SubmitApprovalCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveBaseInfoCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveStatusCmd;
import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.entity.ApprovalInfo;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.ApprovalType;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveType;
import com.yeahzee.lab.leave.domain.person.IPersonDomainService;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.rule.IApprovalRuleDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveCmdHandler {

    @Autowired
    ILeaveDomainService leaveDomainService;
    @Autowired
    IPersonDomainService personDomainService;
    @Autowired
    IApprovalRuleDomainService approvalRuleDomainService;

    /**
     * 处理新建请假单命令
     * @param cmd
     */
    public void handle(CreateLeaveCmd cmd) {
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(cmd.getApplicantType(),
                cmd.getLeaveType(), cmd.getLeaveDuration());
        //find next approver
        Person person = personDomainService.findFirstApprover(cmd.getApplicantId(), leaderMaxLevel);

        Approver approver = new Approver(person.getPersonId(),
                person.getPersonName(),
                person.getRoleLevel());
        Leave leave = new Leave();
        leave.setId(cmd.getLeaveId());
        LeaveType leaveType = null;
        if ("offical".equals(cmd.getLeaveType())) {
            leaveType = LeaveType.Official;
        } else {
            leaveType = LeaveType.Internal;
        }
        leave.setType(leaveType);
        leaveDomainService.createLeave(leave, leaderMaxLevel, approver);
    }

    /**
     * 处理更新基本信息命令
     * @param cmd
     */
    public void handle(UpdateLeaveBaseInfoCmd cmd) {
        LeaveBaseInfo leaveBaseInfo = new LeaveBaseInfo();
        leaveBaseInfo.setId(cmd.getLeaveId());
        leaveBaseInfo.setStartTime(cmd.getStartTime());
        leaveBaseInfo.setDuration(cmd.getDuration());
        leaveBaseInfo.setEndTime(cmd.getEndTime());
        leaveBaseInfo.setLeaveType(cmd.getLeaveType());
        leaveDomainService.updateLeaveBaseInfo(leaveBaseInfo);
    }

    /**
     * 处理提交审批信息的命令
     */
    public void handle(SubmitApprovalCmd cmd) {
        // 查询请假单信息
        Leave leave = leaveDomainService.queryById(cmd.getLeaveId());
        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(leave.getApplicant().getPersonType(),
                leave.getType().toString(), leave.getDuration());
        //find next approver
        Person person = personDomainService.findNextApprover(cmd.getApproverId(), leaderMaxLevel);
        Approver nextApprover = new Approver(person.getPersonId(),
                person.getPersonName(),
                person.getRoleLevel());
        ApprovalInfo approvalInfo = new ApprovalInfo();
        approvalInfo.setApprovalInfoId(cmd.getApprovalInfoId());
        ApprovalType approvalType = null;
        if ("agree".equals(cmd.getApprovalType())) {
            approvalType = ApprovalType.AGREE;
        } else {
            approvalType = ApprovalType.REJECT;
        }
        approvalInfo.setApprovalType(approvalType);
        leaveDomainService.submitApproval(leave, approvalInfo, nextApprover);
    }

    /**
     * 处理更新单个请假单状态的命令
     * @param cmd
     */
    public void handle(UpdateLeaveStatusCmd cmd) {
        leaveDomainService.updateLeaveStatus(cmd.getLeaveId(), cmd.getStatus());
    }
}
