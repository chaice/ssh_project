package com.ccit.test;

import com.ccit.pojo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSpring {
    Logger logger = LoggerFactory.getLogger(TestSpring.class);
    @Test
    public void testContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = (User) context.getBean("user");
        User user1 = (User) context.getBean("user");
        logger.debug("user:{}",user==user1);
        user.getUser();
//        System.out.println(user.bookName);
    }
}
