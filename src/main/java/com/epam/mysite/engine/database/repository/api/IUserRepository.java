package com.epam.mysite.engine.database.repository.api;


import com.epam.mysite.domain.entity.UserEntity;
import com.epam.mysite.domain.enums.Role;

import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
    UserEntity findUserByLogin(String login) throws SQLException;

    boolean save(UserEntity userEntity, Role role) throws SQLException;

    boolean delete(List<Integer> userIds) throws SQLException;
}
