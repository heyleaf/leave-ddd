package com.yeahzee.lab.leave.domain.command.handler;

import com.yeahzee.lab.common.util.DateUtil;
import com.yeahzee.lab.leave.domain.command.cmd.CreateLeaveCmd;
import com.yeahzee.lab.leave.domain.command.cmd.SubmitApprovalCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveBaseInfoCmd;
import com.yeahzee.lab.leave.domain.command.cmd.UpdateLeaveStatusCmd;
import com.yeahzee.lab.leave.domain.leave.entity.ApprovalInfo;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.ApprovalType;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveType;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Status;
import com.yeahzee.lab.leave.domain.leave.event.ILeaveEventPublisher;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEvent;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEventType;
import com.yeahzee.lab.leave.domain.leave.repository.ILeaveRepository;
import com.yeahzee.lab.leave.domain.person.entity.Person;
import com.yeahzee.lab.leave.domain.person.repository.IPersonRepository;
import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;
import com.yeahzee.lab.leave.domain.rule.repository.IApprovalRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;

@Service
public class LeaveCmdHandler {
    @Autowired
    ILeaveRepository leaveRepository;
    @Autowired
    IApprovalRuleRepository approvalRuleRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    ILeaveEventPublisher eventPublisher;

    /**
     * 处理新建请假单命令
     * @param cmd
     */
    public void handle(CreateLeaveCmd cmd) {
        if (StringUtils.isEmpty(cmd.getLeaveId())) {
            // TODO 抛出异常
            System.out.println("请假单号不允许为空！");
        }
        Integer leaderMaxLevel = getLeaderMaxLevel(cmd.getLeaveType(), cmd.getApplicantType(), cmd.getLeaveDuration());
        //find next approver
        Approver nextApprover = findNextApprover(cmd.getApplicantId(), leaderMaxLevel);
        Leave leave = new Leave();
        leave.setId(cmd.getLeaveId());
        leave.setType(leaveTypeConvert(cmd.getLeaveType()));
        leave.setLeaderMaxLevel(leaderMaxLevel);
        leave.setApprover(nextApprover);
        leave.create();
        leaveRepository.save(leave);
        LeaveEvent event = LeaveEvent.create(LeaveEventType.CREATE_EVENT, leave);
        leaveRepository.saveEvent(event);
        eventPublisher.publish(event);
    }

    /**
     * 处理更新基本信息命令
     * @param cmd
     */
    // TODO 如何处理那种CAS的情况？？
    public void handle(UpdateLeaveBaseInfoCmd cmd) throws ParseException {
        Leave leave = leaveRepository.findById(cmd.getLeaveId());
        if (null == leave) {
            throw new RuntimeException("leave does not exist");
        }
        leave.setId(cmd.getLeaveId());
        leave.setStartTime(DateUtil.parseDate(cmd.getStartTime()));
        leave.setDuration(cmd.getDuration());
        leave.setEndTime(DateUtil.parseDate(cmd.getEndTime()));
        leave.setType(leaveTypeConvert(cmd.getLeaveType()));
        leaveRepository.save(leave);
    }

    /**
     * 处理提交审批信息的命令
     */
    public void handle(SubmitApprovalCmd cmd) {
        // 查询请假单信息
        Leave leave = leaveRepository.findById(cmd.getLeaveId());
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
        // TODO 这段逻辑放哪里？？感觉是leave内部逻辑
        LeaveEvent event;
        if ( ApprovalType.REJECT == approvalInfo.getApprovalType()) {
            //reject, then the leave is finished with REJECTED status
            leave.reject(nextApprover);
            event = LeaveEvent.create(LeaveEventType.REJECT_EVENT, leave);
        } else {
            if (nextApprover != null) {
                //agree and has next approver
                leave.agree(nextApprover);
                event = LeaveEvent.create(LeaveEventType.AGREE_EVENT, leave);
            } else {
                //agree and hasn't next approver, then the leave is finished with APPROVED status
                leave.finish();
                event = LeaveEvent.create(LeaveEventType.APPROVED_EVENT, leave);
            }
        }
        leave.addHistoryApprovalInfo(leave.getCurrentApprovalInfo());
        leaveRepository.save(leave);
        leaveRepository.saveEvent(event);
        eventPublisher.publish(event);
    }

    /**
     * 处理更新单个请假单状态的命令
     * @param cmd
     */
    public void handle(UpdateLeaveStatusCmd cmd) {
        Leave leave = leaveRepository.findById(cmd.getLeaveId());
        if (leave == null) {
            // TODO 异常处理
            System.out.println("请假单不存在！");
        }
        Status status = null;
        if ("rejected".equals(cmd.getStatus())) {
            status = Status.REJECTED;
        } else {
            status = Status.APPROVED;
        }
        leave.setStatus(status);
        leaveRepository.save(leave);
    }

    private Integer getLeaderMaxLevel(String leaveType, String applicantType, Long duration) {
        //get approval leader max level by rule
        ApprovalRule rule = new ApprovalRule();
        rule.setDuration(duration);
        rule.setLeaveType(leaveType);
        rule.setPersonType(applicantType);
        return approvalRuleRepository.findByRule(rule).getMaxLeaderLevel();
    }

    private Approver findNextApprover(String applicantId, Integer leaderMaxLevel) {
        //find next approver
        Approver nextApprover = new Approver();
        Person leader = personRepository.findLeaderByPersonId(applicantId);
        if (leader.getRoleLevel() > leaderMaxLevel) {
            nextApprover = null;
        } else {
            nextApprover.setLevel(leader.getRoleLevel());
            nextApprover.setPersonName(leader.getPersonName());
            nextApprover.setPersonId(leader.getPersonId());
        }
        return nextApprover;
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
