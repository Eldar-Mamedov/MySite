package com.epam.mysite.engine.database.queries;

public enum RoleQuery {
    GET_ROLE_BY_USER_LOGIN("SELECT role FROM beauty_saloon._role as r\n" +
            "join user_role usr on usr.id_role = r.id\n" +
            "join _user u on u.id = usr.user_id\n" +
            "where u.login = ?");

    private final String query;

    RoleQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
