package com.epam.mysite.domain.entity.content.order;

import com.google.gson.annotations.SerializedName;

public class MasterOrderEntity {
    private int id;
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("date_service")
    private String dateService;
    private int price;
    private String status;

    public MasterOrderEntity() {
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

    public String getDateService() {
        return dateService;
    }

    public void setDateService(String dateService) {
        this.dateService = dateService;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
