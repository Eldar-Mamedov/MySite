package com.epam.mysite.domain.webservice;

public class CancelOrder {
    private String parentOrderId;

    public CancelOrder() {
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId;
    }
}
