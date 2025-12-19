package com.mingyuan.wxy.tools.autotest.contract;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;

import java.util.Map;

public interface ContractService {
    JSONObject generatedV2(Map<String, Object> map, ProfileEnv env);

    JSONObject generatedV3(String json, ProfileEnv env);

    JSONObject generatedV1(Map<String, Object> map, ProfileEnv env);

    JSONObject abandon(String code, ProfileEnv env);

    JSONObject remind(String code, ProfileEnv env);

    JSONObject recall(String code, ProfileEnv env);

    JSONObject modifyV2(Map<String, Object> map, ProfileEnv env);
    JSONObject modifyV3(Map<String, Object> map, ProfileEnv env);

    JSONObject signUrl(String code, ProfileEnv env);

    JSONObject detail(String code, ProfileEnv rental);

    JSONObject file(Map<String, Object> map, ProfileEnv rental);

    JSONObject manualLaunch(Map<String, Object> map, ProfileEnv portal);

    JSONObject remind4Admin(Map<String, Object> map, ProfileEnv portal);

    JSONObject psGen(String result, ProfileEnv portal);
}
