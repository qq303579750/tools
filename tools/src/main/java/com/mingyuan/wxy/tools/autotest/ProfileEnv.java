package com.mingyuan.wxy.tools.autotest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEnv {

    private String appKey;
    private String appSecret;
    private String urlPrefix = "http://contract-open-api-inter.yto56test.com/api";
    private String xApplicationCode = "ann-api";
    private String userCode = "02224961";

    public static void main(String[] args) {
        int corporate = 66;
        int industry = 44;
        int country = 55;
        Map<String, Integer> outerStandard = new LinkedHashMap<>();
        outerStandard.put("CORPORATE", corporate);
        outerStandard.put("INDUSTRY", industry);
        outerStandard.put("COUNTRY", country);

    }
}
