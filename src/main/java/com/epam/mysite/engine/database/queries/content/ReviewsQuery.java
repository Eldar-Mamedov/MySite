package com.epam.mysite.engine.database.queries.content;

public enum ReviewsQuery {
    GET_REVIEWS("SELECT r.id, creation_date, message, rating_mark, name, surname FROM beauty_saloon.reviews as r\n" +
            "join _user u on u.id = r.client_id\n" +
            "order by creation_date"),
    INSERT_REVIEW("//TODO insert sql");

    private final String query;

    ReviewsQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
