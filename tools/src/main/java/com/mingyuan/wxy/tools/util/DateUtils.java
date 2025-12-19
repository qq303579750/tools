package com.mingyuan.wxy.tools.util;

import com.mingyuan.wxy.tools.exception.CommonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date parse(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = sdf.parse(date);
            return date1;
        } catch (ParseException e) {
            throw new CommonException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Date date = parse("2020/1/1");
        String object = "2020/1/1";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.err.println(sdf.format(object));


    }
}
