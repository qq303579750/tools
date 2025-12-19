package com.mingyuan.wxy.tools.autotest.announce;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {

    static Map<String, ProfileEnv> uatEnvs = new HashMap<>();
    static Map<String, ProfileEnv> prodEnvs = new HashMap<>();

    static {

        ProfileEnv ymxdUat = ProfileEnv.builder()
                .appKey("f2dfe5c82d3a4d259f0375603214e42f")
                .appSecret("6f02b9d30d")
                .urlPrefix("http://ann-inter.yto56test.com/announce")
                .xApplicationCode("standard-admin-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("ymxd", ymxdUat);

        ProfileEnv ymxdProd = ProfileEnv.builder()
                .appKey("87c0d2686c004296a3c05510fd02a66f")
                .appSecret("5c14588f69")
                .urlPrefix("https://ann.yto.net.cn:48769/announce")
                .xApplicationCode("standard-admin-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("ymxd-prod", ymxdProd);
    }

    private final AnnounceService announceService;

    @GetMapping("/announce/list/test")
    public String listTest(@RequestParam String profile) {
        String result = "该接口仅用于圆梦行动";
        ProfileEnv uat = ProfileEnv.builder()
                .appKey("2576395dc1074e86b61d480d0ae2caee")
                .appSecret("b0c7c5d4d5")
                .urlPrefix("http://ann-inter.yto56test.com/announce")
                .xApplicationCode("standard-admin-api")
                .userCode("02224961")
                .build();

        ProfileEnv prod = ProfileEnv.builder()
                .appKey("87c0d2686c004296a3c05510fd02a66f")
                .appSecret("5c14588f69")
                .urlPrefix("https://ann.yto.net.cn:48769/announce")
                .xApplicationCode("standard-admin-api")
                .userCode("02224961")
                .build();

        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", 2);
        map.put("pageSize", 10);

        JSONObject jsonObject;

        if (profile.equals("uat")) {
            // 获取公告 uat
            map.put("sectionCode", "sec89V4nWKm");
            jsonObject = announceService.announceList(map, uat);
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("公告出错拉：jsonObject为空");
            }
        } else if (profile.equals("prod")) {
            // 获取公告 prod
            map.put("sectionCode", "Je6ukEKO");
            jsonObject = announceService.announceList(map, prod);
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("新闻出错拉：jsonObject为空");
            }
        }


        if (profile.equals("uat")) {
            // 获取新闻 uat
            map.put("sectionCode", "sec0d8xUJua");
            jsonObject = announceService.announceList(map, uat);
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("新闻出错拉：jsonObject为空");
            }
        } else if (profile.equals("prod")) {
            // 获取新闻 prod
            map.put("sectionCode", "q7uAOtJw");
            jsonObject = announceService.announceList(map, prod);
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("新闻出错拉：jsonObject为空");
            }
        }

        if (profile.equals("uat")) {
            // 获取制度 uat
            map.put("sectionCode", "sec0AScB1Ob");
            jsonObject = announceService.announceList(map, uat);
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("制度出错拉：jsonObject为空");
            }
        } else if (profile.equals("prod")) {
            // 获取制度 prod
            map.put("sectionCode", "FiXGUw5e");
            jsonObject = announceService.announceList(map, prod);
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("制度出错拉：jsonObject为空");
            }
        }
        log.info("验证完成/announce/list，无错误信息！！！！！！！！！！");
        return "验证完成/announce/list，无错误信息";
    }


    @GetMapping("/announce/v2/list/test")
    public String listV2Test(@RequestParam String profile) {
        StringBuilder result = new StringBuilder("该接口仅用于圆梦行动（必须存在contentSummary）。");
        JSONObject jsonObject;
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 2);
        if (profile.equals("uat")) {
            // 获取新闻 uat 圆梦行动参数
            map.put("sectionCode", "sec0d8xUJua");
            map.put("columnCode", "colPaaJ43Be");
            map.put("keyword", "政策");
//            map.put("keyword", "员工风采");
//            map.put("keyword", "新闻");
            jsonObject = announceService.announceV2List(map, uatEnvs.get("ymxd"));
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("uat新闻圆梦行动出错拉：jsonObject为空");
            }
            result.append("验证UAT圆梦行动接口掉用无误。");
        } else if (profile.equals("prod")) {
            // 获取新闻 prod 圆梦行动参数
            map.put("sectionCode", "q7uAOtJw");
            map.put("columnCode", "colwd2IfoTn");
            map.put("keyword", "政策");
//            map.put("keyword", "员工风采");
//            map.put("keyword", "新闻");
            jsonObject = announceService.announceV2List(map, prodEnvs.get("ymxd"));
            if (ObjectUtils.isEmpty(jsonObject)) {
                log.info("prod新闻圆梦行动出错拉：jsonObject为空");
            }
            result.append("验证PROD圆梦行动接口掉用无误。");
        }
        return result.toString();
    }

}
