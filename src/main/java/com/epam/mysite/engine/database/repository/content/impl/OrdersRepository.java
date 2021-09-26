package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.domain.entity.content.OrdersEntity;
import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.OrdersQuery;
import com.epam.mysite.engine.database.repository.content.api.IOrdersRepository;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.mysite.util.converter.EntityConverter.convert;

@Log4j
public class OrdersRepository implements IOrdersRepository {
    private final Connection connection;

    public OrdersRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public List<OrdersEntity> findAll() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrdersEntity> ordersEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(OrdersQuery.GET_ORDERS.getQuery());
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdersEntity ordersEntity = convert(rs, OrdersEntity.class);
                ordersEntities.add(ordersEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return ordersEntities;
    }

    @Override
    public List<OrdersEntity> findAllByClientId(int clientId) throws SQLException {
        return findAllById(OrdersQuery.GET_ORDERS_BY_CLIENT, clientId);
    }

    @Override
    public List<OrdersEntity> findAllByMasterId(int employeeId) throws SQLException {
        return findAllById(OrdersQuery.GET_ORDERS_BY_MASTER, employeeId);
    }

    private List<OrdersEntity> findAllById(OrdersQuery query, int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrdersEntity> ordersEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query.getQuery());
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdersEntity ordersEntity = convert(rs, OrdersEntity.class);
                ordersEntities.add(ordersEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return ordersEntities;
    }

    @Override
    public boolean save(OrdersEntity ordersEntity) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(OrdersQuery.INSERT_ORDER.getQuery());
            //TODO set value for ps
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
        }
        return true;
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.info(e.toString());
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.info(e.toString());
            }
        }
    }
}
