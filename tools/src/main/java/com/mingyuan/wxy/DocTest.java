package com.mingyuan.wxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DocTest {
    public static void main(String[] args) throws IOException {
        String ossUrl = "https://doc-gateway.yto56test.com:8088/document-gateway-front/?req=cb996029a8aa417daaa35586fca533a7&token=d1004d4be3984dd99cebdc99331de35c&filling=true";
        URL url = new URL(ossUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET"); // 设置请求方法为 GET
        // 步骤 3：获取输入流
        InputStream inputStream = connection.getInputStream();
        System.err.println("完成");

    }

}
