package com.mingyuan.wxy.cloud.sentinel.config;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import static com.mingyuan.wxy.cloud.sentinel.constant.CommonConstant.SAY;
import static com.mingyuan.wxy.cloud.sentinel.constant.CommonConstant.TEST_FALLBACK;

@Configuration
public class SentinelConfig {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        // 通过sentinel的SPI机制加载了
        //        initFlowRules();
        return new SentinelResourceAspect();
    }

    private static void initFlowRules() {
        // 限流的规则
        FlowRule rule = new FlowRule();
        // 针对qps  =  2
        rule.setCount(2);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 被保护的资源
        rule.setResource(SAY);

        // 限流的规则
        FlowRule rule2 = new FlowRule();
        // 针对qps  =  2
        rule2.setCount(2);
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 被保护的资源
        rule2.setResource(TEST_FALLBACK);
        FlowRuleManager.loadRules(Arrays.asList(rule, rule2));
    }


}
