package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.engine.controller.api.IServicesController;
import com.epam.mysite.engine.controller.impl.ServicesController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PriceServlet", urlPatterns = "/price")
public class PriceServlet extends HttpServlet {
    private final IServicesController servicesController = new ServicesController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "price");
        req.setAttribute("current_lang", System.getProperty("locale"));
        req.setAttribute("services", servicesController.getAllServices());
        RequestDispatcher rd = req.getRequestDispatcher("pages/price.jsp");
        rd.forward(req, resp);
    }
}
