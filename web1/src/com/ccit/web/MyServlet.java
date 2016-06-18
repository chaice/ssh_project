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
import java.util.UUID;

@WebServlet("/web")
public class MyServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(MyServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID token = UUID.randomUUID();
        HttpSession session = req.getSession();
        session.setAttribute("token",token);
        req.setAttribute("token",token);
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        处理表单重复提交
        ①使用PRG post-redirect-get
        post-302 sendRedirect()-get 200
        ②使用token阻止表单重复提交(利用请求转发
        1.产生token，UUID token = UUID.randomUUID();UUID是java.util包下的类，能产生一个全球唯一的编码
        2.将token存入session&&表单中，session.setAttribute();表单中使用hidden输入框，将token存入
        <input type="hidden" name="token" value="${requestScope.token} /">
        3.在doPost()方法中验证session中的token是否和表单中的相同,如果相同则执行请求转发到成功的页面并删除session中的token
        否则请求转发到失败页面。
         */
        String token = req.getParameter("token");
        HttpSession session = req.getSession();
        String sessionToken = String.valueOf(session.getAttribute("token"));
        if(token != null && token.equals(sessionToken)){
            session.removeAttribute("token");
            String name = req.getParameter("name");
            logger.debug("{}",name);
            req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/views/false.jsp").forward(req,resp);
        }
    }
}
