/**
 * 命令处理逻辑实现
 *
 * 核心职责：
 * 1. 将命令处理逻辑委派给对应聚合内的领域服务（domainService），组装领域服务需要的数据。
 * 2. 一个命令的处理可能跨多个聚合，可在此模块协调领域内的多个聚合。
 * 3.对于需要更新多个聚合的情况，命令处理模块需要保证多聚合更新的一致性。
 */
package com.yeahzee.lab.framework.domain.command.handler;