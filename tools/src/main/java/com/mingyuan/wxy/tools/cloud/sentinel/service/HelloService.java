package com.mingyuan.wxy.tools.cloud.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.mingyuan.wxy.tools.cloud.sentinel.constant.CommonConstant.SAY;
import static com.mingyuan.wxy.tools.cloud.sentinel.constant.CommonConstant.TEST_FALLBACK;

@Service
@Slf4j
public class HelloService {

    // 支持方法和类级别，可以看 @SentinelResource 的支持范围
    @SentinelResource(value = SAY,
            blockHandlerClass = SayMethodException.class,
            blockHandler = "handleException")
    public void say() {
        System.out.println("hello world");
    }

    @SentinelResource(value = TEST_FALLBACK,
            fallback = "fallback")
    public String testFallback() {
        log.info("hello world");
        return "hello, world";
    }

    public String fallback() {
        log.info("触发了fallback方法");
        return "fallback";
    }

}
