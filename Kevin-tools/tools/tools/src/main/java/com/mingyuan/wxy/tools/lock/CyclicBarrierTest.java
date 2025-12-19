package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    private final CyclicBarrier barrier = new CyclicBarrier(3, () -> {
        System.out.println("所有节点准备就绪，开始测试");
    });

    public void startTest() {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
//                prepareNode();
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
//                executeTest();
            }).start();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        cyclicBarrierTest.startTest();
        System.err.println("执行完成");
    }

}
