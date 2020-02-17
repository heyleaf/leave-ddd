package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 审批人信息DTO
 */
@Data
public class ApproverDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    String personId;
    String personName;
}
