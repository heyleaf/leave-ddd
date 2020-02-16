/**
 * 我们架构没有使用CommandBus，直接将RequestDTO传入CommandService进行处理，以简化架构。
 *
 * 但在某些异步编程的场景之下（如批量更新操作），需要将一个命令拆成多条子命令进行执行，因此需在此定义这些子命令。
 */
package com.yeahzee.lab.leave.application.command.cmd;