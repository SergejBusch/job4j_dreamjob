package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var images = new ArrayList<String>();
        for (var name : new File("/Users/images/").listFiles()) {
            images.add(name.getName());
        }
        req.setAttribute("images", images);
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (Boolean.parseBoolean(req.getParameter("delete"))) {

            delete(req);
            resp.sendRedirect("delete?id=" + req.getParameter("id") + "&delete=true");
        } else {

            req.setCharacterEncoding("UTF-8");
            save(req);
            resp.sendRedirect(req.getContextPath() + "/candidates.do");
        }

    }

    private void save(HttpServletRequest req) {
        PsqlStore.instOf().save(
                new Candidate(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        Integer.parseInt(req.getParameter("city_id"))
                )
        );
    }

    private void delete(HttpServletRequest req) {
        PsqlStore.instOf().removeCandidate(Integer.parseInt(req.getParameter("id")));
    }
}
