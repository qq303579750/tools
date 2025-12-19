package com.mingyuan.wxy.tools.jdbc;

import com.mingyuan.wxy.tools.ProjectEnum;
import com.mingyuan.wxy.tools.config.DbConfig;
import com.mingyuan.wxy.tools.config.ProjectConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JdbcService {


    // 数据库连接信
    private final DbConfig dbConfig;

    public String select(String sql) {
        Connection connection = null;
        try {
            ProjectConfig projectConfig = dbConfig.configBy(ProjectEnum.CTMS);
            // 1. 加载驱动（MySQL 8.0+ 可以省略这步）
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立连接
            connection = DriverManager.getConnection(projectConfig.getUrl(), projectConfig.getUser(), projectConfig.getPd());
            System.out.println("数据库连接成功！");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                System.out.printf("ID: %d, 姓名: %s, 邮箱: %s, 年龄: %d%n",
                        id, name, email, age);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public String upsert(ParamRequest paramRequest) {
        String dsl = new String(Base64.getDecoder().decode(paramRequest.getDsl()));
        Connection conn = null;
        try {
            String project = paramRequest.getProject();
            ProjectConfig projectConfig = dbConfig.configBy(ProjectEnum.get(project));
            // 1. 加载驱动（MySQL 8.0+ 可以省略这步）
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. 建立连接
            conn = DriverManager.getConnection(projectConfig.getUrl(), projectConfig.getUser(), projectConfig.getPd());
            System.out.println("数据库连接成功！");
            PreparedStatement pstmt = conn.prepareStatement(dsl);
            int rows = pstmt.executeUpdate();
            System.out.println("更新" + rows + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
