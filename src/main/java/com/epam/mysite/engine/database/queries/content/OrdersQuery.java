package com.epam.mysite.engine.database.queries.content;

public enum OrdersQuery {
    INSERT_CLIENT_ORDER("INSERT INTO beauty_saloon.orders (date_order, client_id, service_item_id, parent_order_id, order_status_id) VALUES (?, (SELECT id FROM _user WHERE login = ?), (SELECT id from service_item where service_name = ?), ?, (SELECT id from order_status WHERE status = ?));"),
    GET_MAIN_PART_ORDER_FOR_CLIENT("select o.id, \n" +
            "(\n" +
            "SELECT sc.name from service_item\n" +
            "join (SELECT same_item_id from service_item WHERE id = o.id) s on s.same_item_id = service_item.same_item_id\n" +
            "join service_category sc on sc.id = service_item.category_id\n" +
            "where service_item.locale = ? \n" +
            ") as service_name, o.date_order, o.date_service, sum(si.price) as total, os.status, o.parent_order_id  from orders o\n" +
            "join _user u on u.id = o.client_id\n" +
            "join order_status os on os.id = o.order_status_id\n" +
            "join service_item si on si.id = o.service_item_id\n" +
            "where u.login = ? group by o.parent_order_id\n" +
            "\n"),
    GET_ORDER_DETAILS_FOR_CLIENT("select\n" +
            "(\n" +
            "SELECT name FROM service_category\n" +
            "join (SELECT category_id from service_item ssi\n" +
            "join (SELECT service_item.same_item_id FROM service_item\n" +
            "WHERE o.service_item_id = service_item.id\n" +
            ") sssi on sssi.same_item_id = ssi.same_item_id\n" +
            "WHERE locale = ?) ssi on ssi.category_id = service_category.id\n" +
            ") as category_name, \n" +
            "(\n" +
            "SELECT service_name from service_item ssi\n" +
            "join (SELECT service_item.same_item_id FROM service_item\n" +
            "WHERE o.service_item_id = service_item.id\n" +
            ") sssi on sssi.same_item_id = ssi.same_item_id\n" +
            "WHERE locale = ?\n" +
            ") as service_name, (SELECT CONCAT(name, \" \", surname) FROM _user where id=o.employee_id) as fullname,si.price  from orders o\n" +
            "join _user u on u.id = o.client_id\n" +
            "join service_item si on si.id = o.service_item_id\n" +
            "where u.login = ? AND o.parent_order_id = ?;"),
    GET_ORDER_FOR_MASTER("select\n" +
            "o.id,\n" +
            "( \n" +
            "SELECT service_name from service_item ssi\n" +
            "join (SELECT service_item.same_item_id FROM service_item\n" +
            "WHERE o.service_item_id = service_item.id\n" +
            ") sssi on sssi.same_item_id = ssi.same_item_id\n" +
            "WHERE locale = ?\n" +
            ") as service_name,\n" +
            "o.date_service,\n" +
            "si.price,\n" +
            "os.status from orders o\n" +
            "join order_status os on os.id = o.order_status_id\n" +
            "join _user u on u.id = o.employee_id\n" +
            "join service_item si on si.id = o.service_item_id\n" +
            "where u.login = ? and os.status = ?"),
    UPDATE_ORDER_STATUS("UPDATE orders\n" +
            "SET order_status_id = (SELECT id from order_status where status = ?)\n" +
            "WHERE id = ?"),
    GET_ORDERS_FOR_ADMIN_ORDER_BY_STATUS("select\n" +
            "o.id,\n" +
            "CONCAT(u.name, \" \", u.surname) client_name,\n" +
            "u.phone,\n" +
            "sum(si.price) total,\n" +
            "o.date_order,\n" +
            "os.status,\n" +
            "o.parent_order_id\n" +
            "from orders o\n" +
            "join _user u on u.id = o.client_id\n" +
            "join service_item si on si.id = o.service_item_id\n" +
            "join order_status os on os.id = o.order_status_id\n" +
            "group by parent_order_id\n" +
            "order by os.id"),
    GET_ORDER_ITEMS_BY_PARENT_ORDER_ID("select\n" +
            "o.id,\n" +
            "(\n" +
            "SELECT service_name from service_item ssi\n" +
            "join (SELECT service_item.same_item_id FROM service_item\n" +
            "WHERE o.service_item_id = service_item.id\n" +
            ") sssi on sssi.same_item_id = ssi.same_item_id\n" +
            "WHERE locale = ?\n" +
            ") as service_name,\n" +
            "(\n" +
            "SELECT CONCAT(u.name, \" \", u.surname) FROM _user u\n" +
            "where id = o.employee_id\n" +
            ") employee_name,\n" +
            "service_item_id\n" +
            "from orders o\n" +
            "where o.parent_order_id = ?"),
    UPDATE_ORDER_STATUS_BY_PARENT_ORDER_ID("UPDATE orders\n" +
            "SET order_status_id = (SELECT id from order_status where status = ?)\n" +
            "WHERE parent_order_id = ?;"),
    UPDATE_ORDER_STATUS_DATE_SERVICE_EMPLOYEE_ID_BY_ORDER_ID("UPDATE orders\n" +
            "SET order_status_id = (SELECT id FROM order_status WHERE status = ?), employee_id = ?, date_service = ?\n" +
            "WHERE id = ?");

    private final String query;

    OrdersQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
