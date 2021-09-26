package com.epam.mysite.domain.entity.content;

import java.util.ArrayList;
import java.util.List;

public class ServicesEntity {
    private int id;
    private String name;
    private List<ServiceItemEntity> serviceItems = new ArrayList<>();

    public ServicesEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceItemEntity> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<ServiceItemEntity> serviceItems) {
        this.serviceItems = serviceItems;
    }

    public void addServiceItem(ServiceItemEntity serviceItem) {
        this.serviceItems.add(serviceItem);
    }
}
