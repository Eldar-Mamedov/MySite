package com.epam.mysite.domain.entity.content;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class ClientOrderMainPartEntity {
    private int id;
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("date_order")
    private Timestamp dateOrder;
    @SerializedName("date_service")
    private Timestamp dateService;
    private int total;
    private String status;
    @SerializedName("parent_order_id")
    private String parentOrderId;

    public ClientOrderMainPartEntity() {
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

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Timestamp getDateService() {
        return dateService;
    }

    public void setDateService(Timestamp dateService) {
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
}
