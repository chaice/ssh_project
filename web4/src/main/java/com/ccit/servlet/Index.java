package com.ccit.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by cc on 2016/6/20.
 */
@WebServlet("/index")
public class Index extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
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
