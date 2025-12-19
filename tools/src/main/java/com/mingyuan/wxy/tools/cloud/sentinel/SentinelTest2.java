package com.mingyuan.wxy.tools.cloud.sentinel;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.Collections;

public class SentinelTest2 {

    public static void main(String[] args) {
        SentinelTest2 test = new SentinelTest2();
        test.doRequest();
    }

    // 静态块 最先执行，且只执行一次，加载带静态方法区
    static {
        initFlowRules();
    }

    // 构造块    ===  类加载的时候会实例化，先于构造方法
    {
        initFlowRules();
    }

    private static final String RESOURCE = "doRequest";

    private static void initFlowRules() {
        // 限流的规则
        FlowRule rule = new FlowRule();
        // 针对qps  =  5
        rule.setCount(5);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 被保护的资源
        rule.setResource(RESOURCE);
        FlowRuleManager.loadRules(Collections.singletonList(rule));
    }

    public void doRequest() {
        while (true) {
            if (SphO.entry(RESOURCE)) {
                SphO.exit();
            }
        }
    }
}
