package com.ccit.test;


import org.junit.Test;

import java.util.UUID;

public class TsetSubString {
    @Test
    public void subString(){
        String headvalue = "form-data; name=\"file\"; filename=\"1166283114bc815e34o.jpg\"";
        String filename = headvalue.substring(headvalue.indexOf("filename=\""));
        filename = filename.substring(filename.indexOf("\"")+1,filename.length()-1);
        String extra = filename.substring(filename.indexOf("."));
        String uuid = UUID.randomUUID().toString();
        filename = uuid + extra;
        System.out.println(filename);

    }
}
