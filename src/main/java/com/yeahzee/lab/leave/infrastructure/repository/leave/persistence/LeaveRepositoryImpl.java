package com.yeahzee.lab.leave.infrastructure.repository.leave.persistence;

import com.yeahzee.lab.leave.domain.leave.entity.Leave;
import com.yeahzee.lab.leave.domain.leave.entity.valueobject.LeaveBaseInfo;
import com.yeahzee.lab.leave.domain.leave.event.LeaveEvent;
import com.yeahzee.lab.leave.domain.leave.repository.ILeaveRepository;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.ApprovalInfoDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.LeaveBaseInfoDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.LeaveDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.mapper.LeaveEventDAO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.ApprovalInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeaveBaseInfoPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeaveEventPO;
import com.yeahzee.lab.leave.infrastructure.repository.leave.po.LeavePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * persist entity and handle event in repository
 */
@Repository
public class LeaveRepositoryImpl implements ILeaveRepository {

    @Autowired
    LeaveDAO leaveDao;
    @Autowired
    ApprovalInfoDAO approvalInfoDao;
    @Autowired
    LeaveEventDAO leaveEventDao;
    @Autowired
    LeaveBaseInfoDAO leaveBaseInfoDao;
    @Autowired
    LeaveFactory leaveFactory;

    public void save(Leave leave) {
        //persist leave entity
        LeavePO leavePO = leaveFactory.createLeavePO(leave);
        leaveDao.save(leavePO);
       //set leave_id for approvalInfoPO after save leavePO
        leavePO.getHistoryApprovalInfoPOList().stream().forEach(approvalInfoPO -> approvalInfoPO.setLeaveId(leavePO.getId()));
        approvalInfoDao.saveAll(leavePO.getHistoryApprovalInfoPOList());
    }

    public void saveEvent(LeaveEvent leaveEvent){
        LeaveEventPO leaveEventPO = leaveFactory.createLeaveEventPO(leaveEvent);
        leaveEventDao.save(leaveEventPO);
    }

    @Override
    public void saveLeaveBaseInfo(LeaveBaseInfo leaveBaseInfo) {
        LeaveBaseInfoPO leaveBaseInfoPO = leaveFactory.createLeaveBaseInfoPO(leaveBaseInfo);
        leaveBaseInfoDao.save(leaveBaseInfoPO);
    }

    @Override
    public Leave findById(String id) {
        LeavePO leavePO = leaveDao.findById(id)
                .orElseThrow(() -> new RuntimeException("leave not found"));
        return leaveFactory.getLeave(leavePO);
    }

    @Override
    public List<Leave> queryByApplicantId(String applicantId) {
        List<LeavePO> leavePOList = leaveDao.queryByApplicantId(applicantId);
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });

        return leavePOList.stream()
                .map(leavePO -> leaveFactory.getLeave(leavePO))
                .collect(Collectors.toList());

    }

    @Override
    public List<Leave> queryByApproverId(String approverId) {
        List<LeavePO> leavePOList = leaveDao.queryByApproverId(approverId);
        leavePOList.stream()
                .forEach(leavePO -> {
                    List<ApprovalInfoPO> approvalInfoPOList = approvalInfoDao.queryByLeaveId(leavePO.getId());
                    leavePO.setHistoryApprovalInfoPOList(approvalInfoPOList);
                });
        return leavePOList.stream()
                .map(leavePO -> leaveFactory.getLeave(leavePO))
                .collect(Collectors.toList());
    }

}
