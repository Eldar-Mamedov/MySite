package com.epam.mysite.engine.database.repository.api;


import com.epam.mysite.domain.entity.UserEntity;
import com.epam.mysite.domain.enums.Role;

import java.sql.SQLException;

public interface IUserRepository {
    UserEntity findUserByLogin(String login) throws SQLException;
    boolean save(UserEntity userEntity, Role role) throws SQLException;
}
