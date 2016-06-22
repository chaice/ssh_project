package com.ccit.web;

import com.ccit.utils.TestR;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index_find")
public class TestFind extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=UTF-8");
        String word = new String(req.getParameter("word").getBytes("ISO-8859-1"),"UTF-8");
        TestR testR = new TestR();
        String result = testR.testRSS(word);
        PrintWriter pw = resp.getWriter();
        pw.print(result);
        pw.flush();
        pw.close();
    }
}
