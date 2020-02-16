package com.yeahzee.lab.leave.domain.rule;

public interface IApprovalRuleDomainService {
    Integer getLeaderMaxLevel(String personType, String leaveType, Long duration);
}
