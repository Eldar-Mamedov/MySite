package com.epam.mysite.engine.database.queries.content;

public enum ServiceCategoryQuery {
    GET_SERVICE_BY_LOCALE("SELECT * FROM beauty_saloon.service_category where locale = ?;");
    private final String query;

    ServiceCategoryQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
