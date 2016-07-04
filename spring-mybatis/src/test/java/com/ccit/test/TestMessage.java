package com.ccit.test;

import com.ccit.utils.Message;
import org.junit.Test;

import java.io.IOException;


public class TestMessage {
    @Test
    public void testSendMessage() throws IOException {
        Message message = new Message();
        message.sendMessage();
    }
}
