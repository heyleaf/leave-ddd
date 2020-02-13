package com.yeahzee.lab.leave.domain.leave.entity;

/**
 * 聚合根实体
 *
 */

import com.yeahzee.lab.leave.domain.leave.entity.valueobject.Status;

/**
 * 请假单信息
 */
public class Leave {
    Integer id;
    String leaveType;
    String startTime;
    String endTime;
    Long duration;
    Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
