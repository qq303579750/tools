package com.mingyuan.wxy.tools.autotest.announce;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;

public interface AdminService {
    Integer addMassive(JSONObject jsonObject, ProfileEnv env);

    JSONObject recall4Admin(JSONObject jsonObject, ProfileEnv admin);

    JSONObject modify4Admin(JSONObject jsonObject, ProfileEnv admin);

    JSONObject detail4Admin(Integer id, ProfileEnv admin);
}
