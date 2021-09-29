package com.epam.mysite.engine.controller.impl;

import com.epam.mysite.domain.entity.content.order.*;
import com.epam.mysite.domain.enums.OrderStatus;
import com.epam.mysite.domain.webservice.CreateOrder;
import com.epam.mysite.domain.webservice.ProcessOrder;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.controller.api.IOrdersController;
import com.epam.mysite.engine.database.repository.api.IEmployeeRepository;
import com.epam.mysite.engine.database.repository.content.api.IOrdersRepository;
import com.epam.mysite.engine.database.repository.content.impl.OrdersRepository;
import com.epam.mysite.engine.database.repository.impl.EmployeeRepository;
import com.mysql.cj.util.StringUtils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import static com.epam.mysite.util.IParser.toJson;

public class OrdersController implements IOrdersController {
    private final IOrdersRepository ordersRepository = new OrdersRepository();
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public ClientOrderEntity getAllClientOrders(User user) {
        ClientOrderEntity clientOrderEntity;
        try {
            clientOrderEntity = new ClientOrderEntity();
            List<ClientOrderMainPartEntity> clientOrderMainPartEntities = ordersRepository.findClientMainOrderParts(user);
            for (ClientOrderMainPartEntity clientOrderMainPartEntity : clientOrderMainPartEntities) {
                clientOrderMainPartEntity.setStatus(OrderStatus.valueOf(clientOrderMainPartEntity.getStatus()).getStatus());
                if (StringUtils.isNullOrEmpty(clientOrderMainPartEntity.getDateService())) {
                    clientOrderMainPartEntity.setDateService(ResourceBundle.getBundle("history", new Locale(System.getProperty("locale"))).getString("date_service_empty"));
                }
                List<OrderDetailsEntity> orderDetailsEntities = ordersRepository.findOrderDetails(user, clientOrderMainPartEntity.getParentOrderId());
                for (OrderDetailsEntity orderDetailsEntity : orderDetailsEntities) {
                    if (StringUtils.isNullOrEmpty(orderDetailsEntity.getFullName())) {
                        orderDetailsEntity.setFullName(ResourceBundle.getBundle("history", new Locale(System.getProperty("locale"))).getString("emp_name"));
                    }
                    clientOrderMainPartEntity.putOrderDetail(orderDetailsEntity.getCategoryName(), orderDetailsEntity);
                }
            }
            clientOrderEntity.setMainPartEntities(clientOrderMainPartEntities);
        } catch (SQLException e) {
            clientOrderEntity = new ClientOrderEntity();
        }
        return clientOrderEntity;
    }

    @Override
    public List<MasterOrderEntity> getAllMasterOrdersInProgressStatus(User user) {
        List<MasterOrderEntity> masterOrderEntities;
        try {
            masterOrderEntities = ordersRepository.findMasterOrders(user, OrderStatus.IN_PROGRESS);
            masterOrderEntities.forEach(entity -> {
                entity.setStatus(OrderStatus.valueOf(entity.getStatus()).getStatus());
            });
        } catch (SQLException e) {
            masterOrderEntities = new ArrayList<>();
        }
        return masterOrderEntities;
    }

