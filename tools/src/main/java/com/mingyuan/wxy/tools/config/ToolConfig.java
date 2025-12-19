package com.mingyuan.wxy.tools.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolConfig {

    @Bean
    @ConfigurationProperties(prefix = "com.mingyuan.db")
    public DbConfig oaProxyConfigProperties() {
        return new DbConfig();
    }
}
