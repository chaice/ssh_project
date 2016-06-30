package com.ccit.test;

import com.ccit.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSqlSession {
    Logger logger = LoggerFactory.getLogger(TestSqlSession.class);
    SqlSession sqlSession = MyBatis.getSqlSession();
    @Test
    public void testSqlSession(){
        logger.debug("sqlSession:{}",sqlSession);
    }

}
