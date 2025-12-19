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
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;


public class SignUtilV3 {
    public static void main(String[] args) throws Exception {
        //  URL请求的消息体
        String bodyJsonStr = "{\"mac\":\"04-42-1A-8E-E1-A0\",\"kills\":{\"pId\":14401,\"kills\":[{\"id\":21317,\"success\":true,\"msg\":\"未找到进程\"}]},\"samples\":[{\"id\":3,\"success\":false,\"msg\":\"文件采集上传失败\"}]}今天 10:18";

        //  加签密钥
        String appSecret = "2536a4fce8";

        String appKey = "e7bca8057b4041eeb2983f2825e45187";

        //  获取时间戳
        long timestamp = System.currentTimeMillis();

        //  输出结果
        System.out.println("timestamp=" + timestamp);
        System.out.println("signature=" + SignUtilV3.signBodyParams(bodyJsonStr, timestamp, appKey, appSecret));
    }
    /**
     * 获取请求消息体的签名值
     *
     * @param bodyJsonStr 请求的消息体，json格式。
     * 如果get请求无参数，上送为空字符串就可以。
     * 如果get请求有@RequestParam参数，把参数添加到一个map中并转成String类型，多个参数进行排序
     * @param timestamp   时间戳(毫秒)
     * @param appKey   加签accessKey密钥
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
