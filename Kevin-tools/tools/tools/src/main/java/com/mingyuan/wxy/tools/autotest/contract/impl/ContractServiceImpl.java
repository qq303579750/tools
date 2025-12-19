package com.mingyuan.wxy.tools.autotest.contract.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import com.mingyuan.wxy.tools.autotest.contract.ContractService;
import com.mingyuan.wxy.tools.exception.CommonException;
import com.mingyuan.wxy.tools.httpclient.HttpClient;
import com.mingyuan.wxy.tools.sign.SignUtilV2;
import com.mingyuan.wxy.tools.sign.SignUtilV3;
import com.mingyuan.wxy.tools.sign.SignUtilsV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    @Override
    public JSONObject generatedV2(Map<String, Object> map, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> generate = new HashMap<>();
            generate.put("timestamp", String.valueOf(timestamp));
            generate.put("signature", SignUtilsV1.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            generate.put("appKey", env.getAppKey());
            generate.put("x-application-code", env.getXApplicationCode());
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/v2/generate", bodyJsonStr, generate);
            return JSONObject.parseObject(generateResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject generatedV3(String json, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            Map<String, String> generate = new HashMap<>();
            generate.put("timestamp", String.valueOf(timestamp));
            generate.put("signature", SignUtilV2.signBodyParams(json, timestamp, env.getAppKey(), env.getAppSecret()));
            generate.put("appKey", env.getAppKey());
            generate.put("x-application-code", env.getXApplicationCode());
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/v3/generate", json, generate);
            return JSONObject.parseObject(generateResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @Override
    public JSONObject generatedV1(Map<String, Object> map, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> generate = new HashMap<>();
            generate.put("timestamp", String.valueOf(timestamp));
            generate.put("signature", SignUtilsV1.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            generate.put("appKey", env.getAppKey());
            generate.put("x-application-code", env.getXApplicationCode());
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/generate", bodyJsonStr, generate);
            return JSONObject.parseObject(generateResult);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject abandon(String code, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<>();
            map.put("code", code);
            map.put("operator", "02224961");
            map.put("reason", "reason");
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> remind = new HashMap<>();
            remind.put("timestamp", String.valueOf(timestamp));
            remind.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            remind.put("appKey", env.getAppKey());
            remind.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/abandon", bodyJsonStr, remind);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject remind(String code, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<>();
            map.put("code", code);
            map.put("operator", "02224961");
            map.put("reason", "reason");
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> remind = new HashMap<>();
            remind.put("timestamp", String.valueOf(timestamp));
            remind.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            remind.put("appKey", env.getAppKey());
            remind.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/remind", bodyJsonStr, remind);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject recall(String code, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<>();
            map.put("code", code);
            map.put("operator", "02224961");
            map.put("reason", "reason");
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> remind = new HashMap<>();
            remind.put("timestamp", String.valueOf(timestamp));
            remind.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            remind.put("appKey", env.getAppKey());
            remind.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/recall", bodyJsonStr, remind);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject modifyV2(Map<String, Object> map, ProfileEnv env) {
        try {
            String name = map.get("name") + "-修改";
            map.put("name", name);
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> remind = new HashMap<>();
            remind.put("timestamp", String.valueOf(timestamp));
            remind.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            remind.put("appKey", env.getAppKey());
            remind.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/v2/modify", bodyJsonStr, remind);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject modifyV3(Map<String, Object> map, ProfileEnv env) {
        try {
            String name = map.get("name") + "-修改";
            map.put("name", name);
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> remind = new HashMap<>();
            remind.put("timestamp", String.valueOf(timestamp));
            remind.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            remind.put("appKey", env.getAppKey());
            remind.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/v3/modify", bodyJsonStr, remind);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject signUrl(String code, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            params.put("operator", "02224961");
            String bodyJsonStr = JSON.toJSONString(params);
            Map<String, String> request = new HashMap<>();
            request.put("timestamp", String.valueOf(timestamp));
            request.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            request.put("appKey", env.getAppKey());
            request.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/getSignUrl", bodyJsonStr, request);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject detail(String code, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            String bodyJsonStr = JSON.toJSONString(params);
            Map<String, String> request = new HashMap<>();
            request.put("timestamp", String.valueOf(timestamp));
            request.put("signature", SignUtilV2.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            request.put("appKey", env.getAppKey());
            request.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doGetJsonByHeaders(env.getUrlPrefix() + "/contract/detail?code=" + code, request);
            JSONObject jsonObject = JSONObject.parseObject(result);
            List<Map> attachments = jsonObject.getJSONObject("data").getList("attachments", Map.class);
            if (attachments.isEmpty()) {
                throw new CommonException("获取附件信息为空！code = " + code);
            }
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CommonException(e.getMessage());
        }
    }

    @Override
    public JSONObject file(Map<String, Object> map, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> request = new HashMap<>();
            request.put("timestamp", String.valueOf(timestamp));
            request.put("signature", SignUtilsV1.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            request.put("appKey", env.getAppKey());
            request.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/file", bodyJsonStr, request);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject manualLaunch(Map<String, Object> map, ProfileEnv env) {
        try {
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> headers = new HashMap<>();
            headers.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/basic/info/manualLaunch", bodyJsonStr, headers);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject remind4Admin(Map<String, Object> map, ProfileEnv env) {
        try {
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> headers = new HashMap<>();
            headers.put("x-application-code", env.getXApplicationCode());
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/basic/info/notify", bodyJsonStr, headers);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject psGen(String param, ProfileEnv env) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("x-application-code", env.getXApplicationCode());
            headers.put("currentUser", "{\"userCode\":\"02224961\",\"userName\":\"兀宵阳\"}");
            String result = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/contract/api/info/ehr/save", param, headers);
            return JSONObject.parseObject(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
