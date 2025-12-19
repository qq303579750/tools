package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("locked");
        } finally {
            lock.unlock();
        }
    }
}
