package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("name");
        String id = req.getParameter("id");

        if (Optional.ofNullable(fileName).isPresent()) {
            removeSinglePhoto(fileName);
        } else if (Optional.ofNullable(id).isPresent()) {
            removeAllCandidatePhotos(id);
        }

        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    private void removeSinglePhoto(String fileName) {
        try {
            Files.delete(
                    Paths.get("/Users/images/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeAllCandidatePhotos(String id) {
        for (File file : new File("/Users/images/").listFiles()) {
            if (file.getName().startsWith(id + "-")) {
                try {
                    Files.delete(
                            Paths.get(file.getPath()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
