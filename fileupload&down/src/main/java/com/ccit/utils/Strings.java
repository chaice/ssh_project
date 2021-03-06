package com.ccit.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class Strings {
    public static String getUTF8(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return str;
        }
        return "";
    }
}
