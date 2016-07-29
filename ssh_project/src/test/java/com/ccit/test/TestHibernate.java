package com.ccit.test;

import com.ccit.pojo.User;
import com.ccit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:applicationContext.xml")
public class TestHibernate {
    @Inject
    private UserService userService;

    @Test
    public void test(){
      List<User> userList = userService.findAll();
        System.out.println(userList);
    }
}
