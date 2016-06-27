package com.ccit.web;

import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/pic.png")
public class TestPatch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfigurableCaptchaService service = new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(0x11522F)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));

        RandomWordFactory randomWordFactory = new RandomWordFactory();
        randomWordFactory.setMinLength(4);
        randomWordFactory.setMaxLength(6);
        randomWordFactory.setCharacters("123456789");
        service.setFontFactory(new FontFactory() {
            @Override
            public Font getFont(int i) {
                return new Font("宋体",Font.ITALIC,25);
            }
        });
        service.setWordFactory(randomWordFactory);

        OutputStream outputStream = resp.getOutputStream();//使验证码在浏览器页面上显示
        String str = EncoderHelper.getChallangeAndWriteImage(service,"png",outputStream);
        System.out.println(str);
        HttpSession session = req.getSession();
        session.setAttribute("str",str);
    }
}
