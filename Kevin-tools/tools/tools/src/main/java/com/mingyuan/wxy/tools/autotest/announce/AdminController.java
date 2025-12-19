package com.mingyuan.wxy.tools.autotest.announce;

import com.alibaba.fastjson2.JSONObject;
import com.mingyuan.wxy.tools.autotest.ProfileEnv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    static Map<String, ProfileEnv> uatEnvs = new HashMap<>();
    static Map<String, ProfileEnv> prodEnvs = new HashMap<>();

    static {
        ProfileEnv adminUat = ProfileEnv.builder()
                .appKey("f2dfe5c82d3a4d259f0375603214e42f")
                .appSecret("6f02b9d30d")
                .urlPrefix("http://10.130.16.110:8766/announce")
//                .urlPrefix("http://localhost:8766/announce")
                .xApplicationCode("standard-admin-api")
                .userCode("02224961")
                .build();
        uatEnvs.put("admin", adminUat);

        ProfileEnv ymxdProd = ProfileEnv.builder()
                .appKey("87c0d2686c004296a3c05510fd02a66f")
                .appSecret("5c14588f69")
//                .urlPrefix("https://ann.yto.net.cn:48769/announce")
                .xApplicationCode("standard-admin-api")
                .userCode("02224961")
                .build();
        prodEnvs.put("ymxd-prod", ymxdProd);
    }

    private final AdminService adminService;

    @GetMapping("/add/massive/test")
    public String addMassive() throws Exception {
        String result = "";
        String json = "{\"announceContentKey\":{\"contentKey1\":\"关键字1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"announceLabel\":\"14c11e9c0ea1e3a16bbd9a04a1192f70,普通\",\"emergencyGrade\":\"14c11e9438dc00e7be35834418084341,普通\",\"copyable\":1,\"commentFlag\":1,\"announceName\":\"兀宵阳测试-20250211\",\"officialDocNum\":\"兀宵阳测试-20250211\",\"fileName\":\"驿站主控.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2ESm64swx0oAR0iXlWtZffRAfpH/sPBXiG0tNnNegKDypQy5b1XHujw/ODqjLHK7JzJ1I9lj5UsdKo7Srear4i/E3a5Q/6btE9dxk3C3GVFA==\",\"appendixName\":\"圆通标准体系E-R图.pdf\"},{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv0r9ZMb32NI51H93MyPQCLpnEyx0a6csvwdX6FiwNoPobQ1dyOTFADb4QnG3CbxyviBPV8IZqh4GtqmYkRR9npr5bwSwahlcoOxvC5+R93hzg==\",\"appendixName\":\"test.pdf\"}],\"relatedAnnounceIds\":[84535],\"announceType\":1,\"templateId\":\"15c8635ceb1ec83c39ca97a4e2a8b45d\",\"belongColumnCode\":\"colL9vqIVNE\",\"nodeHandlers\":{\"N16\":[\"00000005\"],\"N18\":[\"00000003\"],\"N7\":[\"00000001\"]},\"belongSectionCode\":\"sec89V4nWKm\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIq3FveUB5GcFDbrjKe69kKY904EZ24fmQKLJ7+yvpE8RGNo1/L8JJHAH9kDc2TAlHQ==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIjl9HG2Dv7L4RRsZifig5QWsSTubKHEf7NHHC1Xbgs+qdY1zy9svxXZhfHLr55fLUw==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIm2aqvjJCfa8+Zm0/HuZQSGrKeilZFdqcsLJIXXNYkVbv1zqdjXpVml7JxStQPlITQ==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIgaQQbIs06pYVEtkbos7UMCRewVSv7sCPyffQbtfrbY2RK5pVUaE9D2dGl8ams+svA==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv22mM8C6FFHm+COJ0beRrGvPtnHHS1d97xB9aak7R4D2Lp1LPZ+izmO0WSPYwW0PYl6BGSyoChoz94Kd9aNoN49tWAYU4LlfMFojB9GifRxJw==\",\"textType\":2,\"language\":\"zh-CN\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer id = adminService.addMassive(jsonObject, uatEnvs.get("admin"));

        log.info("发文成功，返回id为：{}", id);
        result += "发文成功，返回id为：" + id;
        Thread.sleep(10 * 1000);
        JSONObject detail = adminService.detail4Admin(id, uatEnvs.get("admin"));
        log.info("获取详情成功，返回数据为：{}", JSONObject.toJSONString(detail));
        result += "获取详情成功，返回数据为：" + JSONObject.toJSONString(detail);
        result += "<br>=============================================<br>";
        JSONObject recall = adminService.recall4Admin(detail, uatEnvs.get("admin"));
        log.info("撤回成功，返回数据为：{}", JSONObject.toJSONString(recall));
        result += "撤回成功，返回数据为：" + JSONObject.toJSONString(recall);
        result += "<br>=============================================<br>";

        String modifyJson = "{\"announceContentKey\":{\"contentKey1\":\"关键字1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"announceLabel\":\"14c11e9c0ea1e3a16bbd9a04a1192f70,普通\",\"emergencyGrade\":\"14c11e9438dc00e7be35834418084341,普通\",\"copyable\":1,\"commentFlag\":1,\"announceName\":\"兀宵阳测试-20250211\",\"officialDocNum\":\"兀宵阳测试-20250211\",\"fileName\":\"驿站主控.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2ESm64swx0oAR0iXlWtZffRAfpH/sPBXiG0tNnNegKDypQy5b1XHujw/ODqjLHK7JzJ1I9lj5UsdKo7Srear4i/E3a5Q/6btE9dxk3C3GVFA==\",\"appendixName\":\"圆通标准体系E-R图.pdf\"},{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv0r9ZMb32NI51H93MyPQCLpnEyx0a6csvwdX6FiwNoPobQ1dyOTFADb4QnG3CbxyviBPV8IZqh4GtqmYkRR9npr5bwSwahlcoOxvC5+R93hzg==\",\"appendixName\":\"test.pdf\"}],\"relatedAnnounceIds\":[84535],\"announceType\":1,\"templateId\":\"15c8635ceb1ec83c39ca97a4e2a8b45d\",\"belongColumnCode\":\"colL9vqIVNE\",\"nodeHandlers\":{\"N16\":[\"00000005\"],\"N18\":[\"00000003\"],\"N7\":[\"00000001\"]},\"belongSectionCode\":\"sec89V4nWKm\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIq3FveUB5GcFDbrjKe69kKY904EZ24fmQKLJ7+yvpE8RGNo1/L8JJHAH9kDc2TAlHQ==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIjl9HG2Dv7L4RRsZifig5QWsSTubKHEf7NHHC1Xbgs+qdY1zy9svxXZhfHLr55fLUw==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIm2aqvjJCfa8+Zm0/HuZQSGrKeilZFdqcsLJIXXNYkVbv1zqdjXpVml7JxStQPlITQ==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36UT5TVCcRMzjbAEbqj0KCSoEo5jS+7hy91RXZp+VTIgaQQbIs06pYVEtkbos7UMCRewVSv7sCPyffQbtfrbY2RK5pVUaE9D2dGl8ams+svA==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv22mM8C6FFHm+COJ0beRrGvPtnHHS1d97xB9aak7R4D2Lp1LPZ+izmO0WSPYwW0PYl6BGSyoChoz94Kd9aNoN49tWAYU4LlfMFojB9GifRxJw==\",\"textType\":2,\"language\":\"zh-CN\"}\n";
        JSONObject modifyJsonObject = JSONObject.parseObject(modifyJson);
        modifyJsonObject.put("announceId", id);
        JSONObject modified = adminService.modify4Admin(modifyJsonObject, uatEnvs.get("admin"));
        log.info("修改成功，返回数据为：{}", JSONObject.toJSONString(modified));
        result += "修改成功，返回数据为：" + JSONObject.toJSONString(modified);
        result += "<br>=============================================<br>";

        return result;
    }

    @GetMapping("/add/news/test")
    public String addNews() throws Exception {
        String result = "";
        String json = "{\"announceContentKey\":{\"contentKey1\":\"news\",\"contentKey2\":\"新闻\",\"contentKey3\":\"announce\"},\"emergencyGrade\":\"3\",\"copyable\":1,\"commentFlag\":1,\"announceName\":\"圆梦新闻测试\",\"textType\":1,\"contentV2\":\"<p>圆梦新闻测试<img src=\\\"objectId:HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2HEzAjR5Z9xVDKx3Bk1SLR4AX4pzLy43zm0envojUN/IodUbb47eSxcFdLJSZOhL0=\\\" alt=\\\"wd.png\\\" data-href=\\\"\\\" style=\\\"\\\"/></p>\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv0Pmo7H8PK0ZkBMSQsd9X6G3m9t2snykIVH4RibmeSJ4bbXnL6jF2SHEHQo08sH3mzqcD7VuxdHSTOQbq8BczOpiHXKroYvu7RxtjK2TibhDg==\",\"appendixName\":\"document.pdf\"}],\"relatedAnnounceIds\":[80002,83476],\"announceType\":2,\"templateId\":\"158f1e3eb4d3bc5e7e93e2042df9694c\",\"belongColumnCode\":\"colPaaJ43Be\",\"belongSectionCode\":\"sec0d8xUJua\",\"publishFrom\":\"圆梦与爱同行\",\"dreamColumn\":\"新闻\",\"extractFrom\":\"\",\"language\":\"zh-CN\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer id = adminService.addMassive(jsonObject, uatEnvs.get("admin"));

        log.info("发文成功，返回id为：{}", id);
        result += "发文成功，返回id为：" + id;
        Thread.sleep(10 * 1000);
        JSONObject detail = adminService.detail4Admin(id, uatEnvs.get("admin"));
        log.info("获取详情成功，返回数据为：{}", JSONObject.toJSONString(detail));
        result += "获取详情成功，返回数据为：" + JSONObject.toJSONString(detail);
        result += "<br>=============================================<br>";
        JSONObject recall = adminService.recall4Admin(detail, uatEnvs.get("admin"));
        log.info("撤回成功，返回数据为：{}", JSONObject.toJSONString(recall));
        result += "撤回成功，返回数据为：" + JSONObject.toJSONString(recall);
        result += "<br>=============================================<br>";
        String modifyJson = "{\"announceContentKey\":{\"contentKey1\":\"news\",\"contentKey2\":\"新闻\",\"contentKey3\":\"announce\"},\"emergencyGrade\":\"3\",\"copyable\":1,\"commentFlag\":1,\"announceName\":\"圆梦新闻测试\",\"textType\":1,\"contentV2\":\"<p>圆梦新闻测试<img src=\\\"objectId:HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2HEzAjR5Z9xVDKx3Bk1SLR4AX4pzLy43zm0envojUN/IodUbb47eSxcFdLJSZOhL0=\\\" alt=\\\"wd.png\\\" data-href=\\\"\\\" style=\\\"\\\"/></p>\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv0Pmo7H8PK0ZkBMSQsd9X6G3m9t2snykIVH4RibmeSJ4bbXnL6jF2SHEHQo08sH3mzqcD7VuxdHSTOQbq8BczOpiHXKroYvu7RxtjK2TibhDg==\",\"appendixName\":\"document.pdf\"}],\"relatedAnnounceIds\":[80002,83476],\"announceType\":2,\"templateId\":\"158f1e3eb4d3bc5e7e93e2042df9694c\",\"belongColumnCode\":\"colPaaJ43Be\",\"belongSectionCode\":\"sec0d8xUJua\",\"publishFrom\":\"圆梦与爱同行\",\"dreamColumn\":\"新闻\",\"extractFrom\":\"\",\"language\":\"zh-CN\"}\n";
        JSONObject modifyJsonObject = JSONObject.parseObject(modifyJson);
        modifyJsonObject.put("announceId", id);
        JSONObject modified = adminService.modify4Admin(modifyJsonObject, uatEnvs.get("admin"));
        log.info("修改成功，返回数据为：{}", JSONObject.toJSONString(modified));
        result += "修改成功，返回数据为：" + JSONObject.toJSONString(modified);
        result += "<br>=============================================<br>";

        return result;
    }

    @GetMapping("/add/institution/test")
    public String addInstitution() throws Exception {
        String result = "";
        String json = "{\"announceContentKey\":{\"contentKey1\":\"1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"copyable\":1,\"commentFlag\":1,\"announceName\":\"制度测试\",\"officialDocNum\":\"制度测试\",\"oaDeptName\":\"zhiyuanxuetang\",\"belongSubject\":\"ABOUT_BRANCH_COMPANY\",\"fileName\":\"圆通标准体系E-R图.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2DLFay2UI3480J3RBmSNNN7l8q8IL9n4+76UaKclzV+8wm9YsRpARHa/QpnCdSN7LKMNPExDECLvswalZMh311QYa7NZdxyGa03Z4uILuTDw==\",\"appendixName\":\"test.pdf\"}],\"relatedAnnounceIds\":[80002,83476],\"relatedTables\":[\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenjQHIin2+stXWqI3Xx2sdCBl55DtuA/8N7jMkXMIBYNxLnsycUVMn19MxwfnUxYDt\",\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenlCJ4xBodFmvrCu+z4fflNKvghjaqljl5cQOg0Z8SAr4Wvzcj5ZGdAbdCUR2o/D50\"],\"catalogs\":[{\"title\":\"1\",\"pageNum\":\"1\",\"id\":\"17392611525714\",\"level\":1}],\"announceType\":3,\"templateId\":\"15a2167e5abf281881a8b9a4a539b03f\",\"belongColumnCode\":\"coldYB4JMxo\",\"nodeHandlers\":{\"N21\":[\"00000001\"]},\"belongSectionCode\":\"sec0AScB1Ob\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2svsexVcS+E6v6N5fkkE5KrQAjm2iRO5HG0AuUoBMA2Rx+CVcgjS/4QaDvF8mPfG2nvL1n7GVdqkm9tfOV7CIz1ZiZirYZmSQCAajBHXmYPg==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2svsexVcS+E6v6N5fkkE5KrQAjm2iRO5HG0AuUoBMA2W5VkRNwClv6e0yFKbBd6yPS0XiP2jcwZYxNmSDNGhMQVsxgmi1QOz4cR7EnGBxrTQ==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3mZk+yoCGQeeDxVNExmeEXz2k8iw2opgegM+Cb3rDCHz/4UFN32ag7iVSzKmB+oPRNVmjaF3iRlDEot5IYNsnYqYP1NEXHPzrOnxfBbShxkQ==\",\"textType\":2,\"relatedTableNames\":[\"测试(1).pdf\",\"科技人员(1).xlsx\"],\"language\":\"zh-CN\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer id = adminService.addMassive(jsonObject, uatEnvs.get("admin"));

        log.info("发文成功，返回id为：{}", id);
        result += "发文成功，返回id为：" + id;
        Thread.sleep(10 * 1000);
        JSONObject detail = adminService.detail4Admin(id, uatEnvs.get("admin"));
        log.info("获取详情成功，返回数据为：{}", JSONObject.toJSONString(detail));
        result += "获取详情成功，返回数据为：" + JSONObject.toJSONString(detail);
        result += "<br>=============================================<br>";
        JSONObject recall = adminService.recall4Admin(detail, uatEnvs.get("admin"));
        log.info("撤回成功，返回数据为：{}", JSONObject.toJSONString(recall));
        result += "撤回成功，返回数据为：" + JSONObject.toJSONString(recall);
        result += "<br>=============================================<br>";
        String modifyJson = "{\"announceContentKey\":{\"contentKey1\":\"1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"copyable\":1,\"commentFlag\":1,\"announceName\":\"制度测试\",\"officialDocNum\":\"制度测试\",\"oaDeptName\":\"zhiyuanxuetang\",\"belongSubject\":\"ABOUT_BRANCH_COMPANY\",\"fileName\":\"圆通标准体系E-R图.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2DLFay2UI3480J3RBmSNNN7l8q8IL9n4+76UaKclzV+8wm9YsRpARHa/QpnCdSN7LKMNPExDECLvswalZMh311QYa7NZdxyGa03Z4uILuTDw==\",\"appendixName\":\"test.pdf\"}],\"relatedAnnounceIds\":[80002,83476],\"relatedTables\":[\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenjQHIin2+stXWqI3Xx2sdCBl55DtuA/8N7jMkXMIBYNxLnsycUVMn19MxwfnUxYDt\",\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenlCJ4xBodFmvrCu+z4fflNKvghjaqljl5cQOg0Z8SAr4Wvzcj5ZGdAbdCUR2o/D50\"],\"catalogs\":[{\"title\":\"1\",\"pageNum\":\"1\",\"id\":\"17392611525714\",\"level\":1}],\"announceType\":3,\"templateId\":\"15a2167e5abf281881a8b9a4a539b03f\",\"belongColumnCode\":\"coldYB4JMxo\",\"nodeHandlers\":{\"N21\":[\"00000001\"]},\"belongSectionCode\":\"sec0AScB1Ob\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2svsexVcS+E6v6N5fkkE5KrQAjm2iRO5HG0AuUoBMA2Rx+CVcgjS/4QaDvF8mPfG2nvL1n7GVdqkm9tfOV7CIz1ZiZirYZmSQCAajBHXmYPg==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2svsexVcS+E6v6N5fkkE5KrQAjm2iRO5HG0AuUoBMA2W5VkRNwClv6e0yFKbBd6yPS0XiP2jcwZYxNmSDNGhMQVsxgmi1QOz4cR7EnGBxrTQ==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3mZk+yoCGQeeDxVNExmeEXz2k8iw2opgegM+Cb3rDCHz/4UFN32ag7iVSzKmB+oPRNVmjaF3iRlDEot5IYNsnYqYP1NEXHPzrOnxfBbShxkQ==\",\"textType\":2,\"relatedTableNames\":[\"测试(1).pdf\",\"科技人员(1).xlsx\"],\"language\":\"zh-CN\"}\n";
        JSONObject modifyJsonObject = JSONObject.parseObject(modifyJson);
        modifyJsonObject.put("announceId", id);
        JSONObject modified = adminService.modify4Admin(modifyJsonObject, uatEnvs.get("admin"));
        log.info("修改成功，返回数据为：{}", JSONObject.toJSONString(modified));
        result += "修改成功，返回数据为：" + JSONObject.toJSONString(modified);
        result += "<br>=============================================<br>";

        return result;
    }

    @GetMapping("/add/standard/test")
    public String addStandard() throws Exception {
        String result = "";
        String json = "{\"announceContentKey\":{\"contentKey1\":\"1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"copyable\":1,\"commentFlag\":1,\"announceName\":\"标准测试\",\"officialDocNum\":\"标准测试\",\"oaDeptName\":\"zhiyuanxuetang\",\"belongSubject\":\"ABOUT_BRANCH_COMPANY\",\"fileName\":\"document.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv0vGest4mszAnZto3x+DC0JBVVxtHybNO8GyUmSOWLn+m801MV/3lbF6OPROtZAscwQR/ygJcuImQxd0TWtKGb7cuknFsmhdh0iTNVxSB/Nuw==\",\"appendixName\":\"圆通标准体系E-R图.pdf\"},{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv1yuGFJZuQp8N+VUPc5sQf2VkQaOZHUSo5Nhgw1CWcxuWTSSt5ZY2odbJho3QqpHZo1uNujO2jwozqBfxHoQqvmv0tmVCG80oYnODUrouOFhw==\",\"appendixName\":\"运盟承诺书测试.pdf\"}],\"relatedAnnounceIds\":[83476,83477],\"relatedTables\":[\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenjQHIin2+stXWqI3Xx2sdCBl55DtuA/8N7jMkXMIBYNxLnsycUVMn19MxwfnUxYDt\",\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenlCJ4xBodFmvrCu+z4fflNKvghjaqljl5cQOg0Z8SAr4Wvzcj5ZGdAbdCUR2o/D50\"],\"announceType\":4,\"templateId\":\"15a21678cbf7a78305372994fe684dde\",\"belongColumnCode\":\"colNMIQs2qY\",\"belongSectionCode\":\"sec0AScB1Ob\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3i8HfbJpqHfiHfr/phQPWEgVb5NFV4VJud4HFzGMnfcwaN/DuAEt6Nnlj8ieilOB9zvnk2dOUydGlrHI4T0csa34DrcEIG4vu+231YvywQfQ==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3i8HfbJpqHfiHfr/phQPWEgVb5NFV4VJud4HFzGMnfc8MPXIwWb1NFFYmG6uBLzJMQHyVL+fqzD2WLLk1dsqmw+eZooMG8e4bk2UnttH3zBQ==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36DACx0iYj5iKA1sRDklfbK8sVnxd/ITUWRAgUrBCcUTi7TICJ8dwC0251ek+I+HfRskj1ta7ImRahfGaXy26bLV3WVxQkthaA3rFdx5OAIQ==\",\"textType\":2,\"relatedTableNames\":[\"测试(1).pdf\",\"科技人员(1).xlsx\"],\"language\":\"zh-CN\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer id = adminService.addMassive(jsonObject, uatEnvs.get("admin"));

        log.info("发文成功，返回id为：{}", id);
        result += "发文成功，返回id为：" + id;
        Thread.sleep(10 * 1000);
        JSONObject detail = adminService.detail4Admin(id, uatEnvs.get("admin"));
        log.info("获取详情成功，返回数据为：{}", JSONObject.toJSONString(detail));
        result += "获取详情成功，返回数据为：" + JSONObject.toJSONString(detail);
        result += "<br>=============================================<br>";
        JSONObject recall = adminService.recall4Admin(detail, uatEnvs.get("admin"));
        log.info("撤回成功，返回数据为：{}", JSONObject.toJSONString(recall));
        result += "撤回成功，返回数据为：" + JSONObject.toJSONString(recall);
        result += "<br>=============================================<br>";
        String modifyJson = "{\"announceContentKey\":{\"contentKey1\":\"1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"copyable\":1,\"commentFlag\":1,\"announceName\":\"标准测试\",\"officialDocNum\":\"标准测试\",\"oaDeptName\":\"zhiyuanxuetang\",\"belongSubject\":\"ABOUT_BRANCH_COMPANY\",\"fileName\":\"document.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv0vGest4mszAnZto3x+DC0JBVVxtHybNO8GyUmSOWLn+m801MV/3lbF6OPROtZAscwQR/ygJcuImQxd0TWtKGb7cuknFsmhdh0iTNVxSB/Nuw==\",\"appendixName\":\"圆通标准体系E-R图.pdf\"},{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv1yuGFJZuQp8N+VUPc5sQf2VkQaOZHUSo5Nhgw1CWcxuWTSSt5ZY2odbJho3QqpHZo1uNujO2jwozqBfxHoQqvmv0tmVCG80oYnODUrouOFhw==\",\"appendixName\":\"运盟承诺书测试.pdf\"}],\"relatedAnnounceIds\":[83476,83477],\"relatedTables\":[\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenjQHIin2+stXWqI3Xx2sdCBl55DtuA/8N7jMkXMIBYNxLnsycUVMn19MxwfnUxYDt\",\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenlCJ4xBodFmvrCu+z4fflNKvghjaqljl5cQOg0Z8SAr4Wvzcj5ZGdAbdCUR2o/D50\"],\"announceType\":4,\"templateId\":\"15a21678cbf7a78305372994fe684dde\",\"belongColumnCode\":\"colNMIQs2qY\",\"belongSectionCode\":\"sec0AScB1Ob\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3i8HfbJpqHfiHfr/phQPWEgVb5NFV4VJud4HFzGMnfcwaN/DuAEt6Nnlj8ieilOB9zvnk2dOUydGlrHI4T0csa34DrcEIG4vu+231YvywQfQ==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3i8HfbJpqHfiHfr/phQPWEgVb5NFV4VJud4HFzGMnfc8MPXIwWb1NFFYmG6uBLzJMQHyVL+fqzD2WLLk1dsqmw+eZooMG8e4bk2UnttH3zBQ==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv36DACx0iYj5iKA1sRDklfbK8sVnxd/ITUWRAgUrBCcUTi7TICJ8dwC0251ek+I+HfRskj1ta7ImRahfGaXy26bLV3WVxQkthaA3rFdx5OAIQ==\",\"textType\":2,\"relatedTableNames\":[\"测试(1).pdf\",\"科技人员(1).xlsx\"]}\n";
        JSONObject modifyJsonObject = JSONObject.parseObject(modifyJson);
        modifyJsonObject.put("announceId", id);
        JSONObject modified = adminService.modify4Admin(modifyJsonObject, uatEnvs.get("admin"));
        log.info("修改成功，返回数据为：{}", JSONObject.toJSONString(modified));
        result += "修改成功，返回数据为：" + JSONObject.toJSONString(modified);
        result += "<br>=============================================<br>";

        return result;
    }

    @GetMapping("/add/system/test")
    public String addSystem() throws Exception {
        String result = "";
        String json = "{\"announceContentKey\":{\"contentKey1\":\"1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"copyable\":1,\"commentFlag\":1,\"announceName\":\"制度测试\",\"officialDocNum\":\"制度测试\",\"oaDeptName\":\"zhiyuanxuetang\",\"belongSubject\":\"LET_EMPLOYEE_HAPPINESS\",\"fileName\":\"圆通标准体系E-R图.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2JVBPHf++mrjVBfaHIoNXGXDOWPALF4mnswHgWmqOXYhARuYq4Lyp+Hz6sda+NRri/bIALMy0fmbI4eo5Pz1ZVy6Ry6rv4DaUAfILtVKcJQg==\",\"appendixName\":\"运盟承诺书测试.pdf\"},{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3yZtpoTb7dXwQp4Lr0RYTUla/ZZRXT72CR/m98tclHRzFAt5b335YfIuctWbKmnkLQ3tm7NEBdpcDNYroLkiZt+39vfTadtXYQ7L4rwJ+GdQ==\",\"appendixName\":\"test.pdf\"}],\"relatedAnnounceIds\":[83477,83499],\"relatedTables\":[\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenjQHIin2+stXWqI3Xx2sdCBl55DtuA/8N7jMkXMIBYNxLnsycUVMn19MxwfnUxYDt\",\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenlCJ4xBodFmvrCu+z4fflNKvghjaqljl5cQOg0Z8SAr4Wvzcj5ZGdAbdCUR2o/D50\"],\"announceType\":5,\"templateId\":\"15a2167bec2f3f55320bd854f1586058\",\"belongColumnCode\":\"colNMIQs2qY\",\"nodeHandlers\":{\"N21\":[\"00000001\"]},\"belongSectionCode\":\"sec0AScB1Ob\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2zrRiXBPo5JZM/UMO73exT+o0a+W1BU8tDU17ikwupC1GDThQuSjqYlwIN+YOT4vuaKS6PHbfGr7R7houqHKpmkFIZ7vGbjo0P09xkoP1csA==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2zrRiXBPo5JZM/UMO73exT+o0a+W1BU8tDU17ikwupCzQqiP14Rq06Uk6arYSRsHRgq3O6MqCCpa5QyfG6Iqg8o5+1JdXj4cT3GhgKM3m7Pg==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2K45Bde0uk18XPoN62h8IJtN8nIJSvRUG8JX8tdvi1z8updZenNUhoWLmlxonNRTL4x4ucTFSm5v4IytxMeItuR0AO33I3UjgpjvjymnG9Zw==\",\"textType\":2,\"relatedTableNames\":[\"测试(1).pdf\",\"科技人员(1).xlsx\"],\"language\":\"zh-CN\"}\n";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer id = adminService.addMassive(jsonObject, uatEnvs.get("admin"));

        log.info("发文成功，返回id为：{}", id);
        result += "发文成功，返回id为：" + id;
        Thread.sleep(10 * 1000);
        JSONObject detail = adminService.detail4Admin(id, uatEnvs.get("admin"));
        log.info("获取详情成功，返回数据为：{}", JSONObject.toJSONString(detail));
        result += "获取详情成功，返回数据为：" + JSONObject.toJSONString(detail);
        result += "<br>=============================================<br>";
        JSONObject recall = adminService.recall4Admin(detail, uatEnvs.get("admin"));
        log.info("撤回成功，返回数据为：{}", JSONObject.toJSONString(recall));
        result += "撤回成功，返回数据为：" + JSONObject.toJSONString(recall);
        result += "<br>=============================================<br>";
        String modifyJson = "{\"announceContentKey\":{\"contentKey1\":\"1\",\"contentKey2\":\"2\",\"contentKey3\":\"3\",\"contentKey4\":\"4\",\"contentKey5\":\"5\"},\"copyable\":1,\"commentFlag\":1,\"announceName\":\"制度测试\",\"officialDocNum\":\"制度测试\",\"oaDeptName\":\"zhiyuanxuetang\",\"belongSubject\":\"LET_EMPLOYEE_HAPPINESS\",\"fileName\":\"圆通标准体系E-R图.pdf\",\"appendixList\":[{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2JVBPHf++mrjVBfaHIoNXGXDOWPALF4mnswHgWmqOXYhARuYq4Lyp+Hz6sda+NRri/bIALMy0fmbI4eo5Pz1ZVy6Ry6rv4DaUAfILtVKcJQg==\",\"appendixName\":\"运盟承诺书测试.pdf\"},{\"appendixPathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv3yZtpoTb7dXwQp4Lr0RYTUla/ZZRXT72CR/m98tclHRzFAt5b335YfIuctWbKmnkLQ3tm7NEBdpcDNYroLkiZt+39vfTadtXYQ7L4rwJ+GdQ==\",\"appendixName\":\"test.pdf\"}],\"relatedAnnounceIds\":[83477,83499],\"relatedTables\":[\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenjQHIin2+stXWqI3Xx2sdCBl55DtuA/8N7jMkXMIBYNxLnsycUVMn19MxwfnUxYDt\",\"tkYU9eBRFxualBvYrdHI31SHnftxwiKkXVOIFzEWLsEaEAiOx07ylUflPrViZVenlCJ4xBodFmvrCu+z4fflNKvghjaqljl5cQOg0Z8SAr4Wvzcj5ZGdAbdCUR2o/D50\"],\"announceType\":5,\"templateId\":\"15a2167bec2f3f55320bd854f1586058\",\"belongColumnCode\":\"colNMIQs2qY\",\"nodeHandlers\":{\"N21\":[\"00000001\"]},\"belongSectionCode\":\"sec0AScB1Ob\",\"contentV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2zrRiXBPo5JZM/UMO73exT+o0a+W1BU8tDU17ikwupC1GDThQuSjqYlwIN+YOT4vuaKS6PHbfGr7R7houqHKpmkFIZ7vGbjo0P09xkoP1csA==,HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2zrRiXBPo5JZM/UMO73exT+o0a+W1BU8tDU17ikwupCzQqiP14Rq06Uk6arYSRsHRgq3O6MqCCpa5QyfG6Iqg8o5+1JdXj4cT3GhgKM3m7Pg==\",\"filePathV2\":\"HLCNbvx4t5rvC5/Dt9y41r1jGUMfqxAWQBePZTRmJv2K45Bde0uk18XPoN62h8IJtN8nIJSvRUG8JX8tdvi1z8updZenNUhoWLmlxonNRTL4x4ucTFSm5v4IytxMeItuR0AO33I3UjgpjvjymnG9Zw==\",\"textType\":2,\"relatedTableNames\":[\"测试(1).pdf\",\"科技人员(1).xlsx\"]}";
        JSONObject modifyJsonObject = JSONObject.parseObject(modifyJson);
        modifyJsonObject.put("announceId", id);
        JSONObject modified = adminService.modify4Admin(modifyJsonObject, uatEnvs.get("admin"));
        log.info("修改成功，返回数据为：{}", JSONObject.toJSONString(modified));
        result += "修改成功，返回数据为：" + JSONObject.toJSONString(modified);
        result += "<br>=============================================<br>";

        return result;
    }


}
