package com.yeahzee.lab.leave.application.command;

import org.springframework.util.StringUtils;

public class CreateLeaveCommand {
    String leaveType;
    String startTime;
    String endTime;
    long duration;

    /**
     * 实例化一个Command
     * Command校验数据，保证Command的正确
     */
    public CreateLeaveCommand(String leaveType, String startTime, String endTime, long duration) {
        if (null == leaveType
                || StringUtils.isEmpty(startTime)
                || StringUtils.isEmpty(endTime)
                || duration <0 ) {
            throw new IllegalArgumentException();
        }
        this.leaveType = leaveType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return duration;
    }
}
