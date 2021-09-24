package com.epam.mysite.engine.database.repository.impl;


import com.epam.mysite.entity.User;

public interface IUserRepository {
    User findUserByLogin(String login);
}
