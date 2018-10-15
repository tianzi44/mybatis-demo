package com.panda521.dao;

import com.panda521.entity.UserDO;
import com.panda521.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by jack on 2018/10/15.
 */
public class UserDao {
    private SqlSessionFactory sqlSessionFactory;

    public UserDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public UserDO getUserInfo(Integer userId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDO userDO = sqlSession.selectOne("test.queryUserById",userId);
        sqlSession.close();
        return userDO;
    }

}