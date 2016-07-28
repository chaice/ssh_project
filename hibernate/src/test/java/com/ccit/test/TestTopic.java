package com.ccit.test;

import com.ccit.pojo.Topic;
import com.ccit.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class TestTopic {
    @Test
    public void testFindById(){
        Session session  = HibernateUtil.getSession();
        session.beginTransaction();

        Topic topic = (Topic) session.get(Topic.class,1);
        System.out.println(topic.getTitle());
        System.out.println(topic.getTopicContent().getContent());

        session.getTransaction().commit();
    }
}
