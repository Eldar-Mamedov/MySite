package com.epam.mysite.engine.database.queries;

public enum UserQuery {
    GET_USER_BY_LOGIN("SELECT * FROM beauty_saloon._user where login = ?;");

    private final String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
