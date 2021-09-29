package com.epam.mysite.domain.entity.content.order;

import com.epam.mysite.domain.entity.content.EmployeeEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminOrderItemEntity {
    private int id;
    @SerializedName("service_name")
    private String serviceName;
    @SerializedName("service_item_id")
    private int serviceId;
    @SerializedName("employee_name")
    private String employeeName;
    private List<EmployeeEntity> employeeEntities;

    public AdminOrderItemEntity() {
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }
}
