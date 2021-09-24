package com.epam.mysite.engine.database.queries.content;

public enum ServiceSubcategoryQuery {
    GET_ALL_BY_CATEGORY("SELECT sbc.id,sbc.category_id,sbc.subcategory_name,sbc.locale FROM beauty_saloon.service_subcategory as sbc \n" +
            "join service_category on service_category.id = sbc.category_id\n" +
            "where service_category.name = ? and sbc.locale = ?;");
    private final String query;

    ServiceSubcategoryQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
