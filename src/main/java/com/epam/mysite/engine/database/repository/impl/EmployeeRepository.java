package com.epam.mysite.engine.database.repository.impl;

import com.epam.mysite.domain.entity.content.EmployeeEntity;
import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.EmployeeQuery;
import com.epam.mysite.engine.database.repository.api.IEmployeeRepository;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.mysite.util.converter.EntityConverter.convert;

@Log4j
public class EmployeeRepository implements IEmployeeRepository {
    private final Connection connection;

    public EmployeeRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public List<EmployeeEntity> findAllEmployeesByServiceItemId(int serviceItemId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(EmployeeQuery.GET_EMPLOYEE_BY_SERVICE_ITEM_ID.getQuery());
            ps.setInt(1, serviceItemId);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmployeeEntity employeeEntity = convert(rs, EmployeeEntity.class);
                employeeEntities.add(employeeEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return employeeEntities;
    }

    @Override
    public boolean saveEmployeeSpecialityByEmployeeLogin(String login, List<Integer> serviceIds) throws SQLException {
        PreparedStatement ps = null;
        try {
            connection.setAutoCommit(false);
            connection.commit();
            ps = connection.prepareStatement(EmployeeQuery.INSERT_EMPLOYEE_SPECIALITY_BY_LOGIN.getQuery());
            for (Integer id : serviceIds) {
                ps.setString(1, login);
                ps.setInt(2, id);
                ps.addBatch();
            }
            int[] orders = ps.executeBatch();
            for (int i : orders) {
                if (i != 1) {
                    return false;
                }
            }
        } catch (Exception e) {
            log.info(e.toString());
            connection.rollback();
            throw e;
        } finally {
            closePreparedStatement(ps);
            connection.setAutoCommit(true);
        }
        return true;
    }

    @Override
    public List<EmployeeEntity> findAllEmployeesByRoleExcludeUserByLogin(User user, Role role) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(EmployeeQuery.GET_EMPLOYEES_BY_ROLE_EXCLUDE_USER_BY_LOGIN.getQuery());
            ps.setString(1, role.toString());
            ps.setString(2, user.getLogin());
            rs = ps.executeQuery();
            while (rs.next()) {
                EmployeeEntity employeeEntity = convert(rs, EmployeeEntity.class);
                employeeEntities.add(employeeEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return employeeEntities;
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.info(e.toString());
            }
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.info(e.toString());
            }
        }
    }
}
