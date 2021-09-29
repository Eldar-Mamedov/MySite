package com.epam.mysite.engine.database.repository.content.api;

import com.epam.mysite.domain.entity.content.order.*;
import com.epam.mysite.domain.enums.OrderStatus;
import com.epam.mysite.domain.webservice.CreateOrder;
import com.epam.mysite.domain.webservice.ProcessOrder;
import com.epam.mysite.domain.webservice.User;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersRepository {

    boolean save(CreateOrder createOrder, User user) throws SQLException;

    List<ClientOrderMainPartEntity> findClientMainOrderParts(User user) throws SQLException;

    List<OrderDetailsEntity> findOrderDetails(User user, String orderParentId) throws SQLException;

    List<MasterOrderEntity> findMasterOrders(User user, OrderStatus status) throws SQLException;

    boolean updateOrderStatus(int orderId, OrderStatus orderStatus) throws SQLException;

    List<AdminOrderEntity> findAdminOrders() throws SQLException;

    List<AdminOrderItemEntity> findAdminOrderItemsByParentOrderId(String parentOrderId) throws SQLException;

    boolean updateOrderStatusByParentOrderId(String parentOrderId, OrderStatus orderStatus) throws SQLException;

    boolean updateOrderStatusEmployeeDateService(ProcessOrder processOrder, OrderStatus orderStatus) throws SQLException;
}
