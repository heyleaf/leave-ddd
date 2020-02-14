package com.yeahzee.lab.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询参数
 */
@Data
public class FindApproverDTO implements Serializable {

    String applicantId;
    int leaderMaxLevel;
}
