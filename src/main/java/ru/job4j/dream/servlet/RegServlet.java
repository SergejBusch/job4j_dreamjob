package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var store = PsqlStore.instOf();
        User user = null;
        if (store.findUserByEmail(req.getParameter("email")) == null) {
            user = new User();
            user.setName(req.getParameter("name"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));
            store.save(user);
        }
        if (user != null) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        } else {
            req.setAttribute("error", "User with this email is already registered");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
