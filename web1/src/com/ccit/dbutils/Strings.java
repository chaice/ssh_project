package com.ccit.dbutils;


public class Strings {
    public static String getFileName(String str){
        String filename = str.substring(str.indexOf("filename=\""));
        filename = filename.substring(filename.indexOf("\"")+1,filename.length()-1);
        return filename;
    }
}
