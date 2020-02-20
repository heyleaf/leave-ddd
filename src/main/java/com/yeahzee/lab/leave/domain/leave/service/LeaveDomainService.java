package com.yeahzee.lab.leave.domain.leave.service;

import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.entity.ApprovalInfo;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.ApprovalType;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;
import com.yeahzee.lab.leave.domain.leave.event.ILeaveEventPublisher;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEvent;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEventType;
import com.yeahzee.lab.leave.domain.leave.repository.ILeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * TODO 疑问：
 * <p>
 * 1. 目前规定领域层传给基础设施层的都是领域对象，对于哪些无关联的简单领域对象实体还好，直接从数据库中获取；
 * 但对于那些有关联的实体（如聚合根），它在数据库中保存的是关联ID，但是在领域对象中是引用整个entity，
 * 此时从基础设施层查出来的聚合根实体，需要把整个引用的entity都查找出来？很多场景之下可能只需要用聚合根的
 * 主数据，而不需要它引用的实体的数据。
 * 2. 如上，是不是可以只查询出聚合根实体的主数据，而不需要带出其引用的entity?
 * 3. 更进一步，如果领域逻辑中只需要部分主数据，如只需要account的账号名称和密码，想到了三种处理方式：
 * （1）返回account entity，entity中只有name和secret有数据，其他段无数据，领域层从entity中get这两个数据。
 * （2）新建一个简单类，只有name和secret两个字段；
 * （3）返回account entity, 且entity中所有字段均填充，领域层获取其想要的值。
 * 个人想法，在无惰性加载的前提下，取（1）会更好，一方面接口扩展性强，且领域层是知道自己需要的数据，不会乱取。另一方面保障了效率。
 * 4. 其他DTO，应用层与领域层的接口，也都会有这个问题。
 * 个人认为：这些较为内聚的参数，应该是领域内的值对象；而那些毫无关联的参数，就是用简单参数。
 */

@Service

public class LeaveDomainService implements ILeaveDomainService {

    @Autowired
    ILeaveEventPublisher eventPublisher;
    @Autowired
    ILeaveRepository leaveRepository;

    @Transactional
    public String createLeave(Leave leave, int leaderMaxLevel, Approver approver) {
        leave.setLeaderMaxLevel(leaderMaxLevel);
        leave.setApprover(approver);
        leave.create();
        leaveRepository.save(leave);
        LeaveEvent event = LeaveEvent.create(LeaveEventType.CREATE_EVENT, leave);
        leaveRepository.saveEvent(event);
        eventPublisher.publish(event);
        return leave.getId();
    }


    @Transactional
    public void updateLeaveInfo(Leave leave) {
        Leave po = leaveRepository.findById(leave.getId());
        if (null == po) {
            throw new RuntimeException("leave does not exist");
        }
        leaveRepository.save(leave);
    }

    @Transactional
    public void updateLeaveBaseInfo(LeaveBaseInfo leaveBaseInfo) {
        // 业务逻辑validate：需要保证聚合内的数据一致性
        Leave leave = leaveRepository.findById(leaveBaseInfo.getId());
        if (null == leave) {
            throw new RuntimeException("leave does not exist");
        }
        leaveRepository.saveLeaveBaseInfo(leaveBaseInfo);
    }

    @Transactional
    public void submitApproval(Leave leave, ApprovalInfo approvalInfo, Approver nextApprover) {
        if (null == leave) {
            // TODO 异常处理
            System.out.println("请假单不存在");
        }

        LeaveEvent event;
        if (ApprovalType.REJECT == approvalInfo.getApprovalType()) {
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

    @Override
    public void updateLeaveStatus(String leaveId, String status) {

    }


    @Override
    public Leave queryById(String leaveId) {
        return leaveRepository.findById(leaveId);
    }

}