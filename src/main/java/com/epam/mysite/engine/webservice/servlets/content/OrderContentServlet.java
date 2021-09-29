package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.entity.content.order.AdminOrderEntity;
import com.epam.mysite.domain.entity.content.order.MasterOrderEntity;
import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.controller.impl.OrdersController;
import com.epam.mysite.engine.webservice.util.WebUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderContentServlet", urlPatterns = "/order-content")
public class OrderContentServlet extends HttpServlet {
    private final IOrdersController ordersController = new OrdersController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie roleCookie = WebUtils.getRoleCookie(req);
        RequestDispatcher rd;
        if (roleCookie.getValue().equals(Role.Admin.name())) {
            rd = req.getRequestDispatcher("components/admin-orders.jsp");
            List<AdminOrderEntity> adminOrderEntities = ordersController.getAllAdminOrders();
            req.setAttribute("orderItems", adminOrderEntities);
        } else {
            rd = req.getRequestDispatcher("components/master-orders.jsp");
            List<MasterOrderEntity> masterOrderEntities = ordersController.getAllMasterOrdersInProgressStatus(WebUtils.getLoggedIndUser(req.getSession(), req.getCookies()));
            req.setAttribute("orderItems", masterOrderEntities);
        }
        rd.include(req, resp);
    }
}