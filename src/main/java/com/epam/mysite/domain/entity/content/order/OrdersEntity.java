package com.epam.mysite.domain.entity.content;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class OrdersEntity {
    private int id;
    @SerializedName("date_order")
    private Timestamp dateOrder;
    @SerializedName("clientId")
    private int clientId;
    @SerializedName("employee_id")
    private int employeeId;
    @SerializedName("is_payed")
    private boolean isPayed;
    @SerializedName("date_service")
    private Timestamp dateService;
    @SerializedName("parent_order_id")
    private String parentOrderId;

    public OrdersEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public Timestamp getDateService() {
        return dateService;
    }

    public void setDateService(Timestamp dateService) {
        this.dateService = dateService;
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }
}
