package com.ccit.web;

import com.ccit.dao.FileDAO;
import com.ccit.entity.FileUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/filelist")
public class FileList extends HttpServlet{
    private FileDAO fileDAO = new FileDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FileUp> list = fileDAO.findAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/WEB-INF/views/filelist.jsp").forward(req,resp);
    }
}
