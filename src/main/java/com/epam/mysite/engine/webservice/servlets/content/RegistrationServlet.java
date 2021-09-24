package com.epam.mysite.engine.webservice.servlets.content;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet",urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "registration");
        req.setAttribute("current_lang", System.getProperty("locale"));
        RequestDispatcher rd = req.getRequestDispatcher("pages/registration.jsp");
        rd.forward(req, resp);
    }
}
