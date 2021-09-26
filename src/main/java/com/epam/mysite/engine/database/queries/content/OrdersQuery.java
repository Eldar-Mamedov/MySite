package com.epam.mysite.engine.database.queries.content;

public enum OrdersQuery {
    GET_ORDERS("//TODO sql for admin orders"),
    GET_ORDERS_BY_CLIENT("//TODO sql for client orders"),
    GET_ORDERS_BY_MASTER("//TODO sql for master orders"),
    INSERT_ORDER("//TODO insert sql");

    private final String query;

    OrdersQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
