package com.demo.nacos.consumer.service;

/**
 * @author qiaohui
 * @date 2020/11/24 11:57
 * 分布式管理接口
 */
public interface DistributedLocker {

    /**
     * 获取锁时需要填写的参数
     *
     * @param resourceName
     * @param worker
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception;


    /**
     * 获取锁时候的需要填写参数，同时设置了锁的有效期
     *
     * @param <T>
     * @param resourceName
     * @param worker
     * @param time
     * @throws Exception
     */
    <T> T lock(String resourceName, AquiredLockWorker<T> worker, long time) throws Exception;
}