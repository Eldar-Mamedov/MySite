package com.epam.mysite.engine.database.queries;

public enum UserQuery {
    GET_USER_BY_LOGIN("SELECT * FROM beauty_saloon._user where login = ?;"),
    INSERT_USER("INSERT INTO `beauty_saloon`.`_user` (`name`, `surname`, `phone`, `email`, `login`, `password`, `gender`) VALUES (?, ?, ?, ?, ?, ?, ?);"),
    INSERT_USER_ROLE_BY_ID_AND_ROLE_NAME("INSERT INTO beauty_saloon.user_role (id_role, user_id) SELECT id, ? FROM beauty_saloon._role WHERE _role.role = ?"),
    REMOVE_USER_BY_ID("DELETE FROM _user WHERE id = ?;");

    private final String query;

    UserQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
