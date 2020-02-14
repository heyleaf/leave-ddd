package com.yeahzee.lab.leave.infrastructure.repository.leave.mapper;


import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeaveEventPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveEventDao extends JpaRepository<LeaveEventPO, String> {

}
