package com.mingyuan.wxy.tools.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
