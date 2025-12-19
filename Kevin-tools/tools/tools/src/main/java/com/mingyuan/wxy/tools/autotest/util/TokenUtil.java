package com.mingyuan.wxy.tools.autotest.util;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.httpclient.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Slf4j
public class TokenUtil {

    public static String obtainToken(String userCode) {
        String tgc = "";
        String url = "http://sso-uat-api-inter.yto56.com.cn/ulp-auth-tgc-server/oauth/exchangeYtoTgc";
        String authorizationHeader = "Basic WVRPLUFDVElPTi1BTkFMWVNJUzp6MHF5NGpsbnFtaWoyenU4b3Q2ZA==";

        // 创建 HttpClient 实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Authorization", authorizationHeader);

            // 构建表单数据
            List<NameValuePair> formData = new ArrayList<>();
            formData.add(new BasicNameValuePair("userCode", userCode));

            // 设置请求体
            httpPost.setEntity(new UrlEncodedFormEntity(formData, "UTF-8"));

            // 发送请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 获取响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Response Code: " + statusCode);
                // 获取响应体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity, "UTF-8");
                    JSONObject jsonObject = JSONObject.parseObject(responseBody);
                    if (isNotEmpty(jsonObject)) {
                        JSONObject obj = jsonObject.getJSONObject("data");
                        tgc = obj.getString("ytoTgc");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tgc;
    }

    public static String genToken(String userCode) {
        String tgc = TokenUtil.obtainToken(userCode);
        String token = "";
        String url = "http://10.130.16.110:8766/announce/portal/oauth/login/tokenObtain";
        // 创建 HttpClient 实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            httpPost.setHeader("X-Requested-With", "XMLHttpRequest");
            // 构建表单数据
            List<NameValuePair> formData = new ArrayList<>();
            formData.add(new BasicNameValuePair("ytoTgc", tgc));

            // 设置请求体
            httpPost.setEntity(new UrlEncodedFormEntity(formData, "UTF-8"));

            // 发送请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                // 获取响应状态码
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Response Code: " + statusCode);
                Header authorization = response.getFirstHeader("Authorization");
                token = authorization.getValue();
                // 获取响应体
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity, "UTF-8");
                    log.info(responseBody);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
