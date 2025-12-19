package com.mingyuan.wxy.tools.sign;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author 杨凯
 * @date 2024-05-29 14:38
 * @mail 02266235@yto.net.cn
 */

public class SignUtilV2 {
    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("announceId", 23135);

        //  URL请求的消息体
        String json = "{\n" +
                "    \"businessClassify\": \"100\",\n" +
                "    \"creatorCode\": \"02205474\",\n" +
                "    \"creatorName\": \"王丹\",\n" +
                "    \"creatorOrgCode\": \"1045\",\n" +
                "    \"endDate\": \"2025-04-02 00:00:00\",\n" +
                "    \"extension\": {\n" +
                "        \"businessCode\": \"02350825_25\",\n" +
                "        \"contractPeriodChoice\": \"1\",\n" +
                "        \"currentResidence\": \"重庆市渝中区\",\n" +
                "        \"currentResidenceDetail\": \"海北路东100米（228国道辅路与205国道辅路交叉口西北260米）\",\n" +
                "        \"employeeDay\": \"2025-03-03\",\n" +
                "        \"firstEnd\": \"2025-04-02\",\n" +
                "        \"firstParty\": \"圆通速递（北京）有限公司\",\n" +
                "        \"firstPartyAddress\": \"北京市顺义区南法信镇顺畅大道1号B-042室\",\n" +
                "        \"firstStart\": \"2025-03-03\",\n" +
                "        \"idCard\": \"320621199307134717\",\n" +
                "        \"managementJob\": \"true\",\n" +
                "        \"mobile\": \"17791948827\",\n" +
                "        \"otherJob\": \"市场部终端管理员\",\n" +
                "        \"payDays\": \"28\",\n" +
                "        \"registerPlace\": \"北京市房山区\",\n" +
                "        \"registerPlaceDetail\": \"北京市房山区济大路11号1层\",\n" +
                "        \"relationCompany\": \"圆通速递有限公司\",\n" +
                "        \"salaryChoice\": \"1\",\n" +
                "        \"certifierPhone\":\"17791948827\",\n" +
                "        \"salaryHourly\": \"11111.00\",\n" +
                "        \"secondParty\": \"王丹\",\n" +
                "        \"secondPartyJob\": \"21\",\n" +
                "        \"signOthers\": \"true\",\n" +
                "        \"thirdParty\": \"12\",\n" +
                "        \"trialChoice\": \"1\",\n" +
                "        \"userCode\": \"02350825\",\n" +
                "        \"vacationChoice\": \"1\",\n" +
                "        \"signDateCN\": \"2025-03-27\",\n" +
                "        \"workPlace\": \"浙江省嘉兴市嘉善县\",\n" +
                "         \"languageCertNo\":\"1\",\n" +
                "        \"languageCertName\":\"2\"\n" +
                "    },\n" +
                "    \"firstParty\": \"圆通速递（北京）有限公司\",\n" +
                "    \"firstPartyMobile\": \"17791948827\",\n" +
                "    \"name\": \"0621生产验收人资合同\",\n" +
                "    \"secondParty\": \"王丹\",\n" +
                "    \"secondPartyMobile\": \"15929601664\",\n" +
                "    \"secondPartyType\": \"PERSONAL\",\n" +
                "    \"startDate\": \"2025-03-03 00:00:00\",\n" +
                "    \"templateCodes\": [\n" +
                "        \n" +
                "        \"100100091\" \n" +
                "    ]\n" +
                "}";
        // 离职证明
//        String appSecret = "e6927f976c";
//        String appKey = "eb18911885cc4ed2bba551ad6a3320ca";
        // 内网-prod-网点管家
//        String appSecret = "45d45ef822";
//        String appKey = "c2b0cdf0ea9442638202b5983f98bbb9";
        //  驿站-测试
//        String appSecret = "b87af5e032";
//        String appKey = "a8119ca029374edd92db6b27f8039769";
        //  人资-测试
//        String appSecret = "252fkbhj21";
//        String appKey = "a0e26c1a4b9e444185a8b9d159febf24";
        // 企业内网 GPT
//        String appSecret = "0e35cb4f8f";
//        String appKey = "1c7862a88e344c728c547c3f28865e38";

