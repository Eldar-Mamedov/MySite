package com.epam.mysite.engine.database.queries.content;

public enum ServiceItemQuery {
    GET_ALL_BY_SUBCATEGORY("SELECT si.id,si.subcategory_id,si.service_name,si.price,si.locale FROM beauty_saloon.service_item as si \n" +
            "join service_subcategory on si.subcategory_id = service_subcategory.id\n" +
            "where service_subcategory.subcategory_name = ? and si.locale = ?;");
    private final String query;

    ServiceItemQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
