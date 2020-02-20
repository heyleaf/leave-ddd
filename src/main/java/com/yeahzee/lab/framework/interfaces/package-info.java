/**
 * 用户接口层
 *
 * 主要职责：
 * 1. 根据交互协议，对外暴露接口。如为http协议，则在controller目录中定义Rest相关接口。
 * 2. 本层无任何业务逻辑，仅做输入数据格式校验，根据协议封装输出数据格式。
 * 3. 本层可以调用application层对应函数来处理请求。
 * 4. 本层的接口名称应当于application对应接口的名称一致，以便阅读和理解。
 */
package com.yeahzee.lab.framework.interfaces;