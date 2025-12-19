package com.mingyuan.wxy.tools.config;

import com.mingyuan.wxy.tools.ProjectEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

import static com.mingyuan.wxy.tools.base.AppBase.assertExist;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbConfig {

    private Map<ProjectEnum, ProjectConfig> conf = new EnumMap<>(ProjectEnum.class);

    public ProjectConfig configBy(ProjectEnum project) {
        assertExist(project, "获取系统配置-系统配置不能为空");
        return assertExist(() -> conf.get(project),
                "获取配置-配置信息不存在");
    }

}
