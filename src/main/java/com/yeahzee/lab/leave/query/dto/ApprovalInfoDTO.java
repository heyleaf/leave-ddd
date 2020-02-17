package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 审批信息DTO
 */
@Data
public class ApprovalInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    String approvalInfoId;
    ApproverDTO approverDTO;
    String msg;
    Long time;
}
