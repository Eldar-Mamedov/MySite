package com.epam.mysite.engine.database.repository.api;


import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.UserQuery;
import com.epam.mysite.engine.database.repository.impl.IUserRepository;
import com.epam.mysite.entity.User;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.mysite.engine.database.repository.converter.EntityConverter.convert;


@Log4j
public class UserRepository implements IUserRepository {
    private final Connection connection;

    public UserRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public User findUserByLogin(String login) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            ps = connection.prepareStatement(UserQuery.GET_USER_BY_LOGIN.getQuery());
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = convert(rs, User.class);
            }
        } catch (SQLException e) {
            log.info(e.toString());
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return user;
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
