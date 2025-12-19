package com.mingyuan.wxy.cloud.sentinel.config;

import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.Arrays;

import static com.mingyuan.wxy.cloud.sentinel.constant.CommonConstant.SAY;
import static com.mingyuan.wxy.cloud.sentinel.constant.CommonConstant.TEST_FALLBACK;

public class FlowRuleInitFunc implements InitFunc {
    @Override
    public void init() throws Exception {
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
