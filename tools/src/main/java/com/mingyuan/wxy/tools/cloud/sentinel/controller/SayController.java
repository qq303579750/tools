package com.mingyuan.wxy.tools.cloud.sentinel.controller;

import com.mingyuan.wxy.tools.cloud.sentinel.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SayController {

    private final HelloService helloService;

    @GetMapping(value = "/say")
    public void say() {
        helloService.say();
    }

    @GetMapping(value = "/testFallback")
    public String testFallback() {
        return helloService.testFallback();
    }
}
