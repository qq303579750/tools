package com.mingyuan.wxy.tools.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.ObjectUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


public final class SignUtilsV1 {

    /**
     * utf-8
     */
    public static final String CHARSET_UTF8 = "utf-8";

    public static void main(String[] args) throws Exception {
        //  URL请求的消息体
        String json = "{\n" +
                "        \"id\": \"d63c01b11f501ebde0c4526fc303395a\"\n" +
                "}";
        //  加签密钥
        String appSecret = "134b17771e";

        String appKey = "332301eeeec34c178c10f845c18c63f4";

        //  获取时间戳
        long timestamp = System.currentTimeMillis();

        //  输出结果
        System.out.println("timestamp=" + timestamp);
        System.out.println("signature=" + SignUtilsV1.signBodyParams(json, timestamp, appKey, appSecret));
    }

    public static String getDataSignature(final String content, final String secretKey, final long currentDate) {
        String dataDigest;
        String signContent = content + secretKey + currentDate;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signContent.getBytes(CHARSET_UTF8));
            dataDigest = new String(Base64.encodeBase64(md.digest()), CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataDigest;
    }

    public static String getDataSignatureV2(final String content, final String secretKey, final String currentDate) {
        String dataDigest;
        String signContent = content + secretKey + currentDate;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signContent.getBytes(CHARSET_UTF8));
            dataDigest = new String(Base64.encodeBase64(md.digest()), CHARSET_UTF8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dataDigest;
    }


//    public static void main(String[] args) throws Exception {
//        //  URL请求的消息体
//        String bodyJsonStr = "{\"student\":\"student\",\"key\":4,\"another\"
//        :[\"bobo\",\"popo\"],\"boy\":{\"tom\":false}}";
//
//        //  加签密钥
//        String appSecret = "2536a4fce8";
//
//        String appKey = "e7bca8057b4041eeb2983f2825e45187";
//
//        //  获取时间戳
//        long timestamp = System.currentTimeMillis();
//
//        //  输出结果
//        System.out.println("timestamp=" + timestamp);
//        System.out.println("signature=" + DataSignatureUtils.signBodyParams(bodyJsonStr, timestamp,
//        appKey, appSecret));
//    }

    /**
     * 获取请求消息体的签名值
     *
     * @param bodyJsonStr 请求的消息体，json格式
     * @param timestamp   时间戳(毫秒)
     * @param appKey      加签accessKey密钥
     * @param appSecret   加签secretKey密钥
     * @return 请求消息体的签名值
     * @throws Exception
     */
    public static String signBodyParams(String bodyJsonStr, long timestamp, String appKey, String appSecret)
            throws Exception {
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
            if (!ObjectUtils.isEmpty(entry.getValue())) {
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
    public static String hashMAC(String data, String secret) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        return new String(java.util.Base64.getEncoder().encode(rawHmac));
    }
}


