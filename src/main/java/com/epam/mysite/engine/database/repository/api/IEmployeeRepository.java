package com.epam.mysite.engine.database.repository.api;

import com.epam.mysite.domain.entity.content.EmployeeEntity;
import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.User;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeRepository {
    List<EmployeeEntity> findAllEmployeesByServiceItemId(int serviceItemId) throws SQLException;

    boolean saveEmployeeSpecialityByEmployeeLogin(String login, List<Integer> serviceIds) throws SQLException;

    List<EmployeeEntity> findAllEmployeesByRoleExcludeUserByLogin(User user,Role role) throws SQLException;
}
