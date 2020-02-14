package com.yeahzee.lab.leave.infrastructure.repository.leave.mapper;


import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalInfoDao extends JpaRepository<ApprovalInfoPO, String> {

    List<LeavePO> queryByApplicantId(String applicantId);

    List<ApprovalInfoPO> queryByLeaveId(String leaveId);

}
