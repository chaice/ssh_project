package com.ccit.test;

import com.ccit.mappers.TopicMapper;
import com.ccit.pojo.Topic;
import com.ccit.utils.MyBatis;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class TestTopic {
    private SqlSession sqlSession = MyBatis.getSqlSession();
    private TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);
    private Logger logger = LoggerFactory.getLogger(TestTopic.class);
    @Test
    public void testFindById(){
        List<Topic> list = topicMapper.findByTopicId(1);
        for (Topic topic : list) {
            logger.debug("username:{}->useravatar:{}",topic.getUser().getUsername(),topic.getUser().getAvatar());
            logger.debug("nodename:{}",topic.getNode().getNodename());
            logger.debug("topic:{}",topic);
        }

    }

}
