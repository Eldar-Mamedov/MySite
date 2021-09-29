package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.enums.OrderStatus;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.controller.impl.OrdersController;
import com.epam.mysite.util.HttpServletRequestHelper;
import com.epam.mysite.util.converter.RequestConverter;
import com.google.gson.reflect.TypeToken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@WebServlet(name = "UpdateServlet", urlPatterns = "/update-order")
public class UpdateOrderServlet extends HttpServlet {

    private final IOrdersController ordersController = new OrdersController();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Integer> body = RequestConverter.convertFromBody(req, new TypeToken<Map<String, Integer>>() {
        }.getType());
        Response response = new Response();
        if (body.containsKey("id")) {
            response = ordersController.updateStatusOrder(body.get("id"), OrderStatus.DONE);
        } else {
            response.setStatus(400);
        }
        HttpServletRequestHelper.sendResponse(resp, response);
    }
}