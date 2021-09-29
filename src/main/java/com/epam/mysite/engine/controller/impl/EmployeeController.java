package com.epam.mysite.engine.controller.impl;

import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.EmployeeStaff;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.controller.api.IEmployeeController;
import com.epam.mysite.engine.database.repository.api.IEmployeeRepository;
import com.epam.mysite.engine.database.repository.impl.EmployeeRepository;

import java.sql.SQLException;

public class EmployeeController implements IEmployeeController {
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();

    @Override
    public Response addSpecialityToEmployee(User user) {
        Response response = new Response();
        try {
            boolean result = employeeRepository.saveEmployeeSpecialityByEmployeeLogin(user.getLogin(), user.getServiceIds());
            if (result) {
                response.setStatus(200);
            }
        } catch (SQLException e) {
            response.setStatus(500);
            response.setBody(e.getMessage());
        }
        return response;
    }

    @Override
    public EmployeeStaff getEmployeeStaffExcludeCurrentUser(User user) {
        EmployeeStaff employeeStaff;
        try {
            employeeStaff = new EmployeeStaff();
            employeeStaff.setAdmins(employeeRepository.findAllEmployeesByRoleExcludeUserByLogin(user, Role.Admin));
            employeeStaff.setMasters(employeeRepository.findAllEmployeesByRoleExcludeUserByLogin(user, Role.Master));
        } catch (SQLException e) {
            employeeStaff = new EmployeeStaff();
        }
        return employeeStaff;
    }
}
