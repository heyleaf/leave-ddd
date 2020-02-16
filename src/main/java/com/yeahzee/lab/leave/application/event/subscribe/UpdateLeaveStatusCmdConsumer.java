package com.yeahzee.lab.leave.application.event.subscribe;

import com.yeahzee.lab.api.dto.LeaveStatusDTO;
import com.yeahzee.lab.leave.application.command.LeaveCommandService;
import com.yeahzee.lab.leave.application.command.cmd.UpdateLeaveStatusCmd;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "update_leave_status_cmd", consumerGroup = "leave-group")
public class UpdateLeaveStatusCmdConsumer implements RocketMQListener {

    @Autowired
    LeaveCommandService leaveCommandService;

    @Override
    public void onMessage(Object message) {
        // TODO message to UpdateLeaveStatusCmd
        UpdateLeaveStatusCmd cmd = (UpdateLeaveStatusCmd)message;
        LeaveStatusDTO leaveStatusDTO = new LeaveStatusDTO();
        leaveStatusDTO.setLeaveId(cmd.getLeaveId());
        leaveStatusDTO.setStatus(cmd.getStatus());
        this.leaveCommandService.updateLeaveStatus(leaveStatusDTO);
    }

}
