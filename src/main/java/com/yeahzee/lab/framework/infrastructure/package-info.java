/**
 * 基础设施层
 *
 * 主要职责：
 * 1. 封装数据仓储功能的实现，如数据库数据增删改查。
 * 2. 封装领域事件发布等MQ机制的实现。
 * 3. 在本架构中，基础设施层实现领域层定义的repository相关接口，实现对聚合根的查询和保存。
 *    即返回给领域层的是Entity，而非PO/BO.
 * 4. 应用层可直接调用基础设施层的数据库访问接口（DAO），获取PO/BO数据。
 */
package com.yeahzee.lab.framework.infrastructure;