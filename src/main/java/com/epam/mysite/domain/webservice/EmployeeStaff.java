package com.epam.mysite.domain.webservice;

import com.epam.mysite.domain.entity.content.EmployeeEntity;

import java.util.List;

public class EmployeeStaff {
    private List<EmployeeEntity> masters;
    private List<EmployeeEntity> admins;

    public EmployeeStaff() {
    }

    public List<EmployeeEntity> getMasters() {
        return masters;
    }

    public void setMasters(List<EmployeeEntity> masters) {
        this.masters = masters;
    }

    public List<EmployeeEntity> getAdmins() {
        return admins;
    }

    public void setAdmins(List<EmployeeEntity> admins) {
        this.admins = admins;
    }
}