        // 运盟-测试
//        String appSecret = "21f7b48187";
//        String appKey = "65f1be08444b439887ef6c51de3b3c86";
        // 人资生产
        String appSecret = "78f71e5e9e";
        String appKey = "2015d83e63624ccbaafea63a9b1ca6d2";
        // 租赁-prod
//        String appSecret = "0cb0973e25";
//        String appKey = "462bfffcb4e24895aa9934f3a69f9231";
        // 圆梦-uat
//        String appSecret = "6f02b9d30d";
//        String appKey = "f2dfe5c82d3a4d259f0375603214e42f";
        // unknown
//        String appSecret="d6d739865f";
//        String appKey="ea2de9dc072646d6900659ed82889d44";
        // 安全保密协议uat
//        String appSecret = "90b5dd2e31";
//        String appKey = "b10e5776998df0ddded26e8029e1d277";
        // 安全保密协议prod
//        String appSecret = "134b17771e";
//        String appKey = "332301eeeec34c178c10f845c18c63f4";
        // unknown
//        String appSecret = "d4c1433db9";
//        String appKey = "41d2438597e24488b63e299da121511d";
        // 圆梦行动-prod
//        String appSecret = "99e6a73ffe";
//        String appKey = "c0dc30132ec848258ef47c7b4d6f19c6";

        // 圆梦行动-prod
//        String appSecret = "b1d9223b6f";
//        String appKey = "f7c31cfe7efe4612bdcb230ed1c00d0f";

        //  获取时间戳
        long timestamp = System.currentTimeMillis();
//        long timestamp =1745301633959L;

        //  输出结果
        System.out.println(timestamp);
//        System.out.println(SignUtilV2.signBodyParams(JSONObject.toJSONString(map), timestamp, appKey, appSecret));
        System.out.println(SignUtilV2.signBodyParams(json, timestamp, appKey, appSecret));
//        System.out.println(SignUtilsV1.signBodyParams(json, timestamp, appKey, appSecret));
    }

    /**
     * 获取请求消息体的签名值
     *
     * @param bodyJsonStr 请求的消息体，json格式。
     *                    如果get请求无参数，上送为空字符串就可以。
     *                    如果get请求有@RequestParam参数，把参数添加到一个map中并转成String类型，多个参数进行排序
     * @param timestamp   时间戳(毫秒)
     * @param appKey      加签accessKey密钥
     * @param appSecret   加签secretKey密钥
     * @return 请求消息体的签名值
     * @throws Exception
     */
    public static String signBodyParams(String bodyJsonStr, long timestamp, String appKey, String appSecret) throws Exception {
        // 1.对消息体进行排序，保证加签方和验签方的顺序一致
        String jsonStrAfterSort = JSON.toJSONString(sortJsonDataByKey(JSON.parseObject(bodyJsonStr)));
        // 2.将timestamp、jsonStrAfterSort和appKey组合，生成需要签名的数据
        String signData = timestamp + jsonStrAfterSort + appKey;

        // 3.生成签名值
        String signature = hashMAC(signData, appSecret);

        // 4.返回签名值
        return signature;
    }

    /**
     * 对jsonObject进行排序，保证加签方和验签方的顺序一致
     *
     * @param jsonObject json对象
     * @return 排序后的结果
     */
    public static Map<String, Object> sortJsonDataByKey(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.isEmpty()) {
            return Collections.emptyMap();
        }

        //  使用treemap进行排序，排序规则是按照字符串升序排序，数组内容不做排序
        Map<String, Object> sortedMap = new TreeMap<>(String::compareTo);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            if (!isEmpty(entry.getValue())) {
                if (entry.getValue() instanceof JSONObject) {
                    sortedMap.put(entry.getKey(), sortJsonDataByKey((JSONObject) entry.getValue()));
                } else {
                    sortedMap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return sortedMap;
    }

    /**
     * 获取HashMAC
     *
     * @param data   需要签名的数据
     * @param secret 加签密钥
     * @return 签名值
     */
    public static String hashMAC(String data, String secret) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        return new String(Base64.getEncoder().encode(rawHmac));
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof Optional) {
            return !((Optional) obj).isPresent();
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        // else
        return false;
    }
}

