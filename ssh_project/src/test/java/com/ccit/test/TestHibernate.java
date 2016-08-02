package com.ccit.test;

import com.ccit.pojo.Record;
import com.ccit.pojo.User;
import com.ccit.service.RecordService;
import com.ccit.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath:applicationContext.xml")
public class TestHibernate {
    @Inject
    private UserService userService;
    @Inject
    private RecordService recordService;

    Logger logger = LoggerFactory.getLogger(TestHibernate.class);

    @Test
    public void test(){
      List<Record> result = recordService.findByPatientId(7);
        for(Record record :result){
            Assert.assertNotNull(record);
            logger.warn("record:{}"+record);
        }
    }
}
