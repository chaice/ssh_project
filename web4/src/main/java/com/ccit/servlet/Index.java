package com.ccit.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/index")
public class Index extends HttpServlet{
    Logger logger = LoggerFactory.getLogger(Index.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = new String(req.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
        PrintWriter pw = resp.getWriter();
        logger.debug("{}",name);
        logger.debug(req.getParameter("address"));
        if("tom".equals(name)){
            pw.write("no");
        }else {
            pw.write("yes");
        }
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        logger.debug("{}",name);
        logger.debug("{}",address);
        PrintWriter pw = resp.getWriter();
        if("tom".equals(name)){
            pw.write("no");
        }else {
            pw.write("yes");
        }
        pw.flush();
        pw.close();
    }
}
