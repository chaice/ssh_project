package com.ccit.test;

import org.junit.Test;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class TestPatch {
    @Test
    public void test() throws IOException {
        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(0x11522F)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        RandomWordFactory factory = new RandomWordFactory();
        factory.setMaxLength(5);
        factory.setMinLength(5);
        factory.setCharacters("123456789");

        service.setWordFactory(factory);
        OutputStream outputStream = new FileOutputStream("d:/check");
        String str = EncoderHelper.getChallangeAndWriteImage(service,"text",outputStream);
        System.out.println(str);

    }
}
