package com.yeahzee.lab.leave.infrastructure.repository.leave.mapper;

import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeaveBaseInfoPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveBaseInfoDao extends JpaRepository<LeaveBaseInfoPO, String> {
}
