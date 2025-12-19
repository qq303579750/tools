package com.mingyuan.wxy.tools.autotest.announce.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import com.mingyuan.wxy.tools.autotest.announce.AnnounceService;
import com.mingyuan.wxy.tools.base.AppBase;
import com.mingyuan.wxy.tools.httpclient.HttpClient;
import com.mingyuan.wxy.tools.sign.SignUtilsV1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AnnounceServiceImpl implements AnnounceService {

    public JSONObject announceList(Map<String, Object> map, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> generate = new HashMap<>();
            generate.put("timestamp", String.valueOf(timestamp));
            generate.put("signature", SignUtilsV1.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            generate.put("appKey", env.getAppKey());
            generate.put("userCode", "02224961");
            generate.put("x-application-code", env.getXApplicationCode());
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/list", bodyJsonStr, generate);
            log.info("请求返回的结果为：{}", generateResult);
            JSONObject jsonObject = JSONObject.parseObject(generateResult);
            if (jsonObject.isEmpty()) {
                throw new RuntimeException("返回结果为空");
            }
            if (!jsonObject.getBoolean("success")) {
                throw new RuntimeException("success=false");
            }
            JSONObject data = jsonObject.getJSONObject("data");
            List<Map> list = data.getList("list", Map.class);
            for (Map m : list) {
                Integer announceId = AppBase.assertExist(Integer.parseInt(m.get("announceId") + ""), "不存在");
                AppBase.assertExist(Integer.parseInt(m.get("isTop") + ""), "不存在");
                AppBase.assertExist(Integer.parseInt(m.get("viewCount") + ""), "不存在");
                AppBase.assertExist(Integer.parseInt(m.get("readStatus") + ""), "不存在");
                AppBase.assertExist(m.get("staffDeptCode"), "不存在");
                AppBase.assertExist(m.get("staffDeptName"), announceId + " staffDeptName不存在");
                AppBase.assertExist(m.get("announceName") + "", "不存在");
                AppBase.assertExist(m.get("sectionCode") + "", "不存在");
                AppBase.assertExist(m.get("columnCode") + "", "不存在");
                AppBase.assertExist(m.get("columnName") + "", "不存在");
                AppBase.assertExist(m.get("pcUrl") + "", "不存在");
                AppBase.assertExist(m.get("h5Url") + "", "不存在");
                AppBase.assertExist(m.get("approveDate") + "", "不存在");
            }
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    public JSONObject announceV2List(Map<String, Object> map, ProfileEnv env) {
        try {
            long timestamp = System.currentTimeMillis();
            String bodyJsonStr = JSON.toJSONString(map);
            Map<String, String> generate = new HashMap<>();
            generate.put("timestamp", String.valueOf(timestamp));
            generate.put("signature", SignUtilsV1.signBodyParams(bodyJsonStr, timestamp, env.getAppKey(), env.getAppSecret()));
            generate.put("appKey", env.getAppKey());
            generate.put("userCode", "02224961");
            generate.put("x-application-code", env.getXApplicationCode());
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/v2/list", bodyJsonStr, generate);
            log.info("请求返回的结果为：{}", generateResult);
            JSONObject jsonObject = JSONObject.parseObject(generateResult);
            if (jsonObject.isEmpty()) {
                throw new RuntimeException("返回结果为空");
            }
            if (!jsonObject.getBoolean("success")) {
                throw new RuntimeException("success=false");
            }
            JSONObject data = jsonObject.getJSONObject("data");
            List<Map> list = data.getList("list", Map.class);
            for (Map m : list) {
                Integer announceId = AppBase.assertExist(Integer.parseInt(m.get("announceId") + ""), "不存在");
                AppBase.assertExist(Integer.parseInt(m.get("isTop") + ""), "不存在");
                AppBase.assertExist(Integer.parseInt(m.get("viewCount") + ""), "不存在");
                AppBase.assertExist(Integer.parseInt(m.get("readStatus") + ""), "不存在");
                AppBase.assertExist(m.get("staffDeptCode"), "不存在");
                AppBase.assertExist(m.get("staffDeptName"), announceId + " staffDeptName不存在");
                AppBase.assertExist(m.get("announceName") + "", "不存在");
                AppBase.assertExist(m.get("sectionCode") + "", "不存在");
                AppBase.assertExist(m.get("columnCode") + "", "不存在");
                AppBase.assertExist(m.get("columnName") + "", "不存在");
                AppBase.assertExist(m.get("pcUrl") + "", "不存在");
                AppBase.assertExist(m.get("h5Url") + "", "不存在");
                AppBase.assertExist(m.get("approveDate") + "", "不存在");
                AppBase.assertExist(m.get("contentSummary") + "", "不存在");
            }
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
