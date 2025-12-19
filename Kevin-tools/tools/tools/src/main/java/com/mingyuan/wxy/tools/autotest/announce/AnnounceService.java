package com.mingyuan.wxy.tools.autotest.announce;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;

import java.util.Map;

public interface AnnounceService {

    JSONObject announceList(Map<String, Object> map, ProfileEnv env);


    JSONObject announceV2List(Map<String, Object> map, ProfileEnv env);
}
