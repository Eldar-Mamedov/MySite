package com.epam.mysite.engine.database.repository.api;


import com.epam.mysite.entity.User;

public interface IUserRepository {
    User findUserByLogin(String login);
}
