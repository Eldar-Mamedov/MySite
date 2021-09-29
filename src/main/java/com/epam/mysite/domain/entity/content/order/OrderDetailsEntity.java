package com.epam.mysite.domain.entity.content.order;

import com.google.gson.annotations.SerializedName;

public class OrderDetailsEntity {
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("fullname")
    private String fullName;
    private int price;

    public OrderDetailsEntity() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
