package com.mingyuan.wxy.tools.gen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CodeGenerator4Flex {

    private static List<String> ignoreFields = new ArrayList<>();

    static {
        ignoreFields.add("id");
        ignoreFields.add("create_time");
        ignoreFields.add("create_user_code");
        ignoreFields.add("create_user_name");
        ignoreFields.add("update_time");
        ignoreFields.add("update_user_code");
        ignoreFields.add("update_user_name");
        ignoreFields.add("deleted");
    }

    public static void main(String[] args) throws Exception {
        String tableComment = "角色表";
        String tableName = "base__resource";

        String url = "jdbc:mysql://10.130.15.114:3306/yto_standard";
        // 数据库用户名
        String user = "yto_standard";
        // 数据库密码
        String password = "asdJd2n03kdo$c";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "SELECT COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE FROM information_schema.columns WHERE table_schema = 'yto_standard' " +
                "  AND table_name = '" + tableName + "';";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        tableName="resource";

//        generateEntity(rs, tableComment, tableName);
//
//        generateModel(rs, tableComment, tableName);
//
//        generateRequestModel(rs, tableComment, tableName);
//
//        generateMapper(tableName);
//

//        generateController(tableComment, tableName);
//
        generateServiceImpl(tableComment, tableName);
    }

    private static void generateServiceImpl(String tableComment, String tableName) {
        String modelName = upperFirstLetter(underscoreToCamel(tableName));
        String modelVariableName = underscoreToCamel(tableName);
        String serviceName = modelName + "Service";
        String mapperName = modelName + "Mapper";
        String variableMapperName = underscoreToCamel(tableName) + "Mapper";
        String variableServiceName = underscoreToCamel(tableName) + "Service";
        String queryName = modelName + "Query";
        String requestName = modelName + "Request";
        System.err.println(
                "import cn.yto.standard.admin.domain.service." + serviceName + ";\n" +
                        "import cn.yto.standard.infrastructure.base.AppPage;\n" +
                        "import cn.yto.standard.infrastructure.mapper." + mapperName + ";\n" +
                        "import cn.yto.standard.infrastructure.model." + modelName + ";\n" +
                        "import cn.yto.standard.infrastructure.model.request." + modelName + "Query;\n" +
                        "import cn.yto.standard.infrastructure.model.request." + modelName + "Request;\n" +
                        "import com.alibaba.fastjson.JSONObject;\n" +
                        "import lombok.extern.slf4j.Slf4j;\n" +
                        "import org.springframework.stereotype.Service;\n" +
                        "\n" +
                        "@Slf4j\n" +
                        "@Service\n" +
                        "@RequiredArgsConstructor\n" +
                        "public class " + serviceName + "Impl implements " + serviceName + " {\n" +
                        "    private final " + mapperName + " " + variableMapperName + ";\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public AppPage<" + modelName + "> search(" + modelName + "Query query) {\n" +
                        "        log.info(\"" + serviceName + ".search request: {}\", JSONObject.toJSONString(query));\n" +
                        "        AppPage<" + modelName + "> page = " + variableMapperName + ".selectByPage(query);\n" +
                        "        log.info(\"" + serviceName + ".search response: {}\", JSONObject.toJSONString(page));\n" +
                        "        return page;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    @Transactional\n" +
                        "    public " + modelName + " add(" + modelName + "Request request) {\n" +
                        "        log.info(\"" + serviceName + ".add request: {}\", JSONObject.toJSONString(request));\n" +
                        "        " + modelName + " " + modelVariableName + " = " + variableMapperName + ".insert(request);\n" +
                        "        log.info(\"" + serviceName + ".add response: {}\", JSONObject.toJSONString(" + modelVariableName + "));\n" +
                        "        return " + modelVariableName + ";\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    @Transactional\n" +
                        "    public " + modelName + " modify(" + modelName + "Request request) {\n" +
                        "        log.info(\"" + serviceName + ".modify request: {}\", JSONObject.toJSONString(request));\n" +
                        "        " + modelName + " " + modelVariableName + " = " + variableMapperName + ".update(request);\n" +
                        "        log.info(\"" + serviceName + ".modify response: {}\", JSONObject.toJSONString(" + modelVariableName + "));\n" +
                        "        return " + modelVariableName + ";\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    @Transactional\n" +
                        "    public void remove(Long id) {\n" +
                        "        log.info(\"" + serviceName + ".remove request: {}\", id);\n" +
                        "        " + variableMapperName + ".deleteById(id);\n" +
                        "        log.info(\"" + serviceName + ".remove finish \");\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public " + modelName + " detail(Long id) {\n" +
                        "        log.info(\"" + serviceName + ".detail request: {}\", id);\n" +
                        "        " + modelName + " " + modelVariableName + " = " + variableMapperName + ".selectOne(id);\n" +
                        "        log.info(\"" + serviceName + ".detail response: {}\", JSONObject.toJSONString(" + modelVariableName + "));\n" +
                        "        return " + modelVariableName + ";\n" +
                        "    }\n" +
                        "}"
        );
    }

    private static void generateController(String tableComment, String tableName) {

        String modelName = upperFirstLetter(underscoreToCamel(tableName));
        String controllerName = modelName + "Controller";
        String serviceName = modelName + "Service";
        String variableServiceName = underscoreToCamel(tableName) + "Service";
        String queryName = modelName + "Query";
        String requestName = modelName + "Request";
        System.err.println("import cn.yto.standard.admin.domain.service.JargonService;\n" +
                "import cn.yto.standard.infrastructure.base.AppPage;\n" +
                "import cn.yto.standard.infrastructure.model.Jargon;\n" +
                "import cn.yto.standard.infrastructure.model.request.JargonQuery;\n" +
                "import cn.yto.standard.infrastructure.model.request.JargonRequest;\n" +
                "import io.swagger.annotations.Api;\n" +
                "import io.swagger.annotations.ApiOperation;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestParam;\n" +
                "import org.springframework.web.bind.annotation.RestController;");
        System.err.println("@Api(tags = \"" + tableComment + "管理\")\n" +
                "@RequestMapping(\"/" + underscoreToCamel(tableName) + "\")\n" +
                "@RequiredArgsConstructor\n" +
                "@RestController");
        System.err.println("public class " + controllerName + " {");

        System.err.println(" @PostMapping({\"/search\"})\n" +
                "    @ApiOperation(\"" + tableComment + "：分页查询\")\n" +
                "    public AppPage<" + modelName + "> search(@RequestBody " + queryName + " query) {\n" +
                "        return " + variableServiceName + ".search(query);\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping({\"/add\"})\n" +
                "    @ApiOperation(\"" + tableComment + "：新增\")\n" +
                "    public " + modelName + " add(@RequestBody " + requestName + " request) {\n" +
                "        return " + variableServiceName + ".add(request);\n" +
                "    }\n" +
                "\n" +
                "    @PostMapping({\"/modify\"})\n" +
                "    @ApiOperation(\"" + tableComment + "：编辑\")\n" +
                "    public " + modelName + " modify(@RequestBody " + requestName + " request) {\n" +
                "        return " + variableServiceName + ".modify(request);\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping({\"/remove\"})\n" +
                "    @ApiOperation(\"" + tableComment + "：删除\")\n" +
                "    public void modify(@RequestParam Long id) {\n" +
                "        " + variableServiceName + ".remove(id);\n" +
                "    }\n" +
                "\n" +
                "    @GetMapping({\"/detail\"})\n" +
                "    @ApiOperation(\"" + tableComment + "：详情\")\n" +
                "    public " + modelName + " detail(@RequestParam Long id) {\n" +
                "        return " + variableServiceName + ".detail(id);\n" +
                "    }");

        System.err.println("}");
    }

    private static void generateMapper(String tableName) {
        String modelName = upperFirstLetter(underscoreToCamel(tableName));
        System.err.println("import cn.yto.standard.infrastructure.base.AppPage;\n" +
                "import cn.yto.standard.infrastructure.mapper.mysql.entity.OuterStandardEntity;\n" +
                "import cn.yto.standard.infrastructure.model.OuterStandard;\n" +
                "import cn.yto.standard.infrastructure.model.request.OuterStandardQuery;\n" +
                "import cn.yto.standard.infrastructure.model.request.OuterStandardRequest;\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import com.baomidou.mybatisplus.core.metadata.IPage;\n" +
                "import org.apache.ibatis.annotations.Mapper;\n" +
                "import org.apache.ibatis.annotations.Param;\n" +
                "import org.apache.ibatis.annotations.Select;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "import static org.apache.commons.lang3.ObjectUtils.isEmpty;\n" +
                "import static cn.yto.standard.infrastructure.base.AppBase.merge;\n" +
                "import static cn.yto.standard.infrastructure.base.AppBase.toBean;\n" +
                "import static cn.yto.standard.infrastructure.base.AppBase.toList;\n" +
                "import static com.baomidou.mybatisplus.core.toolkit.Wrappers.lambdaQuery;");
        System.err.println("@Repository\n" +
                "@Mapper");
        System.err.println("public interface " + modelName + "Mapper extends BaseMapper<" + modelName + "Entity> {");
        System.err.println("default AppPage<" + modelName + "> selectByPage(" + modelName + "Query query) {\n" +
                "        IPage<" + modelName + "Entity> iPage = AppPage.by(\n" +
                "                query.getPageNum(),\n" +
                "                query.getPageSize()\n" +
                "        );\n" +
                "        IPage<" + modelName + "Entity> page = selectPage(iPage, lambdaQuery(" + modelName + "Entity.class)\n" +
                "                .orderByDesc(" + modelName + "Entity::getUpdateTime)\n" +
                "                .orderByAsc(" + modelName + "Entity::getId)\n" +
                "        );\n" +
                "        return AppPage.of(page).transform(entity -> toBean(entity, " + modelName + ".class));\n" +
                "    }");

        System.err.println(" default " + modelName + " insert(" + modelName + "Request request) {\n" +
                "        " + modelName + "Entity bean = toBean(request, " + modelName + "Entity.class);\n" +
                "        insert(bean);\n" +
                "        return toBean(bean, " + modelName + ".class);\n" +
                "    }");
        String requestName = modelName + "Request";
        String entityName = modelName + "Entity";
        System.err.println("default " + modelName + " update(" + requestName + " request) {\n" +
                "        " + entityName + " old = AppBase.assertExist(selectById(request.getId()),\"数据不存在！\");\n" +
                "        " + entityName + " merged = merge(request, old);\n" +
                "        updateById(merged);\n" +
                "        return toBean(merged, " + modelName + ".class);\n" +
                "    }");

        System.err.println("default " + modelName + " selectOne(Long id) {\n" +
                "        " + entityName + " detail = selectById(id);\n" +
                "        if(ObjectUtils.isEmpty(detail)) { " +
                "            return null;" +
                "        }" +
                "        return toBean(detail, " + modelName + ".class);\n" +
                "    }");
        System.err.println("}");
    }

    private static void generateRequestModel(ResultSet rs, String tableComment, String tableName) throws SQLException {
        String modelName = upperFirstLetter(underscoreToCamel(tableName));
        System.err.println("import io.swagger.annotations.ApiModel;\n" +
                "import io.swagger.annotations.ApiModelProperty;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.Builder;\n" +
                "import lombok.Data;\n" +
                "import lombok.NoArgsConstructor;");
        System.err.println("@Builder\n" +
                "@Data\n" +
                "@AllArgsConstructor\n" +
                "@NoArgsConstructor\n" +
                "@ApiModel(\"" + tableComment + "Request类\")");
        System.err.println("public class " + modelName + "Request {");
        while (rs.next()) {
            if (!ignoreFields.contains(rs.getString("column_name"))) {
                System.err.println("@ApiModelProperty(\"" + rs.getString("COLUMN_COMMENT") + "\")");
                String dataType = rs.getString("DATA_TYPE");
                System.err.println("private " + CodeGenerator4Flex.typeHandler(dataType) + " " +
                        CodeGenerator4Flex.underscoreToCamel(rs.getString("column_name")) + ";");
            }
        }
        System.err.println("}");
    }

    public static void generateModel(ResultSet rs, String tableComment, String tableName) throws Exception {
        String modelName = upperFirstLetter(underscoreToCamel(tableName));
        System.err.println("import cn.yto.standard.infrastructure.base.AppBaseModel;\n" +
                "import com.fasterxml.jackson.annotation.JsonInclude;\n" +
                "import io.swagger.annotations.ApiModel;\n" +
                "import io.swagger.annotations.ApiModelProperty;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.Data;\n" +
                "import lombok.EqualsAndHashCode;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "import lombok.experimental.SuperBuilder;");
        System.err.println("@SuperBuilder\n" +
                "@AllArgsConstructor\n" +
                "@NoArgsConstructor\n" +
                "@Data\n" +
                "@EqualsAndHashCode(callSuper = true)\n" +
                "@ApiModel(\"" + tableComment + "model\")\n" +
                "@JsonInclude(JsonInclude.Include.NON_ABSENT)");
        System.err.println("public class " + modelName + " extends AppBaseModel {");
        while (rs.next()) {
            if (!ignoreFields.contains(rs.getString("column_name"))) {
                System.err.println("@ApiModelProperty(\"" + rs.getString("COLUMN_COMMENT") + "\")");
                String dataType = rs.getString("DATA_TYPE");
                System.err.println("private " + CodeGenerator4Flex.typeHandler(dataType) + " " +
                        CodeGenerator4Flex.underscoreToCamel(rs.getString("column_name")) + ";");
            }
        }
        System.err.println("}");
    }

    public static void generateEntity(ResultSet rs, String tableComment, String tableName) throws Exception {
        String modelName = CodeGenerator4Flex.upperFirstLetter(underscoreToCamel(tableName));

        System.err.println("import com.baomidou.mybatisplus.annotation.TableName;\n" +
                "import io.swagger.annotations.ApiModel;\n" +
                "import io.swagger.annotations.ApiModelProperty;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.Data;\n" +
                "import lombok.EqualsAndHashCode;\n" +
                "import lombok.NoArgsConstructor;\n" +
                "import lombok.experimental.SuperBuilder;");
        System.err.println("@SuperBuilder\n" +
                "@AllArgsConstructor\n" +
                "@NoArgsConstructor\n" +
                "@Data\n" +
                "@EqualsAndHashCode(callSuper = true)\n" +
                "@ApiModel(\"" + tableComment + "\")\n" +
                "@TableName(\"" + tableName + "\")");
        System.err.println("public class " + modelName + "Entity extends AppBaseModel {");
        while (rs.next()) {
            if (!ignoreFields.contains(rs.getString("column_name"))) {
                System.err.println("@ApiModelProperty(\"" + rs.getString("COLUMN_COMMENT") + "\")");
                String dataType = rs.getString("DATA_TYPE");
                System.err.println("private " + CodeGenerator4Flex.typeHandler(dataType) + " " +
                        CodeGenerator4Flex.underscoreToCamel(rs.getString("column_name")) + ";");
            }
        }
        System.err.println("}");
    }

    public static String upperFirstLetter(String str) {
        String substring = str.substring(0, 1);
        return substring.toUpperCase() + str.substring(1);
    }

    public static String typeHandler(String str) {
        if ("varchar".equals(str)) {
            return "String";
        } else if ("int".equals(str)) {
            return "Integer";
        } else if ("tinyInt".equals(str)) {
            return "Integer";
        } else if ("bigint".equals(str)) {
            return "Long";
        } else if ("text".equals(str)) {
            return "String";
        } else if ("datetime".equals(str)) {
            return "Instant";
        }
        return null;
    }

    public static String underscoreToCamel(String input) {
        if (input == null) {
            return input;
        }

        StringBuilder builder = new StringBuilder();
        boolean nextUpperCase = false;

        for (char c : input.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    builder.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    builder.append(Character.toLowerCase(c));
                }
            }
        }

        return builder.toString();
    }


}
