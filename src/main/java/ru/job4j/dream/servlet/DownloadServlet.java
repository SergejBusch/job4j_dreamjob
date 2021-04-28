package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        File downloadFile = null;
        for (File file : new File("/Users/images/").listFiles()) {
            if (file.getName().equals(name) && file.getName().startsWith(id + "-")) {
                downloadFile = file;
                break;
            }
        }
        try (var fis = new FileInputStream(downloadFile)) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
            resp.getOutputStream().write(fis.readAllBytes());
        }
    }
}