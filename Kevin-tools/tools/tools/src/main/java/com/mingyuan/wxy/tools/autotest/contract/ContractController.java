package com.mingyuan.wxy.tools.autotest.contract;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import com.mingyuan.wxy.tools.exception.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.mingyuan.wxy.tools.autotest.util.DateUtil.STANDARD_FORMAT;
import static com.mingyuan.wxy.tools.autotest.util.DateUtil.currMonthFirstDay;
import static com.mingyuan.wxy.tools.autotest.util.DateUtil.instant2String;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContractController {

    private final ContractService contractService;
    static Map<String, ProfileEnv> uatEnvs = new HashMap<>();
    static Map<String, ProfileEnv> prodEnvs = new HashMap<>();

    static {
        // 离职证明uat
        ProfileEnv archiveUat = ProfileEnv.builder()
                .appKey("f7c31cfe7efe4612bdcb230ed1c00d0f")
                .appSecret("b1d9223b6f")
                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("archive", archiveUat);
        // 离职证明prod
        ProfileEnv archiveProd = ProfileEnv.builder()
                .appKey("d57195b37b8941d7ab421781a2011953")
                .appSecret("8f4ce53cxy")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("archive", archiveProd);

        // 无人车uat
        ProfileEnv networkManageUat = ProfileEnv.builder()
                .appKey("65f1be08444b439887ef6c51iokoji89")
                .appSecret("21f7uijko6")
                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("networkManage", networkManageUat);
        // 无人车prod
        ProfileEnv networkManageProd = ProfileEnv.builder()
                .appKey("d57195b37b8941d7ab421781a2011953")
                .appSecret("8f4ce53cxy")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("networkManage", networkManageProd);

        // 离职证明uat
        ProfileEnv dimissionUat = ProfileEnv.builder()
                .appKey("eb18911885cc4ed2bba551ad6a3320ca")
                .appSecret("e6927f976c")
                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("dimission", dimissionUat);
        // 离职证明prod
        ProfileEnv dimissionProd = ProfileEnv.builder()
                .appKey("d57195b37b8941d7ab421781a2011953")
                .appSecret("8f4ce53cxy")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("dimission", dimissionProd);

        // 运盟uat
        ProfileEnv yonmenUat = ProfileEnv.builder()
                .appKey("65f1be08444b439887ef6c51de3b3c86")
                .appSecret("21f7b48187")
                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("yonmen", yonmenUat);
        // 运盟prod
        ProfileEnv yonmenProd = ProfileEnv.builder()
                .appKey("ea2de9dc072646d6900659ed82889d44")
                .appSecret("d6d739865f")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("yonmen", yonmenProd);

        // 驿站主控uat
        ProfileEnv stationUat = ProfileEnv.builder()
                .appKey("a8119ca029374edd92db6b27f8039769")
                .appSecret("b87af5e032")
                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("station", stationUat);
        // 驿站主控prod
        ProfileEnv stationProd = ProfileEnv.builder()
                .appKey("299be3eda010458b9fa56eaa364d0b35")
                .appSecret("205c6b1481")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("station", stationProd);

        // 租赁uat
        ProfileEnv rentalUat = ProfileEnv.builder()
                .appKey("e7bca8057b4041eeb2983f2825e45187")
                .appSecret("2536a4fce8")
                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("rental", rentalUat);
        // 租赁prod
        ProfileEnv rentalProd = ProfileEnv.builder()
                .appKey("1784bf2cf752467c93d79928dcf1c031")
                .appSecret("0cb0973e25")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("rental", rentalProd);

        // 新人资uat
        ProfileEnv ehrUat = ProfileEnv.builder()
                .appKey("a0e26c1a4b9e444185a8b9d159febf24")
                .appSecret("252fkbhj21")
//                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .urlPrefix("http://localhost:8020/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("ehr", ehrUat);
        // 新人资prod
        ProfileEnv ehrProd = ProfileEnv.builder()
                .appKey("2015d83e63624ccbaafea63a9b1ca6d2")
                .appSecret("78f71e5e9e")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("ehr", ehrProd);

        // 安全保密协议uat
        ProfileEnv secretUat = ProfileEnv.builder()
                .appKey("b10e5776998df0ddded26e8029e1d277")
                .appSecret("90b5dd2e31")
//                .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                .urlPrefix("http://localhost:8020/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("secret", secretUat);
        // 新人资prod
        ProfileEnv secretProd = ProfileEnv.builder()
                .appKey("2015d83e63624ccbaafea63a9b1ca6d2")
                .appSecret("78f71e5e9e")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("secret", secretProd);


        // 网点金刚系统uat
        ProfileEnv portalUat = ProfileEnv.builder()
                .appKey("b10e5776998df0ddded26e8029e1d277")
                .appSecret("90b5dd2e31")
                .urlPrefix("http://10.130.15.156:8001")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("portal", portalUat);
        // 新人资prod
        ProfileEnv portalProd = ProfileEnv.builder()
                .appKey("2015d83e63624ccbaafea63a9b1ca6d2")
                .appSecret("78f71e5e9e")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("portal", portalProd);

        // 网点金刚系统uat
        ProfileEnv primaryDataUat = ProfileEnv.builder()
                .appKey("b10e5776998df0ddded26e8029e1d277")
                .appSecret("90b5dd2e31")
                .urlPrefix("http://10.130.15.156:8001")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("primaryData", primaryDataUat);
        // 新人资prod
        ProfileEnv primaryDataProd = ProfileEnv.builder()
                .appKey("b10e5776998df0ddded26e8029e1d277")
                .appSecret("78f71e5e9e")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                .xApplicationCode("ann-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("portal", portalProd);
    }

    // 离职证明
    @GetMapping("/contract/certification/test")
    public String certGen(@RequestParam String profile) {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "王丹玥的离职证明");
        map.put("firstParty", "圆通速递（北京）有限公司");
        map.put("secondParty", "王丹");
        map.put("secondPartyType", "PERSONAL");
        map.put("secondPartyMobile", "15929601664");
        map.put("startDate", "2024-12-23");
        map.put("endDate", "2025-12-23");
        Map<String, Object> extensionMap = new HashMap<>();
        extensionMap.put("userCode", "02816809");
        extensionMap.put("secondParty", "王丹玥");
        extensionMap.put("firstParty", "圆通速递（北京）有限公司");
        extensionMap.put("idCard", "610122199909090909");
        extensionMap.put("resumeDepartment", "技术平台部");
        extensionMap.put("leaveReason", "家中有事");
        extensionMap.put("lastDay", instant2String(Instant.now(), "yyyy年MM月dd日"));
        extensionMap.put("applyDate", instant2String(Instant.now(), "yyyy年MM月dd日"));
        map.put("extension", extensionMap);
        map.put("templateCodes", Collections.singletonList("certification"));
        map.put("businessClassify", "certification");
        map.put("creatorCode", "02224961");
        map.put("creatorName", "兀宵阳");
        map.put("autoStamp", false);

        ProfileEnv dimission = null;
        if ("uat".equals(profile)) {
            dimission = uatEnvs.get("dimission");
        } else if ("prod".equals(profile)) {
            dimission = prodEnvs.get("dimission");
        }
        JSONObject generated = contractService.generatedV2(map, dimission);
        result += "发起离职证明成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起离职证明成功，返回消息为：{}", JSON.toJSONString(generated));
        String code = String.valueOf(generated.get("data"));
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            throw new CommonException(e.getMessage());
        }
        JSONObject abandoned = contractService.abandon(code, dimission);
        log.info("作废成功，返回消息为：{}", JSON.toJSONString(abandoned));
        result += "作废成功，返回消息为：{}" + JSON.toJSONString(abandoned);
        return result;
    }

    // 辞职信
    @GetMapping("/contract/termination/test")
    public String terminationGen(@RequestParam String profile) throws Exception {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "王丹的辞职信" + instant2String(Instant.now(), "yyyy年MM月dd日"));
        map.put("firstParty", "圆通速递（北京）有限公司");
        map.put("firstPartyMobile", "17791948827");
        map.put("secondParty", "王丹");
        map.put("secondPartyType", "PERSONAL");
        map.put("secondPartyMobile", "15929601664");
        map.put("startDate", "2024-12-23 03:01:27");
        map.put("endDate", "2025-12-23 03:01:27");
        Map<String, Object> extensionMap = new HashMap<>();
        extensionMap.put("firstParty", "02816809");
        extensionMap.put("secondParty", "圆通速递（北京）有限公司");
        extensionMap.put("idCard", "610122199909090909");
        extensionMap.put("resumeDepartment", "西安技术平台部");
        extensionMap.put("jobDesc", "测试工程师");
        extensionMap.put("leaveReason", "家中有事");
        extensionMap.put("lastDay", instant2String(Instant.now(), "yyyy/MM/dd"));
        extensionMap.put("applyDate", instant2String(Instant.now(), "yyyy/MM/dd"));
        map.put("extension", extensionMap);
        map.put("templateCodes", Collections.singletonList("resignation"));
        map.put("businessClassify", "resignation");
        map.put("creatorCode", "02224961");
        map.put("creatorName", "兀宵阳");
        map.put("autoStamp", false);

        ProfileEnv dimission = null;
        if ("uat".equals(profile)) {
            dimission = uatEnvs.get("dimission");
        } else if ("prod".equals(profile)) {
            dimission = prodEnvs.get("dimission");
        }
        JSONObject generated = contractService.generatedV2(map, dimission);
        result += "发起辞职信成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起辞职信成功，返回消息为：{}", JSON.toJSONString(generated));

        String code = String.valueOf(generated.get("data"));
        map.put("code", code);
        Thread.sleep(30 * 1000);
        JSONObject reminded = contractService.remind(code, dimission);
        result += "辞职信催签成功，返回消息为：" + reminded.toJSONString() + " <br>";
        log.info("辞职信催签成功，返回消息为：{}", JSON.toJSONString(reminded));

        JSONObject recalled = contractService.recall(code, dimission);
        result += "辞职信撤回成功，返回消息为：" + recalled.toJSONString() + " <br>";
        log.info("辞职信撤回成功，返回消息为：{}", JSON.toJSONString(recalled));

        JSONObject modified = contractService.modifyV2(map, dimission);
        result += "辞职信修改成功，返回消息为：" + modified.toJSONString() + " <br>";
        System.err.println("修改辞职信成功，返回消息为：" + JSON.toJSONString(modified));

        Thread.sleep(30 * 1000);
        JSONObject signUrl = contractService.signUrl(code, dimission);
        System.err.println("获取签署地址成功，返回消息为：" + JSON.toJSONString(signUrl));
        return result;
    }

    @GetMapping("/contract/abandon/test")
    public String abandon(@RequestParam String code, @RequestParam String profile, @RequestParam String channel) {
        String result = "";
        ProfileEnv env = null;
        if ("uat".equals(profile)) {
            env = uatEnvs.get(channel);
        } else if ("prod".equals(profile)) {
            env = prodEnvs.get(channel);
        }
        JSONObject abandoned = contractService.abandon(code, env);
        log.info("作废成功，返回消息为： {}", JSON.toJSONString(abandoned));
        result += "作废成功，返回消息为：" + JSON.toJSONString(abandoned);
        return result;
    }

    // 运盟确认函
    @GetMapping("/contract/yonmen/test")
    public String yonmenGen(@RequestParam String profile) throws Exception {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "兀宵阳发起的合同" + instant2String(Instant.now(), "yyyy-MM-dd"));
        map.put("firstParty", "杭州杰伦货运有限公司");
        map.put("firstPartyMobile", "17791948827");
        map.put("secondParty", "融安县安达速递有限责任公司");
        map.put("secondPartyType", "COMPANY");
        map.put("secondPartyLegal", "王丹");
        map.put("secondPartyMobile", "15949561766");
        map.put("startDate", "2024-12-23 03:01:27");
        map.put("endDate", "2025-12-23 03:01:27");
        Map<String, Object> extensionMap = new HashMap<>();
        extensionMap.put("firstParty", "杭州杰伦货运有限公司");
        extensionMap.put("secondParty", "融安县安达速递有限责任公司");
        extensionMap.put("billMonth", "2023-11-11");
        extensionMap.put("costType", "汽运费");
        extensionMap.put("invoiceValueSum", "1,100,640,555.83");
        extensionMap.put("totalPayCarSum", "1,170,527.09");
        extensionMap.put("totalActualPaySum", "1,099,470,028.74");
        map.put("extension", extensionMap);
        map.put("templateCodes", Collections.singletonList("yonmen001"));
        map.put("businessClassify", "commitment");
        map.put("creatorCode", "admin");
        map.put("creatorName", "admin");

        ProfileEnv dimission = null;
        if ("uat".equals(profile)) {
            dimission = uatEnvs.get("yonmen");
        } else if ("prod".equals(profile)) {
            dimission = prodEnvs.get("yonmen");
        }
        JSONObject generated = contractService.generatedV2(map, dimission);
        result += "发起运盟确认函成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起运盟确认函成功，返回消息为：{}", JSON.toJSONString(generated));

        String code = String.valueOf(generated.get("data"));
        map.put("code", code);
        Thread.sleep(30 * 1000);
        JSONObject reminded = contractService.remind(code, dimission);
        result += "运盟确认函催签成功，返回消息为：" + reminded.toJSONString() + " <br>";
        log.info("运盟确认函催签成功，返回消息为：{}", JSON.toJSONString(reminded));

        JSONObject recalled = contractService.recall(code, dimission);
        result += "运盟确认函撤回成功，返回消息为：" + recalled.toJSONString() + " <br>";
        log.info("运盟确认函撤回成功，返回消息为：{}", JSON.toJSONString(recalled));

        JSONObject modified = contractService.modifyV2(map, dimission);
        result += "运盟确认函修改成功，返回消息为：" + modified.toJSONString() + " <br>";
        log.info("修改运盟确认函成功，返回消息为：{}", JSON.toJSONString(modified));
        return result;
    }

    // 驿站主控协议
    @GetMapping("/contract/station/test")
    public String stationGen(@RequestParam String profile) throws Exception {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "王丹的主控协议" + instant2String(Instant.now(), "yyyy-MM-dd"));
        map.put("firstParty", "飞雪连天有限公司");
        map.put("firstPartyMobile", "17791948827");
        map.put("secondParty", "王丹");
        map.put("secondPartyType", "PERSONAL");
        map.put("secondPartyMobile", "15929601664");
        map.put("startDate", "2024-12-23 03:01:27");
        map.put("endDate", "2025-12-23 03:01:27");
        Map<String, Object> extensionMap = new HashMap<>();
        extensionMap.put("firstParty", "妈妈驿站");
        extensionMap.put("secondParty", "王丹");
        extensionMap.put("startDate", "2024年10月11日");
        extensionMap.put("endDate", "2025年10月11日");
        extensionMap.put("provName", "陕西省");
        extensionMap.put("cityName", "西安市");
        extensionMap.put("areaName", "雁塔区");
        extensionMap.put("townName", "天谷八路天谷八路天谷八路天谷八路天谷八路天谷八路天谷八路天谷八路");
        extensionMap.put("expressCost", "500,000,000.00");
        extensionMap.put("earnestMoney", "100,000,000.00");
        map.put("extension", extensionMap);
        map.put("templateCodes", Collections.singletonList("control001"));
        map.put("businessClassify", "control");
        map.put("creatorCode", "02224961");
        map.put("creatorName", "兀宵阳");

        ProfileEnv dimission = null;
        if ("uat".equals(profile)) {
            dimission = uatEnvs.get("station");
        } else if ("prod".equals(profile)) {
            dimission = prodEnvs.get("station");
        }
        JSONObject generated = contractService.generatedV2(map, dimission);
        result += "发起驿站主控协议成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起驿站主控协议成功，返回消息为：{}", JSON.toJSONString(generated));

        String code = String.valueOf(generated.get("data"));
        map.put("code", code);
        Thread.sleep(30 * 1000);
        JSONObject reminded = contractService.remind(code, dimission);
        result += "驿站主控协议催签成功，返回消息为：" + reminded.toJSONString() + " <br>";
        log.info("驿站主控协议催签成功，返回消息为：{}", JSON.toJSONString(reminded));

        JSONObject recalled = contractService.recall(code, dimission);
        result += "驿站主控协议撤回成功，返回消息为：" + recalled.toJSONString() + " <br>";
        log.info("驿站主控协议撤回成功，返回消息为：{}", JSON.toJSONString(recalled));

        JSONObject modified = contractService.modifyV2(map, dimission);
        result += "驿站主控协议修改成功，返回消息为：" + modified.toJSONString() + " <br>";
        log.info("修改驿站主控协议成功，返回消息为：{}", JSON.toJSONString(modified));
        return result;
    }


    // 租赁合同
    @GetMapping("/contract/rental/test")
    public String rentalGen(@RequestParam String profile) throws Exception {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("autoStamp", true);
        map.put("creatorCode", "02224961");
        map.put("creatorName", "兀宵阳");
        map.put("businessClassify", "leases");
        map.put("startDate", "2024-08-31T16:00:00Z");
        map.put("endDate", "2025-08-30T16:00:00Z");
        map.put("templateCodes", Collections.singletonList("rentalSystemleases001"));
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "广东圆通速递有限公司签署的合同" + instant2String(Instant.now(), "yyyy-MM-dd"));
        map.put("firstParty", "广州市圆粤物流有限公司");
        map.put("firstPartyMobile", "17791948827");
        map.put("secondParty", "广东圆通速递有限公司");
        map.put("secondPartyType", "COMPANY");
        String str = "{\"endDate\":\"2026-06-30\",\"signNumber\":\"17791948827\",\"damages\":\"0\",\"secondAddress\":\"深圳市龙岗区吉华街道水径社区吉华路288号金龙羽工业园2号办公楼101\",\"restorationAmount\":\"null\",\"signDate\":\"2024-12-01\",\"initialCost\":\"null\",\"capacity\":\"1000\",\"totalContractAmount\":\"475000\",\"security\":\"a\",\"rentCategory\":\"等额(未变更)\",\"price\":\"null\",\"property\":\"操作场地\",\"firstParty\":\"深圳市华南建材供应链有限公司\",\"term\":\"无\",\"payment\":\"null\",\"firstCode\":\"91440300MA5FF7A043\",\"subBranch\":\"中国工商银行股份有限公司深圳凤凰城支行\",\"paymentTime\":\"null\",\"totalLeasedArea\":\"1000\",\"restoration\":\"否\",\"secondName\":\"周青淼\",\"bankAccount\":\"4000000209000016991\",\"termination\":\"否\",\"cost\":\"475000\",\"secondPartyID\":\"YT0173\",\"secondParty\":\"深圳圆通速递有限公司\",\"renewalOfLease\":\"否\",\"change\":\"是\",\"esign\":\"否\",\"contract\":\"否\",\"purchase\":\"否\",\"demand\":\"10\",\"changeTime\":\"2024-12-01\",\"beginDate\":\"2024-12-01\",\"propertyArea\":\"操作场地1000平方米\",\"breakPayment\":\"null\",\"firstPartyID\":\"GX2020393\",\"managementFee\":\"0.00\",\"changeContent\":\"注：电费单价经双方谈判由原来1.5元下调至1.3元，办公楼11层自2023年9月1日至2024年11月期间仍由房东使用，应相应抵减租金，经协商最终认可该期间租金不再单独抵减。增加场地及办公楼内设备租赁条款，双方评估总价值92.6万（含税），自2024年12月1日起租赁给深圳中心，月租金2.5万元（含税），该部分租赁期限保持与主合同一致，租期19个月，后结合惠阳中心建设进度，决定是否续租；财务要求设\",\"settleCycle\":\"1\",\"location\":\"广东省深圳市龙岗区吉华街道288号金龙羽工业园\",\"firstAddress\":\"深圳市光明区玉塘街道玉律社区第六工业区南环大道1013-82\",\"secondCode\":\"91440300MAD5UCAM5L\",\"customer\":\"深圳市华南建材供应链有限公司\"}";
        Map<String, Object> extensionMap = JSON.parseObject(str, Map.class);
        map.put("extension", extensionMap);


        ProfileEnv rental = null;
        if ("uat".equals(profile)) {
            rental = uatEnvs.get("rental");
        } else if ("prod".equals(profile)) {
            rental = prodEnvs.get("rental");
        }
        JSONObject generated = contractService.generatedV1(map, rental);
        result += "发起租赁合同成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起租赁合同成功，返回消息为：{}", JSON.toJSONString(generated));

        String code = String.valueOf(generated.get("data"));
        map.put("code", code);
        Thread.sleep(20 * 1000);
        JSONObject detail = contractService.detail(code, rental);
        result += "获取租赁合同详情，返回消息为：" + detail.toJSONString() + " <br>";
        log.info("获取租赁合同详情，返回消息为：{}", JSON.toJSONString(detail));

        JSONObject abandoned = contractService.abandon(code, rental);
        result += "租赁合同未归档废弃成功，返回消息为：" + abandoned.toJSONString() + " <br>";
        log.info("租赁合同未归档废弃成功，返回消息为：{}", JSON.toJSONString(abandoned));

        JSONObject generated2 = contractService.generatedV1(map, rental);
        result += "二次发起租赁合同成功，返回消息为：" + generated2.toJSONString() + " <br>";
        log.info("二次发起租赁合同成功，返回消息为：{}", JSON.toJSONString(generated2));
        String code2 = String.valueOf(generated2.get("data"));
        Thread.sleep(20 * 1000);
        JSONObject detail2 = contractService.detail(code2, rental);
        result += "二次获取租赁合同详情，返回消息为：" + detail2.toJSONString() + " <br>";
        log.info("二次获取租赁合同详情，返回消息为：{}", JSON.toJSONString(detail2));

        Map<String, Object> map2 = new HashMap<>();
        map2.put("code", code2);
        map2.put("operator", "02205474");
        List<Map<String, Object>> files = new ArrayList<>();
        Map<String, Object> file1 = new HashMap<>();
        file1.put("fileName", "圆通标准体系E-R图.pdf");
        file1.put("filePath", "i+7uyWbCS9cLvvC1EumxPuYm+alKA4kceyVHToWqujKRhVsrwkinpSjxTkyVvtlg0RDzLZAnZGttM0Vl03G1jQT7xrBvxgsmdS27cdY3uXM=");
        Map<String, Object> file2 = new HashMap<>();
        file2.put("fileName", "保密协议书.pdf");
        file2.put("filePath", "i+7uyWbCS9cLvvC1EumxPuYm+alKA4kceyVHToWqujK5yXRfBTnffLkXKXSsd7v6Jf1v+7oykNPJdqp5n5HCBNBP1vZ9cVlfNDwO2H1C50M=");
        files.add(file1);
        files.add(file2);
        map2.put("files", files);
        JSONObject filed = contractService.file(map2, rental);
        result += "租赁合同归档成功，返回消息为：" + filed.toJSONString() + " <br>";
        log.info("租赁合同归档成功，返回消息为：{}", JSON.toJSONString(filed));

        JSONObject detail3 = contractService.detail(code2, rental);
        result += "归档后获取租赁合同详情，返回消息为：" + detail3.toJSONString() + " <br>";
        log.info("归档后租赁合同详情，返回消息为：{}", JSON.toJSONString(detail3));
        return result;
    }

    // 新人资协议
    @GetMapping("/contract/ehr/test")
    public String ehrGen(@RequestParam String profile) throws Exception {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "王丹的入职合同");
        map.put("firstParty", "圆通速递（北京）有限公司");
        map.put("firstPartyMobile", "17791948827");
        map.put("secondParty", "王丹");
        map.put("secondPartyType", "PERSONAL");
        map.put("secondPartyLegal", "王丹");
        map.put("secondPartyMobile", "15929601664");
        map.put("startDate", "2024-12-23 03:01:27");
        map.put("endDate", "2025-12-23 03:01:27");
        map.put("templateCodes", Arrays.asList("100100091", "100100010", "100100104"));
        map.put("businessClassify", "100");
        map.put("creatorCode", "02224961");

        Map<String, Object> extensionMap = new HashMap<>();
        extensionMap.put("firstParty", "圆通速递（北京）有限公司");
        extensionMap.put("relationCompany", "f0044");
        extensionMap.put("secondPartyJob", "f0043");
        extensionMap.put("notSignOthers", "f0042");
        extensionMap.put("registerPlace", "f0005");
        extensionMap.put("operationJob", true);
        extensionMap.put("technologyJob", true);
        extensionMap.put("secondParty", "王丹");
        extensionMap.put("professionalJob", true);
        extensionMap.put("managementJob", true);
        extensionMap.put("firstPartyAddress", "f0002");
        extensionMap.put("mobile", "15929601664");
        extensionMap.put("currentResidenceDetail", "f0008");
        extensionMap.put("currentResidence", "f0007");
        extensionMap.put("vacationChoice", "f0029");
        extensionMap.put("registerPlaceDetail", "f0006");
        extensionMap.put("workPlace", "f0028");
        extensionMap.put("vacationYear", "f0030");
        extensionMap.put("firstEnd", "2024-01-11 00:00:00");
        extensionMap.put("salaryChoice", "f0034");
        extensionMap.put("firstStart", "2024-01-11 00:00:00");
        extensionMap.put("trialStart", "2024-01-11 00:00:00");
        extensionMap.put("trialEnd", "2024-01-11 00:00:00");
        extensionMap.put("secondStart", "2024-01-11 00:00:00");
        extensionMap.put("thirdStart", "2024-01-11 00:00:00");
        extensionMap.put("thirdEnd", "2024-01-11 00:00:00");
        extensionMap.put("vacationMonth", "f0033");
        extensionMap.put("contractPeriodChoice", "f0010");
        extensionMap.put("vacationQuarter", "f0032");
        extensionMap.put("vacationHalfAYear", "f0031");
        extensionMap.put("thirdEndFlag", "f0016");
        extensionMap.put("trialSalary", "1000.0");
        extensionMap.put("salaryOther", "f0037");
        extensionMap.put("salaryCount", "f0036");
        extensionMap.put("salaryHourly", "f0035");
        extensionMap.put("trialChoice", "f0017");
        extensionMap.put("payDays", "f0039");
        map.put("extension", extensionMap);

        ProfileEnv ehr = null;
        if ("uat".equals(profile)) {
            ehr = uatEnvs.get("ehr");
        } else if ("prod".equals(profile)) {
            ehr = prodEnvs.get("ehr");
        }
        JSONObject generated = contractService.generatedV2(map, ehr);
        result += "发起新人资合同成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起新人资合同成功，返回消息为：{}", JSON.toJSONString(generated));

        String code = String.valueOf(generated.get("data"));
        map.put("code", code);
