package com.epam.mysite.engine.webservice.servlets.content;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class HeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentLang = System.getProperty("locale");
        String secondLang = Objects.equals(currentLang, "ru") ? "en" : "ru";
        req.setAttribute("current_lang", currentLang);
        req.setAttribute("second_lang", secondLang);
        req.getParameterMap().forEach((key, value) -> req.setAttribute(key, value[0]));
        RequestDispatcher rd = req.getRequestDispatcher("components/header.jsp");
        rd.include(req, resp);
    }
}
