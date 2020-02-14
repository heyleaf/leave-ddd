package com.yeahzee.lab.leave.domain.rule.repository;


import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;

public interface IApprovalRuleRepository {

    int getLeaderMaxLevel(ApprovalRule rule);
}
