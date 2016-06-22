package com.ccit.web;
import com.ccit.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class TestClient extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(TestClient.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/xml;charset=UTF-8");
//        Result res = new Result();
//        String result = res.getResult("http://blog.sina.com.cn/rss/1220218113.xml");
//        PrintWriter pw = resp.getWriter();
//        pw.print(result);
//
//        pw.flush();
//        pw.close();
        resp.setContentType("text/xml;charset=UTF-8");
        String word = new String(req.getParameter("p").getBytes("ISO-8859-1"),"UTF-8");
        logger.info(word);
        Result res = new Result();
        String result = res.getResult("http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype=xml&version=1.1&q="+word);
        System.out.println(result);
        PrintWriter pw = resp.getWriter();
        pw.print(result);

        pw.flush();
        pw.close();
    }
}
