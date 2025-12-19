package com.mingyuan.wxy.tools.util;

import java.text.DecimalFormat;

public class NumberFormat {
    public static void main(String[] args) {
        double amount = 1100640555.83;

        // 定义格式（千位分隔符 + 保留两位小数）
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        String formattedAmount = formatter.format(amount);

        System.out.println(formattedAmount); // 输出：1,100,640,555.83
    }
}
