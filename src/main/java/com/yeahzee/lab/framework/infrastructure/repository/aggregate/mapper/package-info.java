/**
 * 数据访问接口DAO定义
 *
 * 主要职责：
 * 1. 在有领域层的服务中，聚合目录下的DAO，只负责聚合根的增加、更新和删除。
 *    同时提供领域层所需的查询接口，以及Query模式下应用层所需的查询接口。
 * 2. 在没有领域层的服务中，即普通MVC架构中，DAO的职责与普通MVC中的一致。
 * 3. 负责领域事件的保存。
 *
 */
package com.yeahzee.lab.framework.infrastructure.repository.aggregate.mapper;