package com.ccit.test;


import com.ccit.utils.AgeUtil;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestAgeUtil {
    Logger logger = LoggerFactory.getLogger(TestAgeUtil.class);
    @Test
    public void getAge(){
        String idnumber = "411202199106303037";
        String age = AgeUtil.getAge(idnumber);
        System.out.println(age);

    }
}
