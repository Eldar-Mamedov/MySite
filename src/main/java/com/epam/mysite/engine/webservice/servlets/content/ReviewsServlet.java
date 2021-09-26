package com.epam.mysite.engine.webservice.servlets.content;

import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.content.Reviews;
import com.epam.mysite.engine.controller.api.IReviewsController;
import com.epam.mysite.engine.controller.impl.ReviewsController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.mysite.util.HttpServletRequestHelper.sendResponse;
import static com.epam.mysite.util.converter.RequestConverter.convertFromBody;

@WebServlet(name = "ReviewsServlet", urlPatterns = "/reviews")
public class ReviewsServlet extends HttpServlet {
    private final IReviewsController reviewsController = new ReviewsController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("current_page", "reviews");
        req.setAttribute("current_lang", System.getProperty("locale"));
        req.setAttribute("reviewItems", reviewsController.getAllReviews());
        RequestDispatcher rd = req.getRequestDispatcher("pages/reviews.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Reviews reviews = convertFromBody(req, Reviews.class);
        Response response = reviewsController.saveReview(reviews);
        if (response.getStatus() == 200) {
            response.setRedirect(String.format("%s/reviews", getServletContext().getContextPath()));
        }
        sendResponse(resp, response);
    }
}