package com.epam.mysite.engine.controller.api;

import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.content.Reviews;

import java.util.List;

public interface IReviewsController {
    List<Reviews> getAllReviews();
    Response saveReview(Reviews reviews);
}
