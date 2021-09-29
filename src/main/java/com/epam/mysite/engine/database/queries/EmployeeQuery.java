package com.epam.mysite.engine.database.queries;

public enum EmployeeQuery {
    GET_EMPLOYEE_BY_SERVICE_ITEM_ID("select \n" +
            "u.id,\n" +
            "CONCAT(u.name, \" \", u.surname) fullname\n" +
            "from employee_speciality es\n" +
            "join _user u on u.id = es.user_id\n" +
            "where es.service_item_id = ?"),
    INSERT_EMPLOYEE_SPECIALITY_BY_LOGIN("INSERT INTO employee_speciality (`user_id`, `service_item_id`) VALUES ( (SELECT id from _user u WHERE u.login = ?), ?)"),
    GET_EMPLOYEES_BY_ROLE_EXCLUDE_USER_BY_LOGIN("SELECT\n" +
            " u.id,\n" +
            "concat(u.name, \" \", u.surname) fullname\n" +
            " from _user u\n" +
            "join user_role us on us.user_id = u.id\n" +
            "join _role r on r.id = us.id_role\n" +
            "WHERE role = ? AND u.login != ?");
    private final String query;

    EmployeeQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
