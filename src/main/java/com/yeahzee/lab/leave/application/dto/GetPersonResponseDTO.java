package com.yeahzee.lab.leave.application.dto;

import lombok.Data;

@Data
public class GetPersonResponseDTO {
    String personId;
    String personName;
    String roleId;
    String personType;
    String createTime;
    String lastModifyTime;
    String status;
}
