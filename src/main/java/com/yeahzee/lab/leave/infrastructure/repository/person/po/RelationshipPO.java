package com.yeahzee.lab.leave.infrastructure.repository.person.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class RelationshipPO {

    @Id
    String id;
    String personId;
    String leaderId;
}
