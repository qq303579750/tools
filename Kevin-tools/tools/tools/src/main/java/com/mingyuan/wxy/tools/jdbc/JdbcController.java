package com.mingyuan.wxy.tools.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/jdbc")
@RequiredArgsConstructor
public class JdbcController {

    private final JdbcService jdbcService;

    @PostMapping("/select")
    public String select(@RequestBody Map<String, String> sql) {
        return jdbcService.select(sql.get("select"));
    }

    @PostMapping("/upsert")
    public String upsert(@RequestBody ParamRequest paramRequest) {
        return jdbcService.upsert(paramRequest);
    }

}
