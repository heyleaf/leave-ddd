package com.yeahzee.lab.leave.domain.rule.service;

import com.yeahzee.lab.leave.domain.rule.IApprovalRuleDomainService;
import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;
import com.yeahzee.lab.leave.domain.rule.repository.IApprovalRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalRuleDomainService implements IApprovalRuleDomainService {

    @Autowired
    IApprovalRuleRepository repositoryInterface;

    @Override
    public Integer getLeaderMaxLevel(String personType, String leaveType, Long duration) {
        ApprovalRule rule = new ApprovalRule();
        rule.setPersonType(personType);
        rule.setLeaveType(leaveType);
        rule.setDuration(duration);
        return repositoryInterface.getLeaderMaxLevel(rule);
    }
}
