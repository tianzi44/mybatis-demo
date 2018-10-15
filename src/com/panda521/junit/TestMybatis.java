package com.panda521.junit;

import com.panda521.dao.UserDao;
import com.panda521.entity.UserDO;
import com.panda521.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by jack on 2018/10/15.
 */
public class TestMybatis {
    private SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() throws Exception {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("resource/SqlMapConfig.xml");
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testQueryUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行SqlSession对象执行查询，获取结果User
        // 第一个参数是UserMapper1.xml的statement的id，第二个参数是执行sql需要的参数；
        Object user = sqlSession.selectOne("test.queryUserById", 1);
        sqlSession.close();
        System.out.println(user);
    }

    /**
     * 通过使用dao层 创建sqlsession ，访问数据库，获取数据。
     */
    @Test
    public void testQueryUserByDao() {
        UserDao userDao = new UserDao(this.sqlSessionFactory);
        UserDO userDO = userDao.getUserInfo(1);
        System.out.print(userDO);
    }

    /**
     * 通过mybatis代理(代理构造了SqlSession的selectOne操作)，操作数据库
     */
    @Test
    public void testQueryUserByMapper() {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserDO userDO = userMapper.queryUserById(1);
        sqlSession.close();
        System.out.print(userDO);
    }
}