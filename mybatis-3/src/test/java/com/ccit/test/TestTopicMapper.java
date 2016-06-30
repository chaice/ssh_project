package com.ccit.test;

import com.ccit.mappers.TopicMapper;
import com.ccit.pojo.Topic;
import com.ccit.utils.MyBatis;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;


public class TestTopicMapper {
    Logger logger = LoggerFactory.getLogger(TestSqlSession.class);
    SqlSession sqlSession = MyBatis.getSqlSession();
    TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);

    @Test
    public void testDelete(){
        topicMapper.delete(2);
        logger.debug("执行删除，id为{}",2);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testInsert(){
        Topic topic = new Topic("mybatis的使用","16-6-29");
        topicMapper.insert(topic);
        logger.debug("向表中添加topic:{}",topic);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testFindOneById(){
        Topic topic = topicMapper.findOneById(1);
        logger.debug("id=1的topic为：{}",topic);
        sqlSession.close();
    }
    @Test
    public void testFindAll(){
        List<Topic> topicList = topicMapper.findAll();
        for (Topic topic:topicList) {
            logger.debug("topic:{}",topic);
        }
        sqlSession.close();
    }
    @Test
    public void testFindByIds(){
        List<Topic> topicList = topicMapper.findByIds(1,3,4,5);
        for (Topic topic:topicList) {
            logger.debug("topic:{}",topic);
        }
        sqlSession.close();
    }
    @Test
    public void testFindByIf(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("title","各种代码高亮演示");
        map.put("userid",11);
        List<Topic> topicList = topicMapper.findByIf(map);
        for (Topic topic:topicList) {
            logger.debug("topic:{}",topic);
        }
        sqlSession.close();
    }
    @Test
    public void testDeleteByIds(){
        topicMapper.deleteByIds(7);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testInserts(){
        List<Topic> list = Lists.newArrayList(new Topic("java入门","16"),
                new Topic("mybatis","17"),new Topic("mybatis内链接","18"),new Topic("idea的使用","16"));
        topicMapper.inserts(list);
        sqlSession.commit();
        sqlSession.close();
    }

}
