package com.mingyuan.wxy.tools.lock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {

    private ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>()
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public String dateFormat() {
        return threadLocal.get().format(new Date());
    }


    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();
        String dateStr = "2022-02-02 12:22:22";
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                // 1. 解析日期字符串
                Date date = null;
                try {
                    date = demo.threadLocal.get().parse(dateStr);
                    // 2. 将解析后的日期再格式化为字符串
                    String formatted = demo.threadLocal.get().format(date);
                    Date newDate = demo.threadLocal.get().parse(dateStr);
                    System.err.println(formatted);
                    System.err.println(newDate);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }

    }

}
