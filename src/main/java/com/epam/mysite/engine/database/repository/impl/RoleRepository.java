package com.epam.mysite.engine.database.repository.impl;

import com.epam.mysite.domain.entity.RoleEntity;
import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.RoleQuery;
import com.epam.mysite.engine.database.repository.api.IRoleRepository;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.mysite.util.converter.EntityConverter.convert;

@Log4j
public class RoleRepository implements IRoleRepository {
    private final Connection connection;

    public RoleRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public RoleEntity findRoleByUserLogin(String login) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        RoleEntity roleEntity = null;
        try {
            ps = connection.prepareStatement(RoleQuery.GET_ROLE_BY_USER_LOGIN.getQuery());
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                roleEntity = convert(rs, RoleEntity.class);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return roleEntity;
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
