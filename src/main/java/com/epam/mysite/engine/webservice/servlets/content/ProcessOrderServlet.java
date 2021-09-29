package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.webservice.ProcessOrder;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.controller.impl.OrdersController;
import com.epam.mysite.util.HttpServletRequestHelper;
import com.epam.mysite.util.converter.RequestConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProcessOrderServlet", urlPatterns = "/process-order")
public class ProcessOrderServlet extends HttpServlet {
    private IOrdersController ordersController = new OrdersController();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProcessOrder processOrder = RequestConverter.convertFromBody(req, ProcessOrder.class);
        Response response = ordersController.processOrder(processOrder);
        if (response.getStatus() == 200) {
            response.setRedirect(String.format("%s/orders", getServletContext().getContextPath()));
        }
        HttpServletRequestHelper.sendResponse(resp, response);
    }
}