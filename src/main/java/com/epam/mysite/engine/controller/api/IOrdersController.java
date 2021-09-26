package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.domain.webservice.content.Orders;

import java.util.List;

public interface IOrdersController {
    List<Orders> getAllOrders();
    List<Orders> getAllClientOrders(User user);
    List<Orders> getAllMasterOrders(User user);
    Response saveOrder(Orders orders);
}
