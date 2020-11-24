package com.demo.nacos.consumer.service;

/**
 * @author qiaohui
 * @date 2020/11/24 11:56
 */
public interface AquiredLockWorker<T> {

    /**
     * 获取锁之后处理具体业务逻辑的方法
     *
     * @return
     * @throws Exception
     */
    T invokeAfterLockAquire() throws Exception;
}
