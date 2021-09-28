package com.epam.mysite.domain.webservice.content;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Orders {
    @SerializedName("date_order")
    private Timestamp dateOrder;
    @SerializedName("employee_id")
    private int employeeId;
    @SerializedName("is_payed")
    private boolean isPayed;
    @SerializedName("date_service")
    private Timestamp dateService;

    public Orders() {
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public Timestamp getDateService() {
        return dateService;
    }

    public void setDateService(Timestamp dateService) {
        this.dateService = dateService;
    }
}
