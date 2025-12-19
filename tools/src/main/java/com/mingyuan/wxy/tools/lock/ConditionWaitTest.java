package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWaitTest implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionWaitTest(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionWaitTest");
        lock.lock();
        try {
            condition.await(); //让当前线程阻塞，Object.wait();
            System.out.println("end - ConditionWaitTest");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
