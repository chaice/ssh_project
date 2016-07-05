package com.ccit.utils;


import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Message {
    public void sendMessage() throws IOException {
        String str = Message.getCheck();
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=gbk");
        NameValuePair[] data = {new NameValuePair("Uid","cc911215"),
                new NameValuePair("Key","4b18cb44f89ad4e99b9f"),
                new NameValuePair("smsMob","18300608644"),
                new NameValuePair("smsText","验证码为："+str)
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getRequestHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println("---" + h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes(
                "gbk"));
        System.out.println(result);

    }
    private static String getCheck() {
        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(0x11522F)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        RandomWordFactory factory = new RandomWordFactory();
        factory.setMaxLength(5);
        factory.setMinLength(5);
        factory.setCharacters("123456789");

        service.setWordFactory(factory);
        OutputStream outputStream = null;
        String str = null;
        try {
            outputStream = new FileOutputStream("d:/check");
            str = EncoderHelper.getChallangeAndWriteImage(service,"text",outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(str);
        return str;
    }
}
