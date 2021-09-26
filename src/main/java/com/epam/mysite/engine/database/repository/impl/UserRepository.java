package com.epam.mysite.engine.database.repository.impl;


import com.epam.mysite.domain.entity.UserEntity;
import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.UserQuery;
import com.epam.mysite.engine.database.repository.api.IUserRepository;
import lombok.extern.log4j.Log4j;

import java.sql.*;

import static com.epam.mysite.util.converter.EntityConverter.convert;


@Log4j
public class UserRepository implements IUserRepository {
    private final Connection connection;

    public UserRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public UserEntity findUserByLogin(String login) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserEntity userEntity = null;
        try {
            ps = connection.prepareStatement(UserQuery.GET_USER_BY_LOGIN.getQuery());
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                userEntity = convert(rs, UserEntity.class);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return userEntity;
    }

    @Override
    public boolean save(UserEntity userEntity, Role role) throws SQLException {
        PreparedStatement ps = null;
        ResultSet id = null;
        try {
            connection.setAutoCommit(false);
            connection.commit();
            ps = connection.prepareStatement(UserQuery.INSERT_USER.getQuery(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userEntity.getName());
            ps.setString(2, userEntity.getSurname());
            ps.setString(3, userEntity.getPhoneNumber());
            ps.setString(4, userEntity.getEmail());
            ps.setString(5, userEntity.getLogin());
            ps.setString(6, userEntity.getPassword());
            ps.setString(7, userEntity.getGender());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            id = ps.getGeneratedKeys();
            if (id.next()) {
                int field = id.getInt(1);
                userEntity.setId(field);
            }
            ps = connection.prepareStatement(UserQuery.INSERT_USER_ROLE_BY_ID_AND_ROLE_NAME.getQuery(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, userEntity.getId());
            ps.setString(2, role.name());
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            log.info(e.toString());
            connection.rollback();
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(id);
            connection.setAutoCommit(true);
        }
        return true;
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
