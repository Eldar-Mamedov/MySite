package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.entity.content.ServiceItem;

import java.util.List;

public interface IServiceItemRepository {
    List<ServiceItem> findAllBySubcategory(String subcategory);
}
