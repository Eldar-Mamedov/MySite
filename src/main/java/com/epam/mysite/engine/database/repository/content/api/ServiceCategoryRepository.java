package com.epam.mysite.engine.database.repository.content.api;


import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.ServiceCategoryQuery;
import com.epam.mysite.engine.database.repository.content.impl.IServiceCategoryRepository;
import com.epam.mysite.engine.database.repository.converter.EntityConverter;
import com.epam.mysite.entity.content.ServiceCategory;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Log4j
public class ServiceCategoryRepository implements IServiceCategoryRepository {
    private final Connection connection;

    public ServiceCategoryRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }
    @Override
    public List<ServiceCategory> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ServiceCategory> categoryList = new LinkedList<>();
        try {
            ps = connection.prepareStatement(ServiceCategoryQuery.GET_SERVICE_BY_LOCALE.getQuery());
            ps.setString(1,System.getProperty("locale"));
            rs = ps.executeQuery();
            if(rs.next()){
                ServiceCategory serviceCategory = EntityConverter.convert(rs,ServiceCategory.class);
                categoryList.add(serviceCategory);
            }
        } catch (SQLException exception) {
            log.info(exception.toString());
        }finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return categoryList;
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
