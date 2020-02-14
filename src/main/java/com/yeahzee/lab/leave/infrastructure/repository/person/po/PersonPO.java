package com.yeahzee.lab.leave.infrastructure.repository.person.po;

import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonStatus;
import com.yeahzee.lab.leave.domain.person.entity.valueobject.PersonType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "person")
public class PersonPO {

    @Id
    String personId;
    String personName;
    String departmentId;
    @Enumerated(EnumType.STRING)
    PersonType personType;
    @Transient
    String leaderId;
    int roleLevel;
    Date createTime;
    Date lastModifyTime;
    @Enumerated(EnumType.STRING)
    PersonStatus status;
    @OneToOne
    RelationshipPO relationshipPO;
}
