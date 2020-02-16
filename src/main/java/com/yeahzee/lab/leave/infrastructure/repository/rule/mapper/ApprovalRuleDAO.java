package com.yeahzee.lab.leave.infrastructure.repository.rule.mapper;

import com.yeahzee.lab.leave.domain.rule.entity.ApprovalRule;
import com.yeahzee.lab.leave.infrastructure.repository.rule.po.ApprovalRulePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApprovalRuleDAO extends JpaRepository<ApprovalRulePO, String> {

    @Query(value = "select r from ApprovalRulePO r where r.applicantRoleId=?1 and r.leaveType=?2 and duration=?3")
    ApprovalRule findRule(String applicantRoleId, String leaveType, long duration);
}
