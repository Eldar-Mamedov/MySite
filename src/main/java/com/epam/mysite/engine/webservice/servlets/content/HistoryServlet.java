package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.content.Orders;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.controller.impl.OrdersController;
import com.epam.mysite.engine.webservice.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.mysite.util.HttpServletRequestHelper.sendResponse;
import static com.epam.mysite.util.converter.RequestConverter.convertFromBody;

@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {
    private final IOrdersController ordersController = new OrdersController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "history");
        req.setAttribute("current_lang", System.getProperty("locale"));
        List<Orders> orders = ordersController.getAllClientOrders(WebUtils.getLoggedIndUser(req.getSession(), req.getCookies()));
        req.setAttribute("orderItems", orders);
        RequestDispatcher rd = req.getRequestDispatcher("pages/history.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Orders orders = convertFromBody(req, Orders.class);
        Response response = ordersController.saveOrder(orders);
        if (response.getStatus() == 200) {
            response.setRedirect(String.format("%s/history", getServletContext().getContextPath()));
        }
        sendResponse(resp, response);
    }
}