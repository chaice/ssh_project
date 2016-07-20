package com.ccit;

import com.ccit.service.ChartService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class test {
    Logger logger = LoggerFactory.getLogger(test.class);
    @Inject
    private ChartService chartService;
    @Test
    public void testFind(){
        List<Map<String,Object>> list = chartService.gerProgressCount();
        Map<String,Object> map1 = Maps.newHashMap();
        Map<String,Object> map2 = Maps.newHashMap();
        for(Map<String,Object> map:list){
            map1.put("name",map.get("type"));
            map2.put("value",map.get("total"));
        }
        logger.debug("{}",list);
        logger.debug("{}",map2);

    }
}
