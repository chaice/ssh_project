package com.ccit.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class Strings {
    public static String toUTF8(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                return new String(str.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("字符编码异常");
            }
        }
        return "";
    }
}
