package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionNotifyTest implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionNotifyTest(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin - ConditionNotifyTest");
        lock.lock();
        try {
            condition.signal(); //让当前线程阻塞，Object.wait();
            System.out.println("end - ConditionNotifyTest");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Condition condition1 = lock1.newCondition();
        new Thread(new ConditionWaitTest(lock1, condition1)).start();
        new Thread(new ConditionNotifyTest(lock1, condition1)).start();
    }
}
