package com.ccit.test;

import com.ccit.mappers.UserMapper;
import com.ccit.pojo.User;
import com.ccit.utils.MyBatis;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUser {
    Logger logger = LoggerFactory.getLogger(TestUser.class);
    SqlSession sqlSession = MyBatis.getSqlSession();
   @Test
    public void testSql(){
        User user = sqlSession.selectOne("com.ccit.mappers.UserMapper.findById",17);
       logger.debug("user:{}",user);
       sqlSession.close();
    }
    @Test
    public void testIf(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("username","tom");
        map.put("password",123123);
        List<User> list = sqlSession.getMapper(UserMapper.class).findByIf(map);
        logger.debug("list:{}",list);
        sqlSession.close();
    }
    @Test
    public void testFindByIds(){
        List<User> list = sqlSession.getMapper(UserMapper.class).findByIds(13,15,16,17);
        logger.debug("list:{}",list);
        sqlSession.close();
    }
    @Test
    public void testDeleteByIds(){
        sqlSession.getMapper(UserMapper.class).deleteByIds(13,15,16,17);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testInserts(){
        User user1 = new User("tom","123");
        User user2 = new User("jim","123");
        User user3 = new User("qom","123");
        User user4 = new User("wom","123");
        User user5 = new User("eom","123");
        List<User> list = Lists.newArrayList(user1, user2, user3, user4, user5);
        sqlSession.getMapper(UserMapper.class).inserts(list);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testFindResultMapById(){
        List<User> userList = sqlSession.getMapper(UserMapper.class).findResultMapById(11);
        for (User user:userList) {
            logger.debug("user:{}",user);
        }
        sqlSession.close();
    }


}
