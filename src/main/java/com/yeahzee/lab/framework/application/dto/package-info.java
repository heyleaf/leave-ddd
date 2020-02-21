/**
 * DTO结构定义模块
 *
 * 主要职责：
 * 1. 定义与外部系统交互的数据结构，只有简单数据字段定义，无业务逻辑。
 * 2. 输入输出参数参见API设计规范：输入为***RequestDTO，输出为***ResponseDTO
 * 3. 每个接口对应两个独立的DTO，DTO不可共用，内部嵌套结构可用Java内部类定义，避免DTO过多。
 */
package com.yeahzee.lab.framework.application.dto;