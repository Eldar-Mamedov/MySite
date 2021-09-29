package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.webservice.EmployeeStaff;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;

public interface IEmployeeController {
    Response addSpecialityToEmployee(User user);

    EmployeeStaff getEmployeeStaffExcludeCurrentUser(User user);
}
