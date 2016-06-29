package com.ccit.test;

import com.ccit.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cc on 2016/6/29.
 */
public class TestSqlSession {
    Logger logger = LoggerFactory.getLogger(TestSqlSession.class);
    @Test
    public void testSqlSession(){
        SqlSession sqlSession = MyBatis.getSqlSession();
        logger.debug("sqlSession:{}",sqlSession);
    }
}
