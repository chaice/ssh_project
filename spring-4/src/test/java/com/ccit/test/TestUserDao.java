package com.ccit.test;


import com.ccit.mappers.UserDao;
import com.ccit.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestUserDao {

    @Inject
    private UserDao userDao;

    @Test
    public void testFindByUserName(){
        User user = userDao.findByUserName("tom");
        Assert.assertNotNull(user);
        System.out.println(user);
    }
}
