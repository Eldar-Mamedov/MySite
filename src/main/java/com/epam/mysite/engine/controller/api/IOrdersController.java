package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.entity.content.order.AdminOrderEntity;
import com.epam.mysite.domain.entity.content.order.ClientOrderEntity;
import com.epam.mysite.domain.entity.content.order.MasterOrderEntity;
import com.epam.mysite.domain.enums.OrderStatus;
import com.epam.mysite.domain.webservice.CreateOrder;
import com.epam.mysite.domain.webservice.ProcessOrder;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;

import java.util.List;

public interface IOrdersController {

    ClientOrderEntity getAllClientOrders(User user);

    List<MasterOrderEntity> getAllMasterOrdersInProgressStatus(User user);

    List<AdminOrderEntity> getAllAdminOrders();

    Response createOrder(CreateOrder createOrder, User user);

    Response updateStatusOrder(int orderId, OrderStatus orderStatus);

    Response cancelOrder(String parentOrderId);

    Response processOrder(ProcessOrder processOrder);
}
