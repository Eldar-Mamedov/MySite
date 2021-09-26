package com.epam.mysite.engine.controller.impl;

import com.epam.mysite.domain.entity.UserEntity;
import com.epam.mysite.domain.entity.content.OrdersEntity;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.domain.webservice.content.Orders;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.database.repository.api.IUserRepository;
import com.epam.mysite.engine.database.repository.content.api.IOrdersRepository;
import com.epam.mysite.engine.database.repository.content.impl.OrdersRepository;
import com.epam.mysite.engine.database.repository.impl.UserRepository;
import com.epam.mysite.util.IParser;
import com.google.gson.reflect.TypeToken;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersController implements IOrdersController {
    private final IOrdersRepository ordersRepository = new OrdersRepository();
    private final IUserRepository userRepository = new UserRepository();

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> orders;
        try {
            List<OrdersEntity> ordersEntities = ordersRepository.findAll();
            orders = IParser.fromJson(IParser.toJson(ordersEntities), new TypeToken<List<Orders>>() {
            }.getType());
        } catch (SQLException e) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    @Override
    public List<Orders> getAllClientOrders(User user) {
        List<Orders> orders;
        try {
            UserEntity userEntity = userRepository.findUserByLogin(user.getLogin());
            List<OrdersEntity> ordersEntities = ordersRepository.findAllByClientId(userEntity.getId());
            orders = IParser.fromJson(IParser.toJson(ordersEntities), new TypeToken<List<Orders>>() {
            }.getType());
        } catch (SQLException e) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    @Override
    public List<Orders> getAllMasterOrders(User user) {
        List<Orders> orders;
        try {
            UserEntity userEntity = userRepository.findUserByLogin(user.getLogin());
            List<OrdersEntity> ordersEntities = ordersRepository.findAllByMasterId(userEntity.getId());
            orders = IParser.fromJson(IParser.toJson(ordersEntities), new TypeToken<List<Orders>>() {
            }.getType());
        } catch (SQLException e) {
            orders = new ArrayList<>();
        }
        return orders;
    }

    @Override
    public Response saveOrder(Orders orders) {
        Response response = new Response();
        OrdersEntity ordersEntity = IParser.fromJson(IParser.toJson(orders), OrdersEntity.class);
        try {
            ordersRepository.save(ordersEntity);
        } catch (SQLException e) {
            response.setStatus(500);
        }
        return response;
    }
}
