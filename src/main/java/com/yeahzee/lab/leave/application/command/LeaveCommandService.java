package com.yeahzee.lab.leave.application.command;

import com.yeahzee.lab.api.dto.BatchUpdateLeaveStatusRequestDTO;
import com.yeahzee.lab.api.dto.LeaveBaseUpdateDTO;
import com.yeahzee.lab.api.dto.LeaveDTO;
import com.yeahzee.lab.api.dto.LeaveStatusDTO;
import com.yeahzee.lab.common.event.CommandPublisher;
import com.yeahzee.lab.leave.application.assembler.LeaveAssembler;
import com.yeahzee.lab.leave.application.assembler.LeaveBaseInfoAssembler;
import com.yeahzee.lab.leave.application.command.cmd.UpdateLeaveStatusCmd;
import com.yeahzee.lab.leave.application.command.validate.LeaveBaseUpdateDTOValidate;
import com.yeahzee.lab.leave.application.command.validate.LeaveDTOValidate;
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

    public String createLeave(LeaveDTO leaveDTO) {
        Leave leave = LeaveAssembler.toDO(leaveDTO);
        return this.leaveDomainService.createLeave(leave);
    }

    /**
     * 创建一个请假申请并为审批人生成任务
     * @param leaveDTO
     */
    public void createLeaveInfo(LeaveDTO leaveDTO){
        // validate：对传入的参数进行校验
        LeaveDTOValidate.check(leaveDTO);

        //get approval leader max level by rule
        int leaderMaxLevel = approvalRuleDomainService.getLeaderMaxLevel(leaveDTO.getApplicantDTO().getApplicantType(),
                leaveDTO.getLeaveType(), leaveDTO.getDuration());
        //find next approver
        Person approver = personDomainService.findFirstApprover(leaveDTO.getApplicantDTO().getPersonId(), leaderMaxLevel);
        leaveDomainService.createLeave(LeaveAssembler.toDO(leaveDTO), leaderMaxLevel, Approver.fromPerson(approver));
    }

    /**
     * 更新请假单信息
     */
    public void updateLeaveInfo(LeaveDTO leaveDTO)
    {
        leaveDomainService.updateLeaveInfo(LeaveAssembler.toDO(leaveDTO));
    }

    /**
     * 更新请假单基本信息
     */
    public void updateLeaveBaseInfo(LeaveBaseUpdateDTO leaveBaseUpdateDTO)
    {
        LeaveBaseUpdateDTOValidate.check(leaveBaseUpdateDTO);
        LeaveBaseInfo leaveBaseInfo = LeaveBaseInfoAssembler.fromDTO(leaveBaseUpdateDTO);
        leaveDomainService.updateLeaveBaseInfo(leaveBaseInfo);
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