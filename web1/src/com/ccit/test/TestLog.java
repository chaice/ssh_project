package com.ccit.test;


import org.junit.Test;
import org.patchca.color.RandomColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestLog {
    @Test
    public void test(){
        Logger logger = LoggerFactory.getLogger(TestLog.class);
        String name = "小明";
        String book = "xx";
        logger.debug("{}借阅了{}",name,book);
    }
    @Test
    public void testPatchca() throws IOException {
//        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
//        service.setColorFactory(new SingleColorFactory(new Color(0xD6863F)));
//        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));
//        RandomWordFactory randomWordFactory = new RandomWordFactory();
//        randomWordFactory.setMaxLength(5);
//        randomWordFactory.setMinLength(4);
//        randomWordFactory.setCharacters("afkasfk");
//        FileOutputStream outputStream = new FileOutputStream("d:/gif.gif");
//        EncoderHelper.getChallangeAndWriteImage(service,"gif",outputStream);
//        outputStream.flush();
//        outputStream.close()






        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(0x61B6C0)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));
        RandomWordFactory randomWordFactory = new RandomWordFactory();
        randomWordFactory.setMinLength(5);//验证码的最小长度
        randomWordFactory.setMaxLength(6);//验证码的最大长度
        randomWordFactory.setCharacters("rtssx刷必须邪恶啊");//设置验证码的类型
        service.setFontFactory(new FontFactory() {//设置中文字体
            @Override
            public Font getFont(int i) {
                return new Font("宋体",Font.BOLD,15);
            }
        });
        service.setWordFactory(randomWordFactory);

        FileOutputStream outputStream = new FileOutputStream("d:test.png");
        EncoderHelper.getChallangeAndWriteImage(service,"png",outputStream);

        outputStream.flush();
        outputStream.close();




    }

}
