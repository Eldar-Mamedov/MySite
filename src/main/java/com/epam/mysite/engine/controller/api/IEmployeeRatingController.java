package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.content.EmployeeRating;

import java.util.List;

public interface IEmployeeRatingController {
    List<EmployeeRating> getAllRating();
    Response saveRating(EmployeeRating employeeRating);
}
