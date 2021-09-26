package com.epam.mysite.engine.database.repository.content.api;

import com.epam.mysite.domain.entity.content.ReviewsEntity;

import java.sql.SQLException;
import java.util.List;

public interface IReviewsRepository {
    List<ReviewsEntity> findAll() throws SQLException;

    boolean save(ReviewsEntity reviews) throws SQLException;
}
