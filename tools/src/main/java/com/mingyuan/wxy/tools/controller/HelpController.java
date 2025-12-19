package com.mingyuan.wxy.tools.controller;

import com.mingyuan.wxy.tools.autotest.announce.AdminController;
import com.mingyuan.wxy.tools.autotest.contract.ContractController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HelpController {

    private final ContractController contractController;
    private final AdminController adminController;

    @GetMapping("/contract")
    public String contract() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("一键测试：尽可能测试到更多的接口 <br>");
            String result = contractController.certGen("uat");
            sb.append("离职证明整套流程验证完成：").append(result)
                    .append("<br>")
                    .append("<br>")

                    .append("辞职信部分程验证完成：")
                    .append(contractController.terminationGen("uat"))
                    .append("请使用合同编号进行废弃")
                    .append("<br>")
                    .append("<br>")


                    .append("运盟确认函部分程验证完成：").append(contractController.yonmenGen("uat"))
                    .append("待乙方签约，甲方会自动盖章，请使用合同编号进行废弃")
                    .append("<br>")
                    .append("<br>")

                    .append("驿站主控协议部分程验证完成：").append(contractController.stationGen("uat"))
                    .append("待乙方和甲方签约（并行签约，无先后顺序），请使用合同编号进行废弃")
                    .append("<br>")
                    .append("<br>")

                    .append("租赁系统验证完成：").append(contractController.rentalGen("uat"))
                    .append("首次验证顺序：发起合同-->查询详情-->废弃合同-->结束")
                    .append("而次验证顺序：发起合同-->查询详情-->归档合同-->废弃合同-->结束")
                    .append("<br>")
                    .append("<br>")

                    .append("人资系统部分验证完成：").append(contractController.ehrGen("uat"))
                    .append("首次验证顺序：发起合同-->催签合同-->撤回合同-->修改合同-->结束")
                    .append("待乙方签约，甲方会自动盖章，请使用合同编号进行废弃")
                    .append("<br>")
                    .append("<br>")

                    .append("网点金刚系统部分验证完成：").append(contractController.portalGen("uat"))
                    .append("验证顺序：发起合同")
                    .append("待乙方签约，甲方会自动盖章，请使用合同编号进行废弃")
                    .append("<br>")
                    .append("<br>")


                    .append("PS部分验证完成：").append(contractController.psGen("uat"))
                    .append("验证顺序：发起合同")
                    .append("待乙方签约，甲方会自动盖章，请使用合同编号进行废弃")
                    .append("<br>")
                    .append("<br>")
            ;

            return sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @GetMapping("/announce/admin")
    public String announceAdmin() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("一键测试企业内网：尽可能测试到更多的接口 <br>")
                    .append("新增企业内网公告：")
                    .append(adminController.addMassive())
                    .append("请去OA审批，手动废弃：")
                    .append("<br>")
                    .append("<br>")

                    .append("新增企业内网新闻：")
                    .append(adminController.addNews())
                    .append("请去OA审批，手动废弃")
                    .append("<br>")
                    .append("<br>")

                    .append("新增企业内网规范性文档：")
                    .append(adminController.addInstitution())
                    .append("请去OA审批，手动废弃")
                    .append("<br>")
                    .append("<br>")

                    .append("新增企业内网标准：")
                    .append(adminController.addStandard())
                    .append("请去OA审批，手动废弃")
                    .append("<br>")
                    .append("<br>")

                    .append("新增企业内网规范：")
                    .append(adminController.addSystem())
                    .append("请去OA审批，手动废弃")
                    .append("<br>")
                    .append("<br>")


            ;

            return sb.toString();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
