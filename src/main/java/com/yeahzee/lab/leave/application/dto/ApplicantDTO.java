package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 申请者信息DTO
 */
@Data
public class ApplicantDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    String personId;
    String personName;
    String leaderId;
    String applicantType;
    String roleLevel;
}