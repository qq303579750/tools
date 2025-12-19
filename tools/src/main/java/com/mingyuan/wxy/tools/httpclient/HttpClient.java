package com.mingyuan.wxy.tools.httpclient;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
public class HttpClient {

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        // 发起请求
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        // 调用不带参数的get请求
        return doGet(uriBuilder.build().toString());
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static byte[] doGetFile(String url, Map<String, String> headers) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        byte[] data = null;
        if (!CollectionUtil.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 发起请求
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            data = EntityUtils.toByteArray(response.getEntity());
        }
        return data;
    }

    /**
     * Post请求 <JSON参数>
     *
     * @param url
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public static String doPostJson(String url, String jsonParam) throws Exception {
        return doPostJsonByHeader(url, jsonParam, null, null);
    }

    /**
     * Post请求 <JSON参数>
     *
     * @param url
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public static String doPostJsonByHeader(String url, String jsonParam, String headerName, String headerValue) throws Exception {
        log.info("doPostJson url:" + url);
        log.info("doPostJson jsonParam:" + jsonParam);
        JSON.parseObject(jsonParam);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (!StringUtils.isAnyBlank(headerName, headerValue)) {
            httpPost.setHeader(headerName, headerValue);
        }
        httpPost.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    /**
     * Post请求 <JSON参数>
     *
     * @param url
     * @param jsonParam
     * @param headers
     * @return
     * @throws Exception
     */
    public static String doPostJsonByHeaders(String url, String jsonParam, Map<String, String> headers) throws Exception {
        log.info("doPostJson url:" + url);
        log.info("doPostJson jsonParam:" + jsonParam);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (!CollectionUtil.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.setEntity(new StringEntity(jsonParam, Charset.forName("UTF-8")));
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity());
    }

    public static String doGetJsonByHeaders(String url,  Map<String, String> headers) throws Exception {
        log.info("doPostJson url:" + url);
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (!CollectionUtil.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity());
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, Object> map) throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        // 判断map是否为空，不为空则进行遍历，封装对象
        if (MapUtils.isNotEmpty(map)) {
            httpPost.setEntity(new StringEntity(JSON.toJSONString(map), "utf-8"));
        }
        // 发起请求
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public static String doPostUrlencoded(String url, Map<String, Object> map) throws Exception {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        // 判断map是否为空，不为空则进行遍历，封装对象
        if (MapUtils.isNotEmpty(map)) {
            httpPost.setEntity(new StringEntity(JSON.toJSONString(map), "utf-8"));
        }
        // 发起请求
        CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "UTF-8");
    }
}