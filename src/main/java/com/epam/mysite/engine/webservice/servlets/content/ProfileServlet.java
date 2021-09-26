package com.epam.mysite.engine.webservice.servlets.content;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.mysite.engine.webservice.util.WebUtils.getLoggedIndUser;

@WebServlet(name = "ProfileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "profile");
        req.setAttribute("current_lang", System.getProperty("locale"));
        req.setAttribute("user", getLoggedIndUser(req.getSession(), req.getCookies()));
        RequestDispatcher rd = req.getRequestDispatcher("pages/profile.jsp");
        rd.forward(req, resp);
    }
}
