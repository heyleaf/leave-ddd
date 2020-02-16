/**
 * 领域层数据操作接口实现模块
 *
 * 1. 实现领域层定义的数据操作接口。
 * 2. 返回给领域层的是领域对象。
 * 3. 对于那些有关联的领域对象（如聚合根），再数据库表中存的是关联的ID，但领域对象中定是引用关联的对象。
 *    此时，需要将新建关联对象，将ID设在关联对象中。
 */

package com.yeahzee.lab.leave.infrastructure.repository.leave.persistence;