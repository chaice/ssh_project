package com.ccit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Login.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();
        String str = (String) session.getAttribute("str");
        String txt = req.getParameter("txt");
        logger.debug("session{}:{}",str,txt);
        if(txt != null && txt.equals(str)){
            req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/views/false.jsp").forward(req,resp);
        }
    }
}
