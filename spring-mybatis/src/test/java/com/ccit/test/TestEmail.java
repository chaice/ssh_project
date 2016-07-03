package com.ccit.test;

import com.ccit.utils.Email;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class TestEmail {
    @Inject
    private Email email;
    @Test
    public void testSendEmail(){
        String time = DateTime.now().toString("yyyy-MM-dd hh:mm:ss");
        email.sendEmail("tom","192.168.1.1",time);
    }
}
