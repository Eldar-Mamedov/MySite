package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.ServiceSubcategoryQuery;
import com.epam.mysite.engine.database.repository.content.api.IServiceSubcategoryRepository;
import com.epam.mysite.engine.database.repository.converter.EntityConverter;
import com.epam.mysite.entity.content.ServiceSubcategory;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Log4j
public class ServiceSubcategoryRepository implements IServiceSubcategoryRepository {
    private final Connection connection;

    public ServiceSubcategoryRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }
    @Override
    public List<ServiceSubcategory> findAllByCategory(String category) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ServiceSubcategory> subcategories = new LinkedList<>();
        try {
            ps = connection.prepareStatement(ServiceSubcategoryQuery.GET_ALL_BY_CATEGORY.getQuery());
            ps.setString(1,category);
            ps.setString(2,System.getProperty("locale"));
            rs = ps.executeQuery();
            if(rs.next()){
                ServiceSubcategory serviceSubcategory = EntityConverter.convert(rs,ServiceSubcategory.class);
                subcategories.add(serviceSubcategory);
            }
        } catch (SQLException exception) {
            log.info(exception.toString());
        }finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return subcategories;
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
