package com.epam.mysite.domain.entity.content.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminOrderEntity {
    private int id;
    @SerializedName("client_name")
    private String clientName;
    private String phone;
    @SerializedName("total")
    private int totalPrice;
    @SerializedName("date_order")
    private String dateOrder;
    private String status;
    @SerializedName("parent_order_id")
    private String parentOrderId;
    private List<AdminOrderItemEntity> orderItemEntities;

    public AdminOrderEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
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

    public List<AdminOrderItemEntity> getOrderItemEntities() {
        return orderItemEntities;
    }

    public void setOrderItemEntities(List<AdminOrderItemEntity> orderItemEntities) {
        this.orderItemEntities = orderItemEntities;
    }
}
