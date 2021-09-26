package com.epam.mysite.engine.database.queries.content;

public enum ServicesQuery {
    GET_SERVICES_BY_LOCALE("SELECT si.id,  name, service_name, price  FROM beauty_saloon.service_category sc\n" +
            "join service_item si on si.category_id = sc.id\n" +
            "where sc.locale = ?;");

    private final String query;

    ServicesQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
