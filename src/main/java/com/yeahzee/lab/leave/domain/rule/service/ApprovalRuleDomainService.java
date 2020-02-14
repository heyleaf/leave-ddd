package com.yeahzee.lab.leave.domain.rule.service;

import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;
import com.yeahzee.lab.leave.domain.rule.repository.IApprovalRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalRuleDomainService {

    @Autowired
    IApprovalRuleRepository repositoryInterface;

    public int getLeaderMaxLevel(String personType, String leaveType, long duration) {
        ApprovalRule rule = new ApprovalRule();
        rule.setPersonType(personType);
        rule.setLeaveType(leaveType);
        rule.setDuration(duration);
        return repositoryInterface.getLeaderMaxLevel(rule);
    }
}
