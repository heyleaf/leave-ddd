package com.yeahzee.lab.leave.domain.command.cmd;

import lombok.Data;

@Data
public class CreatePersonCmd {
    String personId;
    String personName;
    String roleId;
    String personType;
    String createTime;
    String lastModifyTime;
    String status;

    public CreatePersonCmd(String personId, String personName, String roleId,
                           String personType, String createTime, String lastModifyTime,
                           String status) {
        this.personId = personId;
        this.personName = personName;
        this.roleId = roleId;
        this.personType = personType;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
        this.status = status;
    }
}
