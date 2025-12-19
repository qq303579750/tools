package com.mingyuan.wxy.tools.cloud.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.Collections;

public class SentinelTest {

    public static void main(String[] args) {
        SentinelTest test = new SentinelTest();
        test.doRequest();
    }

    static {
        initFlowRules();
    }

    private static final String RESOURCE = "doRequest";

    private static void initFlowRules() {
        // 限流的规则
        FlowRule rule = new FlowRule();
        // 针对qps  =  5
        rule.setCount(5);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 被保护的资源
        rule.setResource(RESOURCE);
        FlowRuleManager.loadRules(Collections.singletonList(rule));
    }

    public void doRequest() {
        while (true) {
            try (Entry entry = SphU.entry(RESOURCE)) {
                System.err.println(Thread.currentThread().getName() + ":执行业务逻辑！");
            } catch (BlockException e) {
                // 如果被限流，就会抛出异常 BlockException
                e.printStackTrace();
            }
        }
    }
}
