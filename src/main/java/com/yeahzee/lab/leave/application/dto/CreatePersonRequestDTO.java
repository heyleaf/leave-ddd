package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

@Data
public class CreatePersonRequestDTO {
    String personName;
    String roleId;
    String personType;
    String createTime;
    String lastModifyTime;
    String status;
}
