package com.mingyuan.wxy.tools.autotest.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String instant2String(Instant instant) {
        return instant2String(instant, STANDARD_FORMAT);
    }

    public static String instant2String(Instant instant, String format) {
        // 将 Instant 转换为 LocalDateTime（需要指定时区）
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        // 定义日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        // 格式化 LocalDateTime 为字符串
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    public static String currMonthFirstDay(Instant instant, String format) {
        // 获取当前日期
        LocalDate today = LocalDate.now();

        // 获取本月1号
        LocalDate firstDayOfMonth = today.withDayOfMonth(1);

        // 转换为LocalDateTime并设置时间为00:00:00
        LocalDateTime firstDayWithTime = firstDayOfMonth.atStartOfDay();

        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化输出
        return firstDayWithTime.format(formatter);

    }
}
