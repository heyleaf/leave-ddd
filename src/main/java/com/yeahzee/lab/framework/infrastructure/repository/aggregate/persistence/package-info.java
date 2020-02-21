/**
 * 领域层repository接口的实现
 *
 * 主要职责：
 * 1. 实现领域层对应聚合内定义的repository相关接口：查询、新增、更新、删除。
 * 2. 负责重建聚合根，将PO转为领域对象。
 * 3. 负责聚合根的完整创建和更新。
 */
package com.yeahzee.lab.framework.infrastructure.repository.aggregate.persistence;