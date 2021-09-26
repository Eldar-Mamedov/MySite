package com.epam.mysite.domain.webservice.content;

import java.util.ArrayList;
import java.util.List;

public class Services {
    private String name;
    private List<ServiceItem> serviceItems = new ArrayList<>();

    public Services() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceItem> getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(List<ServiceItem> serviceItems) {
        this.serviceItems = serviceItems;
    }
}
