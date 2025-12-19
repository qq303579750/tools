//package com.mingyuan.wxy.tools.nacos;
//
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.Executor;
//
//@RestController("nacos")
//@RequestMapping("nacos")
//public class ConfigTest {
//
//    @Autowired
//    private ConfigService nacosConfigService;
//
//    @GetMapping("/get")
//    public String getConfig() {
//        try {
//            String content = nacosConfigService.getConfig("test", "uat", 5000);
//            System.err.println(content);
//            nacosConfigService.addListener("test", "uat", new Listener() {
//                @Override
//                public Executor getExecutor() {
//                    return null;
//                }
//
//                @Override
//                public void receiveConfigInfo(String configInfo) {
//                    System.err.println("recieve:" + configInfo);
//                }
//            });
//            return content;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
