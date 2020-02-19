package com.yeahzee.lab.leave.infrastructure.repository.rule.persistence;

import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;
import com.yeahzee.lab.leave.domain.rule.repository.IApprovalRuleRepository;
import com.yeahzee.lab.leave.infrastructure.repository.rule.mapper.ApprovalRuleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApprovalRuleRepository implements IApprovalRuleRepository {

    @Autowired
    ApprovalRuleDAO ruleDao;

    @Override
    public ApprovalRule findByRule(ApprovalRule rule) {
        String personType = rule.getPersonType();
        String leaveType = rule.getLeaveType();
        return ruleDao.findRule(personType, leaveType, rule.getDuration());
    }
}
