package com.epam.mysite.engine.database.repository.content.impl;


import com.epam.mysite.entity.content.Header;

import java.util.List;

public interface IHeaderRepository {
    List<Header> findAll();
}