    @Override
    public Response createOrder(CreateOrder createOrder, User user) {
        Response response = new Response();
        if (isValidCreateOrder(createOrder, response)) {
            try {
                boolean result = ordersRepository.save(createOrder, user);
                if (result) {
                    response.setStatus(200);
                    response.setBody(ResourceBundle.getBundle("orders", new Locale(System.getProperty("locale"))).getString("success_message"));
                }
            } catch (SQLException e) {
                response.setStatus(500);
                response.setBody(e.getMessage());
            }
        } else {
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public Response updateStatusOrder(int orderId, OrderStatus orderStatus) {
        Response response = new Response();
        try {
            boolean result = ordersRepository.updateOrderStatus(orderId, orderStatus);
            if (result) {
                response.setStatus(200);
            }
        } catch (SQLException e) {
            response.setStatus(500);
            response.setBody(e.getMessage());
        }
        return response;
    }

    @Override
    public List<AdminOrderEntity> getAllAdminOrders() {
        List<AdminOrderEntity> adminOrderEntities;
        try {
            adminOrderEntities = ordersRepository.findAdminOrders();
            adminOrderEntities.forEach(entity -> {
                entity.setStatus(OrderStatus.valueOf(entity.getStatus()).getStatus());
                List<AdminOrderItemEntity> adminOrderItemEntities;
                try {
                    adminOrderItemEntities = ordersRepository.findAdminOrderItemsByParentOrderId(entity.getParentOrderId());
                    for (AdminOrderItemEntity adminOrderItemEntity : adminOrderItemEntities) {

                        adminOrderItemEntity.setEmployeeEntities(employeeRepository.findAllEmployeesByServiceItemId(adminOrderItemEntity.getServiceId()));
                    }
                } catch (SQLException e) {
                    adminOrderItemEntities = new ArrayList<>();
                }
                entity.setOrderItemEntities(adminOrderItemEntities);
            });
        } catch (SQLException e) {
            adminOrderEntities = new ArrayList<>();
        }
        return adminOrderEntities;
    }

    @Override
    public Response cancelOrder(String parentOrderId) {
        Response response = new Response();
        if (!StringUtils.isNullOrEmpty(parentOrderId)) {
            try {
                boolean result = ordersRepository.updateOrderStatusByParentOrderId(parentOrderId, OrderStatus.CANCELLED);
                if (result) {
                    response.setStatus(200);
                }
            } catch (SQLException e) {
                response.setStatus(500);
                response.setBody(e.getMessage());
            }
        } else {
            response.setStatus(400);
            response.setBody(ResourceBundle.getBundle("admin-order", new Locale(System.getProperty("locale"))).getString("cancel_error"));
        }
        return response;
    }

    @Override
    public Response processOrder(ProcessOrder processOrder) {
        Response response = new Response();
        if (isValidProcessOrder(processOrder, response)) {
            try {
                boolean result = ordersRepository.updateOrderStatusEmployeeDateService(processOrder, OrderStatus.IN_PROGRESS);
                if (result) {
                    response.setStatus(200);
                }
            } catch (SQLException e) {
                response.setStatus(500);
                response.setBody(e.getMessage());
            }
        } else {
            response.setStatus(400);
        }
        return response;
    }

    private boolean isValidCreateOrder(CreateOrder createOrder, Response response) {
        ResourceBundle ordersBundle = ResourceBundle.getBundle("orders", new Locale(System.getProperty("locale")));
        Map<String, List<String>> errors = new HashMap<>();
        boolean result = true;
        if (createOrder.getServices().isEmpty()) {
            result = false;
            putValue(errors, "services", ordersBundle.getString("services_error"));
        }
        if (createOrder.getDateTime().before(new Timestamp(System.currentTimeMillis()))) {
            result = false;
            putValue(errors, "dateTime", ordersBundle.getString("date_error"));
        }
        if (createOrder.getDateTime().toString().isEmpty()) {
            result = false;
            putValue(errors, "dateTime", ordersBundle.getString("date_empty_error"));
        }
        if (!result) {
            response.setBody(toJson(errors));
        }
        return result;
    }

    private boolean isValidProcessOrder(ProcessOrder processOrder, Response response) {
        ResourceBundle adminOrders = ResourceBundle.getBundle("admin-order", new Locale(System.getProperty("locale")));
        Map<String, List<String>> errors = new HashMap<>();
        boolean result = true;
        if (processOrder.getItems().isEmpty()) {
            result = false;
            putValue(errors, "items", adminOrders.getString("items_error"));
        }
        if (processOrder.getDateTime().before(new Timestamp(System.currentTimeMillis()))) {
            result = false;
            putValue(errors, "date", adminOrders.getString("date_error"));
        }
        if (!result) {
            response.setBody(toJson(errors));
        }
        return result;
    }

    private void putValue(Map<String, List<String>> map, String key, String value) {
        List<String> values;
        if (map.containsKey(key)) {
            values = map.get(key);
        } else {
            values = new ArrayList<>();
        }
        values.add(value);
        map.put(key, values);
    }
}
