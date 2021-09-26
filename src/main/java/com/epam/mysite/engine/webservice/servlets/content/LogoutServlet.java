package com.epam.mysite.engine.webservice.servlets.content;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie roleCookie = new Cookie("Role", "");
        roleCookie.setMaxAge(0);
        Cookie userCookie = new Cookie("User", "");
        userCookie.setMaxAge(0);
        resp.addCookie(roleCookie);
        resp.addCookie(userCookie);
        req.getSession().invalidate();
        resp.sendRedirect(String.format("%s/main", getServletContext().getContextPath()));
    }
}
