package com.ccit;

import com.ccit.pojo.SalesLog;
import com.ccit.service.SalesLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class test {
    Logger logger = LoggerFactory.getLogger(test.class);
    @Inject
   private SalesLogService salesLogService;
    @Test
    public void testFind(){
        String context = "2016-07-16 14:58:19.0 cc创建了:瞌睡";
        SalesLog salesLog = salesLogService.findByContext(context);
        logger.debug("log:{}",salesLog);
    }
}
