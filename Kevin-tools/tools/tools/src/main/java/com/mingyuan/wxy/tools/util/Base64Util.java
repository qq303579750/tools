package com.mingyuan.wxy.tools.util;

import java.util.Base64;

public class Base64Util {

    public static String encode(String str) {
        return new String(Base64.getEncoder().encode(str.getBytes()));
    }

    public static String decode(String str) {
        return new String(Base64.getDecoder().decode(str.getBytes()));
    }

    public static void main(String[] args) {
        String[] arr = {
               "update t_saas_contract_field set field_value=to_base64(AES_ENCRYPT(field_value,'N3VMezpTanM1Jg==')) where field_value regexp '^[1-9][0-9]{5}(18|19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9Xx]$' limit 200000;"
        };
        for (String sql : arr) {
            String format = "curl --location --request POST 'http://localhost:8080/jdbc/upsert' \\\n" +
                    "--header 'Content-Type: application/json' \\\n" +
                    "-d '{\n" +
                    "    \"dsl\":\"%s\",\n" +
                    "\t\"project\":\"CTMS\"\n" +
                    "}'";
            System.out.println(String.format(format, encode(sql)));
            System.err.println();
        }
    }

}
