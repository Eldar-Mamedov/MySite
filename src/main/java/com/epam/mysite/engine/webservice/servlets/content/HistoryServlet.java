package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.entity.content.order.ClientOrderEntity;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.content.order.Orders;
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

import static com.epam.mysite.util.HttpServletRequestHelper.sendResponse;
import static com.epam.mysite.util.converter.RequestConverter.convertFromBody;

@WebServlet(name = "HistoryServlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {
    private final IOrdersController ordersController = new OrdersController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "history");
        req.setAttribute("current_lang", System.getProperty("locale"));
        ClientOrderEntity clientOrderEntity = ordersController.getAllClientOrders(WebUtils.getLoggedIndUser(req.getSession(), req.getCookies()));
        req.setAttribute("orderItems", clientOrderEntity.getMainPartEntities());
        RequestDispatcher rd = req.getRequestDispatcher("pages/history.jsp");
        rd.forward(req, resp);
    }
}