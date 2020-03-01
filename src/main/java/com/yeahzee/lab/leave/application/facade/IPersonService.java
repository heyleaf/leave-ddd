package com.yeahzee.lab.leave.application.facade;

import com.yeahzee.lab.leave.application.dto.CreatePersonRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetPersonRequestDTO;
import com.yeahzee.lab.leave.application.dto.GetPersonResponseDTO;

import java.text.ParseException;

public interface IPersonService {
    void create(CreatePersonRequestDTO requestDTO) throws ParseException;
    GetPersonResponseDTO getPerson(GetPersonRequestDTO requestDTO);
}
