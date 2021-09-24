package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.entity.content.ServiceCategory;

import java.util.List;

public interface IServiceCategoryRepository {
    List<ServiceCategory> findAll();
}
