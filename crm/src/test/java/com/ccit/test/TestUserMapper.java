package com.ccit.test;

import com.ccit.mappers.UserMapper;
import com.ccit.pojo.User;
import com.ccit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestUserMapper {
    Logger logger = LoggerFactory.getLogger(TestUserMapper.class);
    @Inject
    private UserService userService;
    @Test
    public void testFind(){
        User user = userService.findUser("admin");
        logger.debug("user:{}",user);
    }

}
