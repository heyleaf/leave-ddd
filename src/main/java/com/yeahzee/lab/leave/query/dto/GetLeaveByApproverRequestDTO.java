package com.yeahzee.lab.leave.query.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetLeaveByApproverRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    String personId;
    String personName;
}
