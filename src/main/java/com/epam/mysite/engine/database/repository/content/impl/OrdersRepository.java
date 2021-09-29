package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.domain.entity.content.order.*;
import com.epam.mysite.domain.enums.OrderStatus;
import com.epam.mysite.domain.webservice.CreateOrder;
import com.epam.mysite.domain.webservice.ProcessItem;
import com.epam.mysite.domain.webservice.ProcessOrder;
import com.epam.mysite.domain.webservice.User;
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
import java.util.UUID;

import static com.epam.mysite.util.converter.EntityConverter.convert;

@Log4j
public class OrdersRepository implements IOrdersRepository {
    private final Connection connection;

    public OrdersRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(CreateOrder createOrder, User user) throws SQLException {
        PreparedStatement ps = null;
        String parentOrderId = UUID.randomUUID().toString();
        try {
            connection.setAutoCommit(false);
            connection.commit();
            ps = connection.prepareStatement(OrdersQuery.INSERT_CLIENT_ORDER.getQuery());
            for (String service : createOrder.getServices()) {
                ps.setTimestamp(1, createOrder.getDateTime());
                ps.setString(2, user.getLogin());
                ps.setString(3, service);
                ps.setString(4, service);
                ps.setString(5, parentOrderId);
                ps.setString(6, OrderStatus.IN_PROCESS.name());
                ps.addBatch();
            }
            int[] orders = ps.executeBatch();
            for (int i : orders) {
                if (i != 1) {
                    return false;
                }
            }
        } catch (Exception e) {
            log.info(e.toString());
            connection.rollback();
            throw e;
        } finally {
            closePreparedStatement(ps);
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public List<ClientOrderMainPartEntity> findClientMainOrderParts(User user) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClientOrderMainPartEntity> clientOrderMainPartEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(OrdersQuery.GET_MAIN_PART_ORDER_FOR_CLIENT.getQuery());
            ps.setString(1, System.getProperty("locale"));
            ps.setString(2, user.getLogin());
            rs = ps.executeQuery();
            while (rs.next()) {
                ClientOrderMainPartEntity clientOrderMainPartEntity = convert(rs, ClientOrderMainPartEntity.class);
                clientOrderMainPartEntities.add(clientOrderMainPartEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return clientOrderMainPartEntities;
    }

    @Override
    public List<OrderDetailsEntity> findOrderDetails(User user, String orderParentId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(OrdersQuery.GET_ORDER_DETAILS_FOR_CLIENT.getQuery());
            ps.setString(1, System.getProperty("locale"));
            ps.setString(2, System.getProperty("locale"));
            ps.setString(3, user.getLogin());
            ps.setString(4, orderParentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailsEntity orderDetailsEntity = convert(rs, OrderDetailsEntity.class);
                orderDetailsEntities.add(orderDetailsEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return orderDetailsEntities;
    }

    @Override
    public List<MasterOrderEntity> findMasterOrders(User user, OrderStatus status) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MasterOrderEntity> masterOrderEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(OrdersQuery.GET_ORDER_FOR_MASTER.getQuery());
            ps.setString(1, System.getProperty("locale"));
            ps.setString(2, user.getLogin());
            ps.setString(3, status.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                MasterOrderEntity masterOrderEntity = convert(rs, MasterOrderEntity.class);
                masterOrderEntities.add(masterOrderEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return masterOrderEntities;
    }

    @Override
    public boolean updateOrderStatus(int orderId, OrderStatus orderStatus) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(OrdersQuery.UPDATE_ORDER_STATUS.getQuery());
            ps.setString(1, orderStatus.toString());
            ps.setInt(2, orderId);
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

    @Override
    public List<AdminOrderEntity> findAdminOrders() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdminOrderEntity> adminOrderEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(OrdersQuery.GET_ORDERS_FOR_ADMIN_ORDER_BY_STATUS.getQuery());
            rs = ps.executeQuery();
            while (rs.next()) {
                AdminOrderEntity adminOrderEntity = convert(rs, AdminOrderEntity.class);
                adminOrderEntities.add(adminOrderEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return adminOrderEntities;
    }

    @Override
    public List<AdminOrderItemEntity> findAdminOrderItemsByParentOrderId(String parentOrderId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<AdminOrderItemEntity> itemEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(OrdersQuery.GET_ORDER_ITEMS_BY_PARENT_ORDER_ID.getQuery());
            ps.setString(1, System.getProperty("locale"));
            ps.setString(2, parentOrderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                AdminOrderItemEntity itemEntity = convert(rs, AdminOrderItemEntity.class);
                itemEntities.add(itemEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return itemEntities;
    }

    @Override
    public boolean updateOrderStatusByParentOrderId(String parentOrderId, OrderStatus orderStatus) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(OrdersQuery.UPDATE_ORDER_STATUS_BY_PARENT_ORDER_ID.getQuery());
            ps.setString(1, orderStatus.toString());
            ps.setString(2, parentOrderId);
            if (ps.executeUpdate() < 1) {
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

    @Override
    public boolean updateOrderStatusEmployeeDateService(ProcessOrder processOrder, OrderStatus orderStatus) throws SQLException {
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            connection.commit();
            ps = connection.prepareStatement(OrdersQuery.UPDATE_ORDER_STATUS_DATE_SERVICE_EMPLOYEE_ID_BY_ORDER_ID.getQuery());
            for (ProcessItem processItem : processOrder.getItems()) {
                ps.setString(1, orderStatus.toString());
                ps.setInt(2, processItem.getEmployeeId());
                ps.setTimestamp(3, processOrder.getDateTime());
                ps.setInt(4, processItem.getOrderId());
                ps.addBatch();
            }
            int[] orders = ps.executeBatch();
            for (int i : orders) {
                if (i != 1) {
                    return false;
                }
            }
        } catch (Exception e) {
            log.info(e.toString());
            connection.rollback();
            throw e;
        } finally {
            closePreparedStatement(ps);
            connection.setAutoCommit(true);
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
