package com.mingyuan.wxy.orm;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

public class MyBatisTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(1,10);
        User user = userMapper.selectById(1L);
    }
}
