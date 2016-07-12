package com.ccit.web;

import com.ccit.dao.FileDAO;
import com.ccit.dbutils.FileUtil;
import com.ccit.dbutils.Strings;
import com.ccit.entity.FileUp;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

@WebServlet("/file")
@MultipartConfig
public class FileServlet extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(FileServlet.class);
    private FileDAO fileDAO = new FileDAO();
    private FileUtil fileUtil = new FileUtil();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/file.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");
        InputStream input = part.getInputStream();
        long size = part.getSize();
        String headValue = part.getHeader("Content-Disposition");
        fileUtil.saveFile(input,headValue);
        resp.sendRedirect("/filelist");
    }
}
