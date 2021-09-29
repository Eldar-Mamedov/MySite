package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.CreateOrder;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.controller.impl.OrdersController;
import com.epam.mysite.engine.webservice.util.WebUtils;
import com.epam.mysite.util.HttpServletRequestHelper;
import com.epam.mysite.util.converter.RequestConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.epam.mysite.engine.webservice.util.WebUtils.getLoggedIndUser;

@WebServlet(name = "CreateOrderServlet", urlPatterns = "/create-order")
public class CreateOrderServlet extends HttpServlet {
    private final IOrdersController ordersController = new OrdersController();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie role = WebUtils.getRoleCookie(req);
        Response response = new Response();
        switch (Role.valueOf(role.getValue())) {
            case Client: {
                CreateOrder createOrder = RequestConverter.convertFromBody(req, CreateOrder.class);
                response = ordersController.createOrder(createOrder, getLoggedIndUser(req.getSession(), req.getCookies()));
                if (response.getStatus() == 200) {
                    response.setRedirect(String.format("%s/history", getServletContext().getContextPath()));
                }
                break;
            }
            case Master: {
            }
            case Admin: {
                response.setBody(ResourceBundle.getBundle("orders", new Locale(System.getProperty("locale"))).getString("employee_message"));
                response.setStatus(400);
                break;
            }
            default: {
                response.setBody(ResourceBundle.getBundle("orders", new Locale(System.getProperty("locale"))).getString("auth_message"));
                response.setStatus(400);
                response.setRedirect(String.format("%s/login", getServletContext().getContextPath()));
                break;
            }
        }
        HttpServletRequestHelper.sendResponse(resp, response);
    }
}