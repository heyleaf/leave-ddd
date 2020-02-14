/**
 * CQRS COMMAND
 * 命令服务
 *
 * 1. 为了降低架构复杂性，不使用CommandBus
 * 2. 暂不将DTO再封装为Command，CommandService的输入参数为DTO
 */
package com.yeahzee.lab.leave.application.command;