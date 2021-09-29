package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.engine.webservice.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrdersServlet", urlPatterns = "/orders")
public class OrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "orders");
        req.setAttribute("current_lang", System.getProperty("locale"));
        if (Role.valueOf(WebUtils.getRoleCookie(req).getValue()) == Role.Admin) {
            req.setAttribute("style", "admin-order");
        } else {
            req.setAttribute("style", "master-order");
        }
        RequestDispatcher rd = req.getRequestDispatcher("pages/orders.jsp");
        rd.forward(req, resp);
    }
}