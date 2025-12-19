package com.mingyuan.wxy.tools.autotest.util;

import java.util.UUID;

public class UuidUtil {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
        System.out.println(UUID.randomUUID().toString().replace("-", "").substring(0, 10));
    }
}
