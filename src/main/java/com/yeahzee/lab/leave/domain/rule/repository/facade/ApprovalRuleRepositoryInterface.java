package com.yeahzee.lab.leave.domain.rule.repository.facade;


import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;

public interface ApprovalRuleRepositoryInterface {

    int getLeaderMaxLevel(ApprovalRule rule);
}
