package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetLeaveByApplicantRequestDTO implements Serializable {
    String personId;
    String personName;
    String leaderId;
    String applicantType;
    String roleLevel;
}
