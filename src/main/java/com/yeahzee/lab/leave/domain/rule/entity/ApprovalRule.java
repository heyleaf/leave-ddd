package com.yeahzee.lab.leave.domain.rule.entity;

import lombok.Data;

@Data
public class ApprovalRule {

    String id;
    String personType;
    String leaveType;
    long duration;
    int maxLeaderLevel;
}
