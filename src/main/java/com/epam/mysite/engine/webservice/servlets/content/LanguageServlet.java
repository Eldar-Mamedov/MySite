package com.epam.mysite.engine.webservice.servlets.content;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentLang = System.getProperty("locale");
        String currentPage = req.getParameter("page");
        System.setProperty("locale", Objects.equals(currentLang, "ru") ? "en" : "ru");
        resp.sendRedirect(String.format("/beauty-saloon/%s", currentPage));
    }
}
