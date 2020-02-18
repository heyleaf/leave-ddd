package com.yeahzee.lab.leave.domain.leave.entity.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approver {

    String personId;
    String personName;
    Integer level;
}
