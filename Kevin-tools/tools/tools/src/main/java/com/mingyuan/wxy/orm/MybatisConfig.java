//package com.mingyuan.wxy.orm;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration //声明该类是核心配置类
//@ComponentScan("com.xc") //开启spring注解扫描
//@MapperScan("com.xc.mapper") //MyBatis扫描Mapper接口
//public class MybatisConfig {
//    @Bean
//    public DataSource dataSource() {
//        DataSource driverManagerDataSource = new MysqlDataSource();
////        driverManagerDataSource.setPassword("root");
////        driverManagerDataSource.setUsername("root");
////        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
////        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
//        return driverManagerDataSource;
//    }
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        return sqlSessionFactoryBean;
//    }
//    // 执行操作
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);
//        UserMapper userMapper = context.getBean(UserMapper.class);
//        User user = userMapper.selectById(100L);
//        System.out.println(user);
//    }
//}