package com.ccit.servlet;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestCase {
  @Test
    public void test(){
        Logger logger = LoggerFactory.getLogger(TestCase.class);
        logger.debug("{}","tom");
    }

}
