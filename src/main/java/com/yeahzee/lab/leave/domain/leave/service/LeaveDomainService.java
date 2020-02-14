package com.yeahzee.lab.leave.domain.leave.service;

import com.yeahzee.lab.leave.domain.leave.ILeaveDomainService;
import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.ApprovalType;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Approver;
import com.yeahzee.lab.leave.domain.leave.event.ILeaveEventPublisher;
import com.yeahzee.lab.leave.domain.leave.event.LeaveCreatedEvent;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEvent;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEventType;
import com.yeahzee.lab.leave.domain.leave.repository.ILeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 领域服务
 *
 * 1. 领域服务放在聚合内，实现领域服务接口
 * 2. 领域服务必须有对应的聚合根
 * 3. 领域服务命名规范：{聚合根名称}DomainService
 * 4. 聚合内数据存储操作，放在领域服务内，而非聚合根内
 * 5. 领域事件的发布，放在领域服务内，而非聚合根内
 * 6. 聚合内数据的生命周期维护，放在聚合根内，聚合根只是处理内存数据
 */

/**
 * 与欧创新修改之处：
 * 1. repository的实现放在了基础设施层，而非领域层。
 * 2. PO 与 entity的转换放在了基础设施层，即LeaveFactory移到了基础设施层。
 *
 * TODO 疑问：
 * 1. 目前规定领域层传给基础设施层的都是领域对象，对于哪些无关联的简单领域对象实体还好，直接从数据库中获取；
 *    但对于那些有关联的实体（如聚合根），它在数据库中保存的是关联ID，但是在领域对象中是引用整个entity，
 *    此时从基础设施层查出来的聚合根实体，需要把整个引用的entity都查找出来？很多场景之下可能只需要用聚合根的
 *    主数据，而不需要它引用的实体的数据。
 * 2. 如上，是不是可以只查询出聚合根实体的主数据，而不需要带出其引用的entity?
 * 3. 更进一步，如果领域逻辑中只需要部分主数据，如只需要account的账号名称和密码，想到了三种处理方式：
 *    （1）返回account entity，entity中只有name和secret有数据，其他段无数据，领域层从entity中get这两个数据。
 *    （2）新建一个简单类，只有name和secret两个字段；
 *    （3）返回account entity, 且entity中所有字段均填充，领域层获取其想要的值。
 *    个人想法，在无惰性加载的前提下，取（1）会更好，一方面接口扩展性强，且领域层是知道自己需要的数据，不会乱取。另一方面保障了效率。
 * 4. 其他DTO，应用层与领域层的接口，也都会有这个问题。
 *
 */
@Service
public class LeaveDomainService implements ILeaveDomainService {

    @Autowired
    ILeaveEventPublisher eventPublisher;
    @Autowired
    ILeaveRepository leaveRepository;

    @Override
    public Integer createLeave(Leave leave) {
        // TODO 获取全局唯一ID
        Integer leaveId = 1;
        leave.setId(leaveId.toString());
        this.leaveRepository.save(leave);
        this.eventPublisher.publish(new LeaveCreatedEvent());
        return leaveId;
    }

    @Transactional
    public void createLeave(Leave leave, int leaderMaxLevel, Approver approver) {
        leave.setLeaderMaxLevel(leaderMaxLevel);
        leave.setApprover(approver);
        leave.create();
        leaveRepository.save(leave);
        LeaveEvent event = LeaveEvent.create(LeaveEventType.CREATE_EVENT, leave);
        leaveRepository.saveEvent(event);
        eventPublisher.publish(event);
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
    public void submitApproval(Leave leave, Approver approver) {
        LeaveEvent event;
        if ( ApprovalType.REJECT == leave.getCurrentApprovalInfo().getApprovalType()) {
            //reject, then the leave is finished with REJECTED status
            leave.reject(approver);
            event = LeaveEvent.create(LeaveEventType.REJECT_EVENT, leave);
        } else {
            if (approver != null) {
                //agree and has next approver
                leave.agree(approver);
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

    public Leave getLeaveInfo(String leaveId) {
        return leaveRepository.findById(leaveId);
    }

    public List<Leave> queryLeaveInfosByApplicant(String applicantId) {
        // 柳朕修改：因为直接从数据库中查询出领域对象，且符合需求，直接返回
        return leaveRepository.queryByApplicantId(applicantId);
    }

    public List<Leave> queryLeaveInfosByApprover(String approverId) {
        // 同上，直接返回
        return leaveRepository.queryByApproverId(approverId);

    }
}