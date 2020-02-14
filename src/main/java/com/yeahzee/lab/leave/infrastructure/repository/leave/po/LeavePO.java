package com.yeahzee.lab.leave.infrastructure.repository.leave.po;

import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveType;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Status;
import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Leave")
@Data
public class LeavePO {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    String id;
    String applicantId;
    String applicantName;
    @Enumerated(EnumType.STRING)
    PersonType applicantType;
    String approverId;
    String approverName;
    @Enumerated(EnumType.STRING)
    LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    Status status;
    Date startTime;
    Date endTime;
    long duration;
    @Transient
    List<ApprovalInfoPO> historyApprovalInfoPOList;

    public Leave toLeave() {
        return new Leave();
    }

}
