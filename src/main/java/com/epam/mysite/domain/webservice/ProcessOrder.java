package com.epam.mysite.domain.webservice;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.List;

public class ProcessOrder {
    @SerializedName("date")
    private Timestamp dateTime;
    private List<ProcessItem> items;

    public ProcessOrder() {
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public List<ProcessItem> getItems() {
        return items;
    }

    public void setItems(List<ProcessItem> items) {
        this.items = items;
    }
}
