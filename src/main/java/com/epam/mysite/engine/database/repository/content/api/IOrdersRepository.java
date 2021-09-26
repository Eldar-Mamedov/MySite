package com.epam.mysite.engine.database.repository.content.api;

import com.epam.mysite.domain.entity.content.OrdersEntity;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersRepository {
    List<OrdersEntity> findAll() throws SQLException;
    List<OrdersEntity> findAllByClientId(int clientId) throws SQLException;
    List<OrdersEntity> findAllByMasterId(int employeeId) throws SQLException;
    boolean save(OrdersEntity ordersEntity) throws SQLException;
}
