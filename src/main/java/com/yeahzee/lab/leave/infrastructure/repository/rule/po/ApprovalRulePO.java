package com.yeahzee.lab.leave.infrastructure.repository.rule.po;

import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveType;
import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class ApprovalRulePO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String id;
    @Enumerated(EnumType.STRING)
    LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    PersonType personType;
    long duration;
    String applicantRoleId;
}
