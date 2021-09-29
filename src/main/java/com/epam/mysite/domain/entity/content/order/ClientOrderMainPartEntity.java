package com.epam.mysite.domain.entity.content.order;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientOrderMainPartEntity {
    private int id;
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("date_order")
    private String dateOrder;
    @SerializedName("date_service")
    private String dateService;
    private int total;
    private String status;
    @SerializedName("parent_order_id")
    private String parentOrderId;
    private final Map<String, List<OrderDetailsEntity>> detailsEntityMap;

    public ClientOrderMainPartEntity() {
        detailsEntityMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDateService() {
        return dateService;
    }

    public void setDateService(String dateService) {
        this.dateService = dateService;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }

    public void putOrderDetail(String categoryName, OrderDetailsEntity orderDetailsEntity) {
        if (!detailsEntityMap.containsKey(categoryName)) {
            detailsEntityMap.put(categoryName, new ArrayList<>());
        }
        detailsEntityMap.get(categoryName).add(orderDetailsEntity);
    }

    public Map<String, List<OrderDetailsEntity>> getDetailsEntityMap() {
        return detailsEntityMap;
    }
}
