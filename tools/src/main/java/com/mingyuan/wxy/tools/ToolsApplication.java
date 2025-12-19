package com.mingyuan.wxy.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@ComponentScan("com.mingyuan.wxy")
@Slf4j
@Configuration
public class ToolsApplication {

    public static void main(String[] args)  {
        ConfigurableApplicationContext run = SpringApplication.run(ToolsApplication.class, args);
//        log.info("{}", System.currentTimeMillis());
//
//        ConfigService nacosConfigService = run.getBean(ConfigService.class);
//        nacosConfigService.addListener("test", "uat", new Listener() {
//            @Override
//            public Executor getExecutor() {
//                return null;
//            }
//
//            @Override
//            public void receiveConfigInfo(String configInfo) {
//                System.err.println("recieve:" + configInfo);
//            }
//        });
    }

//    @Bean
//    public ConfigService configService() throws NacosException {
//        Properties properties = new Properties();
//        properties.setProperty("serverAddr", "localhost:8848");
//        properties.setProperty("namespace", "uat");
//        return com.alibaba.nacos.api.config.ConfigFactory.createConfigService(properties);
//    }
}
