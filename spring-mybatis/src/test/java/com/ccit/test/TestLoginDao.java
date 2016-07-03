package com.ccit.test;


import com.ccit.mappers.LoginDao;
import com.ccit.pojo.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestLoginDao {
    @Inject
    private LoginDao loginDao;
    @Test
    public void testSave(){
        loginDao.save(new Login("212.121.22",1));
    }
}
