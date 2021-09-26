package com.epam.mysite.engine.database.repository.content.impl;

import com.epam.mysite.domain.entity.content.ReviewsEntity;
import com.epam.mysite.engine.database.DataBaseConnection;
import com.epam.mysite.engine.database.queries.content.ReviewsQuery;
import com.epam.mysite.engine.database.repository.content.api.IReviewsRepository;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.mysite.util.converter.EntityConverter.convert;

@Log4j
public class ReviewsRepository implements IReviewsRepository {
    private final Connection connection;

    public ReviewsRepository() {
        connection = DataBaseConnection.getInstance().getConnection();
    }

    @Override
    public List<ReviewsEntity> findAll() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ReviewsEntity> reviewsEntities = new ArrayList<>();
        try {
            ps = connection.prepareStatement(ReviewsQuery.GET_REVIEWS.getQuery());
            rs = ps.executeQuery();
            while (rs.next()) {
                ReviewsEntity reviewsEntity = convert(rs, ReviewsEntity.class);
                reviewsEntities.add(reviewsEntity);
            }
        } catch (SQLException e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return reviewsEntities;
    }

    @Override
    public boolean save(ReviewsEntity reviewsEntity) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(ReviewsQuery.INSERT_REVIEW.getQuery());
            //TODO set value for ps
            if (ps.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            log.info(e.toString());
            throw e;
        } finally {
            closePreparedStatement(ps);
        }
        return true;
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
