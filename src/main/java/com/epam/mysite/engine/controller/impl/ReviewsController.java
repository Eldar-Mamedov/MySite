package com.epam.mysite.engine.controller.impl;

import com.epam.mysite.domain.entity.content.ReviewsEntity;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.content.Reviews;
import com.epam.mysite.engine.controller.api.IReviewsController;
import com.epam.mysite.engine.database.repository.content.api.IReviewsRepository;
import com.epam.mysite.engine.database.repository.content.impl.ReviewsRepository;
import com.epam.mysite.util.IParser;
import com.google.gson.reflect.TypeToken;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewsController implements IReviewsController {
    private final IReviewsRepository reviewsRepository = new ReviewsRepository();

    @Override
    public List<Reviews> getAllReviews() {
        List<Reviews> reviews;
        try {
            List<ReviewsEntity> reviewsEntities = reviewsRepository.findAll();
            reviews = IParser.fromJson(IParser.toJson(reviewsEntities), new TypeToken<List<Reviews>>() {
            }.getType());
        } catch (SQLException e) {
            reviews = new ArrayList<>();
        }
        return reviews;
    }

    @Override
    public Response saveReview(Reviews reviews) {
        Response response = new Response();
        ReviewsEntity reviewsEntity = IParser.fromJson(IParser.toJson(reviews), ReviewsEntity.class);
        try {
            reviewsRepository.save(reviewsEntity);
        } catch (SQLException e) {
            response.setStatus(500);
        }
        return response;
    }
}
