package com.mingyuan.wxy.tools.beetl;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeetleTest {
    public static void main(String[] args) throws IOException {
        List<String> users = new ArrayList<>();
        users.add("");
        users.add("4");
        users.add("3");
        users.add("2");
        users.add("1");


        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("eduSchool", "高1");
        map1.put("eduEntryDate", "2025-02-05");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("eduSchool", "高3");
        map2.put("eduEntryDate", "2025-02-06");
        list.add(map1);
        list.add(map2);
        map.put("educationList", list);

        String html3 = "<body><p><span style=\"font-family: FangZhengSSJT; font-size: 16px;\">甲方：" +
                "<label data-w-e-type=\"attachSelect\" class=\"attachSelect-cls\" key=\"firstParty\" data-key=\"firstParty\" key=\"firstParty\" ctms_key=\"firstParty\" data-ctms_key=\"firstParty\" ctms_key=\"firstParty\" data-ctms_name=\"甲方 \" style=\" padding: 4px 8px; margin: 0 4px; border-bottom: 1px solid #ddd;\">${firstParty}</span></p>\n" +
                "<p><span style=\"font-family: FangZhengSSJT; font-size: 16px;\">教育经历：</span></p>\n" +
                "<table style=\"border-collapse: collapse; border: 1px solid black; width: 100%; text-align: center;\">\n" +
                "<thead>\n" +
                "<tr>\n" +
                "<th style=\"border: 1px solid black;\"><span style=\"font-family: FangZhengSSJT;\">入学日期</span></th>\n" +
                "<th style=\"border: 1px solid black;\"><span style=\"font-family: FangZhengSSJT;\">学校</span></th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody><!--<%for(item in educationList){%>-->\n" +
                "<tr>\n" +
                "<td >${item.eduSchool}</td>\n" +
                "<td >${item.eduEntryDate}</td>\n" +
                "</tr>\n" +
                "<!--<%}%>--></tbody>\n" +
                "</table>\n" +
                "<p></p></body>";

        String html4 = "<body>" +
                "<table border=\"1\" style=\"border-collapse: collapse; width: 100%;\"><colgroup><col style=\"width: 50%;\"><col style=\"width: 50%;\"></colgroup>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>学校</span></td>\n" +
                "<td>学历</span></td>\n" +
                "</tr>\n" +
                "<!-- <%for(education in educationList){" +
                "%> -->\n" +
                "<tr>\n" +
                "<td>${education.eduSchool}</td>\n" +
                "<td>${education.eduEntryDate}</td>\n" +
                "</tr>\n" +
                "<!-- <%}%> --></tbody>\n" +
                "</table>\n" +
                "</body>\n";

        String html5 = "<body>" +
                "<table border=\"1\" style=\"border-collapse: collapse; width: 100%;\">" +
                "<colgroup><col style=\"width: 50%;\"><col style=\"width: 50%;\"></colgroup>" +
                "<tbody>" +
                "<tr>" +
                "<td>学校</td>" +
                "<td>学历</td>" +
                "</tr>" +
                "<% for(education in educationList) { %>" +
                "<tr>" +
                "<td>${education.eduSchool}</td>" +
                "<td>${education.edu}</td>" +
                "</tr>" +
                "<% } %>" +
                "</tbody>" +
                "</table>" +
                "</body>";


        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate(html3);
        t.getCtx().safeOutput = true;
        t.binding(map);
//渲染结果
        String str = t.render();
        System.out.println(str);
    }
}
