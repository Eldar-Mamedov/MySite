package com.epam.mysite.domain.webservice.content;

import com.google.gson.annotations.SerializedName;

public class ServiceItem {
    private int id;
    @SerializedName("service_name")
    private String serviceName;
    private int price;

    public ServiceItem() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
