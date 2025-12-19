package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo implements Runnable {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    public CountDownLatchDemo(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new CountDownLatchDemo(countDownLatch)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            countDownLatch.countDown();
            System.err.println(Thread.currentThread().getName() + "业务已经处理完成");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
