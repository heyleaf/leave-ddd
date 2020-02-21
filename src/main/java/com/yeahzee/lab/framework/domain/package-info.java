/**
 * 领域层
 *
 * 核心职责：
 *
 * 1. 核心业务处理层，负责处理命令，以及各个聚合的维护。
 * 2. 领域层内部分command模块和各个聚合模块。
 *    command模块调用各个聚合内的domainService完成命另处理。
 *    各聚合模块维护聚合内业务逻辑及数据完整性。
 */
package com.yeahzee.lab.framework.domain;