package com.ccit.utils;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class AgeUtil {
    public static String getAge(String idnumber){
        if(StringUtils.isNotEmpty(idnumber)){
            String ageNumber = idnumber.substring(6,14);
            int year = Integer.parseInt(ageNumber.substring(0,4));
            int month = Integer.parseInt(ageNumber.substring(4,6));
            int day = Integer.parseInt(ageNumber.substring(6,8));
            String now = DateTime.now().toString("yyyy-MM-dd");
            String[] timeParam = now.split("-");
            int age = Integer.parseInt(timeParam[0])-year;
            if(month == Integer.parseInt(timeParam[1])){
                if(day < Integer.parseInt(timeParam[2])){
                    age ++ ;
                }
            }else if(Integer.parseInt(timeParam[1]) > month){
                age ++;
            }
            return String.valueOf(age);
        }
        return "";
    }
}
