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
import org.springframework.util.StringUtils;

import java.text.ParseException;

@Service
public class LeaveCmdHandler {
    @Autowired
    ILeaveDomainService leaveDomainService;
    @Autowired
    IApprovalRuleDomainService approvalRuleDomainService;
    @Autowired
    IPersonDomainService personDomainService;

    /**
     * 处理新建请假单命令
     * @param cmd
     */
    public void handle(CreateLeaveCmd cmd) {
        if (StringUtils.isEmpty(cmd.getLeaveId())) {
            // TODO 抛出异常
            System.out.println("请假单号不允许为空！");
        }
        Integer leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(cmd.getLeaveType(), cmd.getApplicantType(), cmd.getLeaveDuration());
        //find next approver
        Approver nextApprover = findNextApprover(cmd.getApplicantId(), leaderMaxLevel);
        Leave leave = new Leave();
        leave.setId(cmd.getLeaveId());
        leave.setType(leaveTypeConvert(cmd.getLeaveType()));
        leaveDomainService.createLeave(leave, leaderMaxLevel, nextApprover);
    }

    /**
     * 处理更新基本信息命令
     * @param cmd
     */
    // TODO 如何处理那种CAS的情况？？
    public void handle(UpdateLeaveBaseInfoCmd cmd) throws ParseException {
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
        if (leave == null) {
            // TODO 异常处理
            System.out.println("请假单不存在");
        }
        Approver nextApprover = findNextApprover(leave.getApplicant().getPersonId(), leave.getLeaderMaxLevel());
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

    private Approver findNextApprover(String applicantId, Integer leaderMaxLevel) {
        //find next approver
        Person leader = personDomainService.findNextApprover(applicantId, leaderMaxLevel);
        if (leader == null) {
            return null;
        } else {
            Approver nextApprover = new Approver();
            nextApprover.setLevel(leader.getRoleLevel());
            nextApprover.setPersonName(leader.getPersonName());
            nextApprover.setPersonId(leader.getPersonId());
            return nextApprover;
        }
    }

    private LeaveType leaveTypeConvert(String leaveType) {
        if ("official".equals(leaveType)) {
            return LeaveType.Official;
        } else if ("internal".equals(leaveType)) {
            return LeaveType.Internal;
        } else {
            return LeaveType.External;
        }
    }
}
