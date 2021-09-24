package com.epam.mysite.entity.content;

import com.google.gson.annotations.SerializedName;

public class ServiceItem {
    private int id;
    @SerializedName(value = "subcategory_id")
    private int subcategoryId;
    @SerializedName(value = "service_name")
    private String serviceName;
    private int price;
    private String locale;

    public ServiceItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
