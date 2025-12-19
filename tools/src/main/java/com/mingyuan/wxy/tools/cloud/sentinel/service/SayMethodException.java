package com.mingyuan.wxy.tools.cloud.sentinel.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class SayMethodException {

    public static void handleException(BlockException e){
        e.printStackTrace();
        System.err.println("Occur BlockException" + e.getClass().getCanonicalName());
    }
}