//        Thread.sleep(30 * 1000);
//        JSONObject reminded = contractService.remind(code, ehr);
//        result += "新人资合同催签成功，返回消息为：" + reminded.toJSONString() + " <br>";
//        log.info("新人资合同催签成功，返回消息为：{}", JSON.toJSONString(reminded));
//
        JSONObject recalled = contractService.recall(code, ehr);
        result += "新人资合同撤回成功，返回消息为：" + recalled.toJSONString() + " <br>";
        log.info("新人资合同撤回成功，返回消息为：{}", JSON.toJSONString(recalled));

        JSONObject modified = contractService.modifyV2(map, ehr);
        result += "新人资合同修改成功，返回消息为：" + modified.toJSONString() + " <br>";
        log.info("新人资合同修改成功，返回消息为：{}", JSON.toJSONString(modified));
        return result;
    }


    // 安全保密协议
    @GetMapping("/contract/secret/test")
    public String secretGen(@RequestParam String profile) throws Exception {
        String result = "";
        Map<String, Object> map = new HashMap<>();
        map.put("businessCode", UUID.randomUUID().toString());
        map.put("name", "王丹的安全保密协议");
        map.put("firstParty", "圆通速递（北京）有限公司");
        map.put("secondParty", "王丹");
        map.put("secondPartyType", "PERSONAL");
        map.put("startDate", "2025-02-10 03:01:27");
        map.put("endDate", "2099-12-23 03:01:27");
        map.put("templateCodes", Collections.singletonList("secret001"));
        map.put("businessClassify", "secret");
        map.put("creatorCode", "02205474");
        map.put("creatorName", "王丹");

        Map<String, Object> extensionMap = new HashMap<>();
        extensionMap.put("idCard", "61012219900919151X");
        map.put("extension", extensionMap);

        ProfileEnv secret = null;
        if ("uat".equals(profile)) {
            secret = uatEnvs.get("secret");
        } else if ("prod".equals(profile)) {
            secret = prodEnvs.get("secret");
        }
        JSONObject generated = contractService.generatedV2(map, secret);
        result += "发起安全保密协议成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起安全保密协议成功，返回消息为：{}", JSON.toJSONString(generated));

        return result;
    }

    // 网点金刚
    @GetMapping("/contract/portal/test")
    public String portalGen(@RequestParam String profile) throws Exception {
        String result = "{\"createTimeStart\":\"2025-02-01 00:00:00\",\"createTimeEnd\":\"2025-02-11 10:28:56\",\"stationCode\":\"772012\"}";
        Map<String, Object> map = new HashMap<>();
        map.put("createTimeStart", currMonthFirstDay(Instant.now(), STANDARD_FORMAT));
        map.put("createTimeEnd", instant2String(Instant.now()));
        map.put("stationCode", "772012");
        map.put("tenantId", "1");
        map.put("currentUser", "{'userCode':'02224961','userName':'兀宵阳','orgCode':'999999','orgName':'总公司'}");

        ProfileEnv portal = null;
        if ("uat".equals(profile)) {
            portal = uatEnvs.get("portal");
        } else if ("prod".equals(profile)) {
            portal = prodEnvs.get("portal");
        }
        JSONObject generated = contractService.manualLaunch(map, portal);
        result += "发起网点结算合同成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起网点结算合同成功，返回消息为：{}", JSON.toJSONString(generated));
        return result;
    }


    // PS系统
    @GetMapping("/contract/ps/test")
    public String psGen(@RequestParam String profile) throws Exception {
        String result = "{\"contractContentList\":[{\"contractFieldList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"圆通速递（北京）有限公司\"},{\"fieldKey\":\"f0002\",\"fieldValue\":\"北京市顺义区南法>信镇顺畅大道1号B-042室\"},{\"fieldKey\":\"f0003\",\"fieldValue\":\"王丹\"},{\"fieldKey\":\"f0004\",\"fieldValue\":\"610430199508060528\"},{\"fieldKey\":\"f0005\",\"fieldValue\":\"陕西省咸阳市淳化县\"},{\"fieldKey\":\"f0006\",\"fieldValue\":\"陕西省咸阳市淳化县\"},{\"fieldKey\":\"f0007\",\"fieldValue\":\"陕西省西安市长安区\"},{\"fieldKey\":\"f0008\",\"fieldValue\":\"陕西省西安市长安区\"},{\"fieldKey\":\"f0009\",\"fieldValue\":\"15929601664\"},{\"fieldKey\":\"f0010\",\"fieldValue\":\"1\"},{\"fieldKey\":\"f0011\",\"fieldValue\":\"2024-06-06\"},{\"fieldKey\":\"f0012\",\"fieldValue\":\"2027-06-05\"},{\"fieldKey\":\"f0017\",\"fieldValue\":\"1\"},{\"fieldKey\":\"f0018\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0019\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0028\",\"fieldValue\":\"陕西省西安市雁塔区\"},{\"fieldKey\":\"f0029\",\"fieldValue\":\"1\"},{\"fieldKey\":\"f0034\",\"fieldValue\":\"3\"},{\"fieldKey\":\"f0037\",\"fieldValue\":\"8888\"},{\"fieldKey\":\"f0039\",\"fieldValue\":\"28\"},{\"fieldKey\":\"f0040\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0042\",\"fieldValue\":\"true\"},{\"fieldKey\":\"f0043\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0044\",\"fieldValue\":\"圆通速递有限公司\"}],\"templateCode\":\"100100010\"},{\"contractFieldList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"圆通速递（北京）有限公司\"},{\"fieldKey\":\"f0002\",\"fieldValue\":\"王丹\"},{\"fieldKey\":\"f0003\",\"fieldValue\":\"610430199508060528\"},{\"fieldKey\":\"f0004\",\"fieldValue\":\"西安人资行政组\"},{\"fieldKey\":\"f0005\",\"fieldValue\":\"人资行政>部人力资源主管\"}],\"templateCode\":\"100100050\"},{\"contractFieldList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"圆通速递（北京）有限公司\"},{\"fieldKey\":\"f0002\",\"fieldValue\":\"王丹\"},{\"fieldKey\":\"f0003\",\"fieldValue\":\"610430199508060528\"}],\"templateCode\":\"100100060\"},{\"contractFieldList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"圆通速递（北京）有限公司\"},{\"fieldKey\":\"f0002\",\"fieldValue\":\"王丹\"},{\"fieldKey\":\"f0003\",\"fieldValue\":\"610430199508060528\"}],\"templateCode\":\"100100090\"},{\"contractFieldList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"王丹\"},{\"fieldKey\":\"f0002\",\"fieldValue\":\"00020244\"},{\"fieldKey\":\"f0003\",\"fieldValue\":\"西安人资行政组\"},{\"fieldKey\":\"f0004\",\"fieldValue\":\"西安人资行政组\"},{\"fieldKey\":\"f0005\",\"fieldValue\":\"2024-06-06\"},{\"fieldKey\":\"f0006\",\"fieldValue\":\"15929601664\"}],\"templateCode\":\"100100070\"},{\"contractFieldList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"610430199508060528\"}],\"templateCode\":\"100100085\"}],\"contractInfoExtendList\":[{\"fieldKey\":\"f0001\",\"fieldValue\":\"王丹\"},{\"fieldKey\":\"f0002\",\"fieldValue\":\"00020244\"},{\"fieldKey\":\"f0003\",\"fieldValue\":\"15929601664\"},{\"fieldKey\":\"f0004\",\"fieldValue\":\"610430199508060528\"},{\"fieldKey\":\"f0005\",\"fieldValue\":\"人资行政部人力资源主管\"},{\"fieldKey\":\"f0006\",\"fieldValue\":\"M1\"},{\"fieldKey\":\"f0007\",\"fieldValue\":\"直营\"},{\"fieldKey\":\"f0008\",\"fieldValue\":\"圆通信息科技（西安）有>限公司\"},{\"fieldKey\":\"f0009\",\"fieldValue\":\"圆通信息科技（西安）有限公司\"},{\"fieldKey\":\"f0010\",\"fieldValue\":\"西安人资行政组\"},{\"fieldKey\":\"f0011\",\"fieldValue\":\"西安人资行政组\"},{\"fieldKey\":\"f0012\",\"fieldValue\":\"西安人资行政组\"},{\"fieldKey\":\"f0013\",\"fieldValue\":\"00020244_80\"},{\"fieldKey\":\"f0014\",\"fieldValue\":\"2024-06-06\"},{\"fieldKey\":\"f0015\",\"fieldValue\":\"2027-06-05\"},{\"fieldKey\":\"f0016\",\"fieldValue\":\"36\"},{\"fieldKey\":\"f0017\",\"fieldValue\":\"有效\"},{\"fieldKey\":\"f0018\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0019\",\"fieldValue\":\"10 - 新签\"},{\"fieldKey\":\"f0020\",\"fieldValue\":\"10-固定期限劳动合>同\"},{\"fieldKey\":\"f0021\",\"fieldValue\":\"10 - 标准\"},{\"fieldKey\":\"f0022\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0023\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0024\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0025\",\"fieldValue\":\"\"},{\"fieldKey\":\"f0026\",\"fieldValue\":\"2022-09-13\"}],\"customizedCode\":\"00020244_80\",\"endDate\":1812124800000,\"initiatorCode\":\"02087744\",\"initiatorName\":\"陶芸静\",\"initiatorOrgCode\":\"994100\",\"name\":\"王丹(00020244)签署的文档\",\"otherSidesMobile\":\"15929601664\",\"otherSidesName\":\"王丹\",\"otherSidesType\":\"PERSONAL\",\"ourSidesCompany\":\"圆通速递（北京）有限公司\",\"ourSidesMobile\":\"18512130206\",\"parentType\":\"100\",\"properties\":10,\"startDate\":1717603200000,\"tenantId\":\"1\",\"type\":\"100\"}";

        ProfileEnv ps = null;
        if ("uat".equals(profile)) {
            ps = uatEnvs.get("portal");
        } else if ("prod".equals(profile)) {
            ps = prodEnvs.get("portal");
        }
        JSONObject generated = contractService.psGen(result, ps);
        result += "发起PS合同成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起PS合同成功，返回消息为：{}", JSON.toJSONString(generated));

        return result;
    }


    @GetMapping("/contract/archive/test")
    public String archiveGen(@RequestParam String profile) throws Exception {
        String result = "";
        String json = "{\n" +
                "    \"bizCode\": \"test\",\n" +
                "    \"bizType\": \"franchisee\",\n" +
                "    \"name\": \"wd测试合同002\",\n" +
                "    \"startDate\": \"2025-12-23 00:00:00\",\n" +
                "    \"endDate\": \"2025-12-23 00:00:00\",\n" +
                "    \"creatorCode\": \"02205474\",\n" +
                "    \"creatorName\": \"王丹\",\n" +
                "    \"creatorOrgCode\": \"1045\",\n" +
                "    \"templateCodes\": [\n" +
                "        \"franchisee001\"\n" +
                "    ],\n" +
                "    \"extension\": {\n" +
                "        \"stationCode\": \"yz10004\",\n" +
                "        \"stationName\": \"妈妈驿站\",\n" +
                "        \"secondParty\": \"兴化市淳安县妈妈驿站\",\n" +
                "        \"secondRegisteredAddress\": \"陕西省西安市雁塔区天谷八路002号\",\n" +
                "        \"secondBusinessAddress\": \"软件新城云汇谷\",\n" +
                "        \"secondUSCICode\": \"91310120MA1HU8XJ5N\",\n" +
                "        \"secondLegalName\": \"王丹\",\n" +
                "        \"secondLegalPhone\": \"15929601664\",\n" +
                "        \"licenseAddress\": \"陕西省西安市雁塔区\",\n" +
                "        \"aroundRange\": \"贵州省贵阳市花溪区东至甲秀南路，南至贵阳南环高速，西至贵安大道，北至金竹路\",\n" +
                "        \"startDate\": \"2025年4月22日\",\n" +
                "        \"endDate\": \"2026年4月18日\",\n" +
                "        \"earnestMoney\": \"500000123\",\n" +
                "        \"secondContactName\": \"王丹\",\n" +
                "        \"secondContactIdCard\": \"610430199005280528\",\n" +
                "        \"secondContactPhone\": \"13888888888\",\n" +
                "        \"contractAccount\": \"13786512574@qq.com\",\n" +
                "        \"firstLegalName\":\"喻会蛟\",\n" +
                "        \"firstParty\":\"圆通速递有限公司\",\n" +
                "        \"signLocation\":\"上海市青浦区\"\n" +
                "    },\n" +
                "    \"signatories\": [\n" +
                "        {\n" +
                "            \"role\": \"INITIATOR\",\n" +
                "            \"type\": \"COMPANY\",\n" +
                "            \"signatoryName\": \"圆通速递有限公司\",\n" +
                "            \"legalPerson\": \"\",\n" +
                "            \"contact\": \"17791948827\",\n" +
                "            \"sequence\": \"4\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"role\": \"SIGNATORY\",\n" +
                "            \"type\": \"PERSONAL\",\n" +
                "            \"signatoryName\": \"马萌\",\n" +
                "            \"legalPerson\": \"\",\n" +
                "            \"contact\": \"13572493974\",\n" +
                "            \"sequence\": \"1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"role\": \"SIGNATORY\",\n" +
                "            \"type\": \"COMPANY\",\n" +
                "            \"signatoryName\": \"融安县安达速递有限责任公司\",\n" +
                "            \"legalPerson\": \"王丹\",\n" +
                "            \"contact\": \"15929601664\",\n" +
                "            \"sequence\": \"3\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        ProfileEnv archive = null;
        if ("uat".equals(profile)) {
            archive = uatEnvs.get("archive");
        } else if ("prod".equals(profile)) {
            archive = prodEnvs.get("archive");
        }
        JSONObject generated = contractService.generatedV3(json, archive);
        result += "发起加盟商协议成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起加盟商协议成功，返回消息为：{}", JSON.toJSONString(generated));
        Map<String, Object> map = JSONObject.parseObject(json);
        String code = String.valueOf(generated.get("data"));
        map.put("contractCode", code);
        Thread.sleep(30 * 1000);
        JSONObject reminded = contractService.remind(code, archive);
        result += "加盟商协议催签成功，返回消息为：" + reminded.toJSONString() + " <br>";
        log.info("加盟商协议催签成功，返回消息为：{}", JSON.toJSONString(reminded));

        JSONObject recalled = contractService.recall(code, archive);
        result += "加盟商协议撤回成功，返回消息为：" + recalled.toJSONString() + " <br>";
        log.info("加盟商协议撤回成功，返回消息为：{}", JSON.toJSONString(recalled));

        JSONObject modified = contractService.modifyV3(map, archive);
        result += "加盟商协议修改成功，返回消息为：" + modified.toJSONString() + " <br>";
        log.info("加盟商协议修改成功，返回消息为：{}", JSON.toJSONString(modified));
        return result;
    }

    @GetMapping("/contract/driveless/test")
    public String drivelessGen(@RequestParam String profile) throws Exception {
        String result = "";
        String json = "{\n" +
                "    \"bizCode\": \"WG2025052300009\",\n" +
                "    \"bizType\": \"driverlessCars\",\n" +
                "    \"creatorCode\": \"02224961\",\n" +
                "    \"creatorName\": \"兀宵阳\",\n" +
                "    \"endDate\": \"2030-05-28 16:17:02\",\n" +
                "    \"extension\": {\n" +
                "        \"bankCardNo\": \"62364905847586\",\n" +
                "        \"companyName\": \"湖南圆汇物流有限公司\",\n" +
                "        \"openingBankName\": \"中国银行\",\n" +
                "        \"products\": [\n" +
                "            {\n" +
                "                \"lineTotal\": \"260000.0\",\n" +
                "                \"name\": \"3m³无人车\",\n" +
                "                \"quantity\": \"2\",\n" +
                "                \"unitPrice\": \"130000.0\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"lineTotal\": \"140000.0\",\n" +
                "                \"name\": \"6m³无人车\",\n" +
                "                \"quantity\": \"100\",\n" +
                "                \"unitPrice\": \"14000000.00\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"secondParty\": \"融安县安达速递有限责任公司\",\n" +
                "        \"secondRegisteredAddress\": \"上海市奉贤区四团镇平港路883-885号1幢\",\n" +
                "        \"tinNumber\": \"91310120MA1HU8XJ5N\"\n" +
                "    },\n" +
                "    \"name\": \"配送机器人合同\",\n" +
                "    \"signatories\": [\n" +
                "        {\n" +
                "            \"role\": \"INITIATOR\",\n" +
                "            \"type\": \"COMPANY\",\n" +
                "            \"signatoryName\": \"湖南圆汇物流有限公司\",\n" +
                "            \"legalPerson\": \"\",\n" +
                "            \"contact\": \"17791948827\",\n" +
                "            \"sequence\": 2\n" +
                "        },\n" +
                "        {\n" +
                "            \"role\": \"SIGNATORY\",\n" +
                "            \"type\": \"COMPANY\",\n" +
                "            \"signatoryName\": \"融安县安达速递有限责任公司\",\n" +
                "            \"legalPerson\": \"\",\n" +
                "            \"contact\": \"15929601664\",\n" +
                "            \"sequence\": 1\n" +
                "        }\n" +
                "    ],\n" +
                "    \"startDate\": \"2025-05-23 16:17:02\",\n" +
                "    \"templateCodes\": [\n" +
                "        \"driverlessCars\"\n" +
                "    ]\n" +
                "}";

        ProfileEnv networkManage = null;
        if ("uat".equals(profile)) {
            networkManage = uatEnvs.get("networkManage");
        } else if ("prod".equals(profile)) {
            networkManage = prodEnvs.get("networkManage");
        }
        JSONObject generated = contractService.generatedV3(json, networkManage);
        result += "发起无人车成功，返回消息为：" + generated.toJSONString() + " <br>";
        log.info("发起无人车成功，返回消息为：{}", JSON.toJSONString(generated));
        Map<String, Object> map = JSONObject.parseObject(json);
        String code = String.valueOf(generated.get("data"));
        map.put("contractCode", code);
        Thread.sleep(30 * 1000);
        JSONObject reminded = contractService.remind(code, networkManage);
        result += "无人车催签成功，返回消息为：" + reminded.toJSONString() + " <br>";
        log.info("无人车催签成功，返回消息为：{}", JSON.toJSONString(reminded));

        JSONObject recalled = contractService.recall(code, networkManage);
        result += "无人车撤回成功，返回消息为：" + recalled.toJSONString() + " <br>";
        log.info("无人车撤回成功，返回消息为：{}", JSON.toJSONString(recalled));

        JSONObject modified = contractService.modifyV3(map, networkManage);
        result += "无人车修改成功，返回消息为：" + modified.toJSONString() + " <br>";
        log.info("无人车修改成功，返回消息为：{}", JSON.toJSONString(modified));
        return result;
    }


}
