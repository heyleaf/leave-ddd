package com.yeahzee.lab.leave.domain.leave.event;

import lombok.Data;

import java.util.Date;


@Data
public class DomainEvent {
    String id;
    String data;
    Date timestamp;
}
