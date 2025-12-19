package com.mingyuan.wxy.tools.oss;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mingyuan.wxy.tools.httpclient.HttpClient;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FileDownload {

    public static void main(String[] args) throws Exception {
        String[] titles = {
                "QYTO 00030-2016-O-G05加盟公司年检办法",
                "QYTO 00050-2021-O-G05转运中心拆包操作标准",
                "QYTO 00063-2021-M-G12 数据导出申请管理标准",
                "QYTO 00068-2020-M-G05跨境项目管理服务标准",
                "QYTO 00081-2019-S-G05全国代取件操作标准",
                "QYTO 00146-2017-M-G11海外产品盈利分析模型",
                "QYTO 00166-2019-M-G05 转运中心搬迁指南",
                "QYTO 00171-2019-O-G05加盟公司财务管理报表编制指引",
                "QYTO 00193-2022-O-G12 信息安全事件调查与处理规范",
                "QYTO-00161-2019-M-G08工程建设总包（专业分包）单位评估标准",
                "Standard Operating Procedures for Courier Service",
                "The Exclusive Business Diction Standard for International Customer Service Department",
                "财务【2020】008号票据及款链凭证管理制度",
                "出口台湾业务客服处理标准",
                "加盟公司进仓件和特殊地址收费管理办法",
                "廉正【2021】005号 审计纪察人员“十个坚决做到·十个绝不允许”守则（试行）",
                "浦东口岸出口快件清关操作标准",
                "浦东口岸进口快件清关操作标准",
                "人资【2016】014号员工亲属任职回避管理办法",
                "退回件费用管理办法",
                "委办物流、铁路运行数据考勤制度",
                "新闻【2019】001号内部出版物管理制度",
                "信息【2019】001号办公室网络管理制度",
                "信息【2021】011号 信息安全检查规范",
                "信息【2021】012号 信息系统漏洞管理及上报规定",
                "优惠政策获取管理制度",
                "运盟系统操作管理规定",
                "转运中心视频监控覆盖区域相关细则技术规范",
                "资产管理总纲制度"
        };

        List<String> errors = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            FileDownload fileDownload = new FileDownload();
            JSONObject down = fileDownload.down(title);
            Map<String, Object> map = (Map<String, Object>) down.get("data");
            JSONArray array = (JSONArray) map.get("resultData");
            if (array.isEmpty()) {
                errors.add(title);
            } else {
                JSONObject o = (JSONObject) array.get(0);
                String announceId = o.getString("announceId");
                JSONObject detail = fileDownload.detail(Integer.valueOf(announceId));
                Map<String, Object> detailMap = (Map<String, Object>) detail.get("data");
                String filePathV2 = (String) detailMap.get("contentV2");
                List<String> split = Arrays.stream(filePathV2.split(",")).collect(Collectors.toList());

                JSONObject jsonObject = fileDownload.batchPreview(split);
                List<String> paths = (List<String>) jsonObject.get("data");
                fileDownload.downFile(paths, title);
            }
        }
        System.err.println(JSONObject.toJSONString(errors));
    }


    public void downFile(List<String> split, String fileName) throws IOException {
        int i = 0;
        for (Object t : split) {
            File file = new File("E:/opt/standard/" + fileName);
            if (!file.exists()) {
                file.mkdirs();
            }
            String path = file.getPath() + "/%s.jpg";
            try {
                URL url = new URL(t.toString());
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");

                try (InputStream in = connection.getInputStream();
                     FileOutputStream out = new FileOutputStream(String.format(path, i++))) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }

                }
            } catch (IOException e) {
                System.err.println("下载图片时出错: " + e.getMessage());
            }
        }
    }

    public JSONObject batchPreview(List<String> list) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer-eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJiZWxvbmdPcmdDb2RlXCI6XCI5OTk5OTlcIixcImJlbG9uZ09yZ05hbWVcIjpcIuWbveWGheS6i-S4mumDqFwiLFwiYmVsb25nT3JnVHlwZVwiOlwiSEVBRFwiLFwibGFuZ1wiOlwiemgtQ05cIixcImxvZ2luVGltZVwiOjE3NDM2NDM0NTI4NzksXCJvcmdDb2RlXCI6XCI5OTk5OTlcIixcIm9yZ05hbWVcIjpcIuWbveWGheS6i-S4mumDqFwiLFwib3JnVHlwZVwiOlwiSEVBRFwiLFwic3lzdGVtQ29kZVwiOlwiQU5OT1VOQ0VNRU5UX01BTkFHRVJcIixcInN5c3RlbVNvdXJjZVwiOlwiUE9SVEFMXCIsXCJ1c2VyQ29kZVwiOlwiMDIyMjQ5NjFcIixcInVzZXJOYW1lXCI6XCLlhYDlrrXpmLNcIn0iLCJqdGkiOiI4YjJkZWQxYi04ODVjLTQyOGUtOWQ0YS1kOTNlNjRhNjVlNTIiLCJpYXQiOjE3NDM2NDM0NTIsImV4cCI6MTc0MzY1MDY1Mn0.czhdUfZRA7m9h8y4v3LftKnKqeWwWd6vmqjzbcJNcpe50_BPBbYqJalY63dMKw0Og4eleHyWMMcJrnCkULYVgA");
        String result = HttpClient.doPostJsonByHeaders("https://ann-admin.yto.net.cn/announce/file/batchPreview", JSONObject.toJSONString(list), headers);
        return JSONObject.parseObject(result);
    }

    public JSONObject down(String title) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", 1);
        map.put("limit", 10);
        map.put("announceName", title);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer-eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJiZWxvbmdPcmdDb2RlXCI6XCI5OTk5OTlcIixcImJlbG9uZ09yZ05hbWVcIjpcIuWbveWGheS6i-S4mumDqFwiLFwiYmVsb25nT3JnVHlwZVwiOlwiSEVBRFwiLFwibGFuZ1wiOlwiemgtQ05cIixcImxvZ2luVGltZVwiOjE3NDM2NDM0NTI4NzksXCJvcmdDb2RlXCI6XCI5OTk5OTlcIixcIm9yZ05hbWVcIjpcIuWbveWGheS6i-S4mumDqFwiLFwib3JnVHlwZVwiOlwiSEVBRFwiLFwic3lzdGVtQ29kZVwiOlwiQU5OT1VOQ0VNRU5UX01BTkFHRVJcIixcInN5c3RlbVNvdXJjZVwiOlwiUE9SVEFMXCIsXCJ1c2VyQ29kZVwiOlwiMDIyMjQ5NjFcIixcInVzZXJOYW1lXCI6XCLlhYDlrrXpmLNcIn0iLCJqdGkiOiI4YjJkZWQxYi04ODVjLTQyOGUtOWQ0YS1kOTNlNjRhNjVlNTIiLCJpYXQiOjE3NDM2NDM0NTIsImV4cCI6MTc0MzY1MDY1Mn0.czhdUfZRA7m9h8y4v3LftKnKqeWwWd6vmqjzbcJNcpe50_BPBbYqJalY63dMKw0Og4eleHyWMMcJrnCkULYVgA");
        String result = HttpClient.doPostJsonByHeaders("https://ann-admin.yto.net.cn/announce/announceMng/announceList", JSONObject.toJSONString(map), headers);
        return JSONObject.parseObject(result);
    }

    public JSONObject detail(Integer id) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer-eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJiZWxvbmdPcmdDb2RlXCI6XCI5OTk5OTlcIixcImJlbG9uZ09yZ05hbWVcIjpcIuWbveWGheS6i-S4mumDqFwiLFwiYmVsb25nT3JnVHlwZVwiOlwiSEVBRFwiLFwibGFuZ1wiOlwiemgtQ05cIixcImxvZ2luVGltZVwiOjE3NDM2NDM0NTI4NzksXCJvcmdDb2RlXCI6XCI5OTk5OTlcIixcIm9yZ05hbWVcIjpcIuWbveWGheS6i-S4mumDqFwiLFwib3JnVHlwZVwiOlwiSEVBRFwiLFwic3lzdGVtQ29kZVwiOlwiQU5OT1VOQ0VNRU5UX01BTkFHRVJcIixcInN5c3RlbVNvdXJjZVwiOlwiUE9SVEFMXCIsXCJ1c2VyQ29kZVwiOlwiMDIyMjQ5NjFcIixcInVzZXJOYW1lXCI6XCLlhYDlrrXpmLNcIn0iLCJqdGkiOiI4YjJkZWQxYi04ODVjLTQyOGUtOWQ0YS1kOTNlNjRhNjVlNTIiLCJpYXQiOjE3NDM2NDM0NTIsImV4cCI6MTc0MzY1MDY1Mn0.czhdUfZRA7m9h8y4v3LftKnKqeWwWd6vmqjzbcJNcpe50_BPBbYqJalY63dMKw0Og4eleHyWMMcJrnCkULYVgA");
        String result = HttpClient.doGetJsonByHeaders("https://ann-admin.yto.net.cn/announce/announceMng/announceDetail?announceId=" + id, headers);
        return JSONObject.parseObject(result);
    }
}
