package com.mingyuan.wxy.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import com.mingyuan.wxy.tools.httpclient.HttpClient;
import com.mingyuan.wxy.tools.sign.SignUtilsV1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static void main(String[] args) {
        // 1. 定义文件路径
        String filePath = "E:/request2.txt";

        try {
            // 2. 读取文件并转换为JSON数组
            JSONArray jsonArray = convertTxtToJson(filePath);
            List<Map<String, Object>> names = new ArrayList<>();
            jsonArray.forEach(t -> {
                Map<String, Object> map = JSONObject.parseObject(String.valueOf(t), Map.class);
                generated(map);
                //names.add(map);
            });
            // 3. 打印结果
            System.out.println("转换后的JSON数据:" + JSONObject.toJSONString(names.size()));
            System.out.println("转换后的JSON数据:" + JSONObject.toJSONString(names));


        } catch (IOException e) {
            System.err.println("处理文件时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static JSONObject generated(Map<String, Object> map) {
        try {
            ProfileEnv env = ProfileEnv.builder()
                    .appKey("a0e26c1a4b9e444185a8b9d159febf24")
                    .appSecret("252fkbhj21")
                    .urlPrefix("http://contract-open-api-inter.yto56test.com/api")
                    .xApplicationCode("ann-api")
                    .userCode("02224961")
                    .build();
            // 新人资prod
            ProfileEnv ehrProd = ProfileEnv.builder()
                    .appKey("2015d83e63624ccbaafea63a9b1ca6d2")
                    .appSecret("78f71e5e9e")
//                .urlPrefix("https://open-contract.yto.net.cn:8080/api")
                    .xApplicationCode("ann-api")
                    .userCode("02224961")
                    .build();

            Map<String, Object> extension = (Map<String, Object>) map.get("extension");
            Map<String, Object> newExtension = new HashMap<>();
            newExtension.put("signDateCN", extension.get("signDateCN"));
            map.put("extension", newExtension);

            List<String> templates = Collections.singletonList("100100106");
            map.put("templateCodes", templates);

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

        }
        return null;
    }


    /**
     * 将文本文件内容逐行转换为JSON数组
     *
     * @param filePath 文本文件路径
     * @return 包含所有行数据的JSON数组
     * @throws IOException 如果读取文件失败
     */
    public static JSONArray convertTxtToJson(String filePath) throws IOException {
        // 方法1: 使用Java 8 Stream API（更简洁）
        // return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
        //     .map(TxtFileToJsonConverter::createLineJson)
        //     .collect(Collectors.toCollection(JSONArray::new));

        // 方法2: 使用BufferedReader（更传统，支持更老版本Java）
        List<JSONObject> jsonLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // 跳过空行
                if (!line.trim().isEmpty()) {
                    jsonLines.add(createLineJson(line));
                }
            }
        }

        return new JSONArray(jsonLines);
    }

    /**
     * 创建单行JSON对象
     *
     * @param line 文本行内容
     * @return 包含行数据的JSON对象
     */
    private static JSONObject createLineJson(String line) {
        return JSONObject.parseObject(line);
    }
}