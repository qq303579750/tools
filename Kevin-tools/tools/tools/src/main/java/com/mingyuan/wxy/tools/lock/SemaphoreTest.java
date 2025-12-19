package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    Semaphore semaphore = new Semaphore(5);

    public void processTask() {
        try {
            semaphore.acquire();
            // 执行任务（最多 5 个线程并行）
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

}
