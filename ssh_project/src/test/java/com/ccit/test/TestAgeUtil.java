package com.ccit.test;


import com.ccit.utils.AgeUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestAgeUtil {
    Logger logger = LoggerFactory.getLogger(TestAgeUtil.class);
    @Test
    public void getAge() throws ParseException {
        String idnumber = "411202199106303037";
        String age = AgeUtil.getAge(idnumber);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse("2011-12-13");
        Date date1 = new Date();
        System.out.println(date1);
        System.out.println(date);
        System.out.println(age);

    }
}
