package com.yeahzee.lab.leave.application.service;

import com.yeahzee.lab.leave.application.dto.CreatePersonRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetPersonRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetPersonResponseDTO;
import com.yeahzee.lab.leave.domain.command.cmd.CreatePersonCmd;
import com.yeahzee.lab.leave.domain.command.handler.PersonCmdHandler;
import com.yeahzee.lab.leave.infrastructure.repository.person.mapper.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class PersonService {
    @Autowired
    PersonCmdHandler personCmdHandler;
    @Autowired
    PersonDAO personDao;

    public void create(CreatePersonRequestDTO requestDTO) throws ParseException {
        // TODO 从其他微服务获取全局ID
        String personId = "personID";
        // 创建命令
        CreatePersonCmd cmd = new CreatePersonCmd(
                personId,requestDTO.getPersonName(),
                requestDTO.getRoleId(), requestDTO.getPersonType(),
                requestDTO.getCreateTime(), requestDTO.getLastModifyTime(),
                requestDTO.getStatus());
        // 处理事件
        personCmdHandler.handle(cmd);
    }


    public GetPersonResponseDTO getPerson(GetPersonRequestDTO requestDTO) {
        // 直接通过dao获取数据，组装
        return null;
    }
}
