package com.mingyuan.wxy.tools.autotest.announce.impl;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import com.mingyuan.wxy.tools.autotest.announce.AdminService;
import com.mingyuan.wxy.tools.autotest.util.DateUtil;
import com.mingyuan.wxy.tools.autotest.util.TokenUtil;
import com.mingyuan.wxy.tools.exception.CommonException;
import com.mingyuan.wxy.tools.httpclient.HttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Override
    public Integer addMassive(JSONObject request, ProfileEnv env) {
        try {
            request.put("announceName", "自动-" + request.getString("announceName") + DateUtil.instant2String(Instant.now()));
            String tgc = TokenUtil.genToken(env.getUserCode());
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", tgc);
            header.put("X-Requested-With", "XMLHttpRequest");
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/announceMng/announce/add", JSONObject.toJSONString(request), header);
            log.info("请求返回的结果为：{}", generateResult);
            JSONObject jsonObject = JSONObject.parseObject(generateResult);
            if (jsonObject.isEmpty()) {
                throw new CommonException("返回结果为空");
            }
            if (!jsonObject.getBoolean("success")) {
                throw new CommonException("success=false");
            }
            return jsonObject.getInteger("data");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject recall4Admin(JSONObject request, ProfileEnv env) {
        try {
            JSONObject data = request.getJSONObject("data");
            Map<String, Object> map = new HashMap<>();
            map.put("announceId", data.getInteger("announceId"));
            map.put("announceType", data.getInteger("announceType"));
            map.put("flowId", data.getString("flowId"));
            String tgc = TokenUtil.genToken(env.getUserCode());
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", tgc);
            header.put("X-Requested-With", "XMLHttpRequest");
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/announceMng/cancel", JSONObject.toJSONString(map), header);
            log.info("recall请求返回的结果为：{}", generateResult);
            JSONObject jsonObject = JSONObject.parseObject(generateResult);
            if (jsonObject.isEmpty()) {
                throw new CommonException("返回结果为空");
            }
            if (!jsonObject.getBoolean("success")) {
                throw new CommonException("success=false");
            }
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject modify4Admin(JSONObject request, ProfileEnv env) {
        try {
            request.put("announceName", request.getString("announceName") + DateUtil.instant2String(Instant.now(), "yyyy-MM-dd") + "-修改");
            String tgc = TokenUtil.genToken(env.getUserCode());
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", tgc);
            header.put("X-Requested-With", "XMLHttpRequest");
            String generateResult = HttpClient.doPostJsonByHeaders(env.getUrlPrefix() + "/announceMng/announce/edit", JSONObject.toJSONString(request), header);
            log.info("modify请求返回的结果为：{}", generateResult);
            JSONObject jsonObject = JSONObject.parseObject(generateResult);
            if (jsonObject.isEmpty()) {
                throw new CommonException("返回结果为空");
            }
            if (!jsonObject.getBoolean("success")) {
                throw new CommonException("success=false");
            }
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public JSONObject detail4Admin(Integer id, ProfileEnv env) {
        try {
            String tgc = TokenUtil.genToken(env.getUserCode());
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", tgc);
            header.put("X-Requested-With", "XMLHttpRequest");
            String detailResult = HttpClient.doGetJsonByHeaders(env.getUrlPrefix() + "/announceMng/announceDetail?announceId=" + id, header);
            log.info("detail请求返回的结果为：{}", detailResult);
            JSONObject jsonObject = JSONObject.parseObject(detailResult);
            if (jsonObject.isEmpty()) {
                throw new CommonException("返回结果为空");
            }
            if (!jsonObject.getBoolean("success")) {
                throw new CommonException("success=false");
            }
            return jsonObject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
