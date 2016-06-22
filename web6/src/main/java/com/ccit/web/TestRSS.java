package com.ccit.web;


import com.ccit.utils.TestR;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class TestRSS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/xml;charset=UTF-8");
        TestR test = new TestR();
        String url = "http://blog.sina.com.cn/rss/1417190052.xml";
        String result = test.testRSS(url);
        PrintWriter pw = resp.getWriter();
        pw.print(result);
        pw.flush();
        pw.close();
    }
}
