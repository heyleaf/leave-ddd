package com.yeahzee.lab.leave.infrastructure.repository.rule.persistence;

import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;
import com.yeahzee.lab.leave.domain.rule.repository.IApprovalRuleRepository;
import com.yeahzee.lab.leave.infrastructure.repository.rule.mapper.ApprovalRuleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalRuleRepository implements IApprovalRuleRepository {

    @Autowired
    ApprovalRuleDao ruleDao;

    @Override
    public int getLeaderMaxLevel(ApprovalRule rule) {
        String personType = rule.getPersonType();
        String leaveType = rule.getLeaveType();
        rule = ruleDao.findRule(personType, leaveType, rule.getDuration());
        return rule.getMaxLeaderLevel();
    }
}
