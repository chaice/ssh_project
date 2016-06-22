package com.ccit.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ajax.xml")
public class Index extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Index.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/xml;charset=UTF-8");
        logger.debug("{}","tom");
        PrintWriter pw = resp.getWriter();
        pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        pw.print("<users>");
        pw.print("<user><id>1</id><name>tom</name><address>中国</address></user>");
        pw.print("<user><id>2</id><name>jim</name><address>usa</address></user>");
        pw.print("<user><id>3</id><name>lisa</name><address>uk</address></user>");
        pw.print("<user><id>4</id><name>alis</name><address>japan</address></user>");

        pw.print("</users>");
        pw.flush();
        pw.close();
    }
}
