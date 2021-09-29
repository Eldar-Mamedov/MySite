package com.epam.mysite.domain.webservice;

import java.sql.Timestamp;
import java.util.List;

public class CreateOrder {
    private Timestamp dateTime;
    private List<String> services;

    public CreateOrder() {
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
