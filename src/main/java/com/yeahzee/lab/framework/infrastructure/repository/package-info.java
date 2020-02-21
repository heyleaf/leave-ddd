/**
 * 数据仓储模块
 *
 * 主要职能：
 * 1. 定义与访问数据库接口DAO、与数据库表对应的数据结构PO、以及实现领域层定义的repository相关接口。
 *
 * repository目录结构说明：
 * 1. 对于每个聚合，定义域聚合名称一致的目录，用于处理每个聚合的仓储需求。
 * 2. 对于非聚合的数据访问（如一些查询优化需要的异构表），可以建立相应的单独目录。
 */
package com.yeahzee.lab.framework.infrastructure.repository;