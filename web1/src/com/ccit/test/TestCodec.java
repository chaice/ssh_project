package com.ccit.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
public class TestCodec {
    @Test
    public void testCodec(){
        String pwd = "12123";
        String salt = "&&&#$#!(*@#{}!{}#";
        String password = DigestUtils.md5Hex(pwd+salt);//给密码加盐，登陆验证时也加
        System.out.println("password:"+password);

        String pass = DigestUtils.sha1Hex(pwd+salt);
        System.out.println("pass:"+pass);
    }

    @Test
    public void testFile() throws IOException {
        //使用DigestUtils.md5(InputStream data);根据方法返回的MD5值判断两个文件是否相同
        FileInputStream inputStream = new FileInputStream("d:/my2.log");
        String md5 = DigestUtils.md5Hex(inputStream);
        System.out.println(md5);
    }
}
