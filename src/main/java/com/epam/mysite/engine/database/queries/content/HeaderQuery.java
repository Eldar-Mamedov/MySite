package com.epam.mysite.engine.database.queries.content;

public enum HeaderQuery {
    GET_ALL("SELECT * FROM beauty_saloon.header where locale = ?;");
    private final String query;

    HeaderQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
