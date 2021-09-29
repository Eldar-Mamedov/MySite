<%--
  Created by IntelliJ IDEA.
  User: eldas
  Date: 26.09.2021
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="history" var="his"/>
<html>
<head>
    <title>History</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="stylesheet" href="styles/history-orders.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header"/>
    <div class="container">
        <div class="columns">
            <div class="column__header">
                <span><fmt:message bundle="${his}" key="order"/></span>
                <span><fmt:message bundle="${his}" key="category_name"/></span>
                <span><fmt:message bundle="${his}" key="date_create"/></span>
                <span><fmt:message bundle="${his}" key="date_service"/></span>
                <span><fmt:message bundle="${his}" key="total_price"/></span>
                <span><fmt:message bundle="${his}" key="status"/></span>
            </div>
            <c:choose>
                <c:when test="${not empty orderItems}">
                    <c:forEach items="${orderItems}" var="order">
                        <div class="column__data">
                            <div class="column-items">
                                <span>${order.id}</span>
                                <span>${order.serviceName}</span>
                                <span>${order.dateOrder}</span>
                                <span>${order.dateService}</span>
                                <span>${order.total}</span>
                                <span>${order.status}</span>
                            </div>
                            <button class="item_btn"><fmt:message bundle="${his}" key="view_details"/></button>
                            <div class="details">
                                <c:forEach items="${order.detailsEntityMap}" var="map">
                                    <div class="detail_item">
                                        <h2><fmt:message bundle="${his}" key="category_name"/>: ${map.key}</h2>
                                        <c:forEach items="${map.value}" var="item">
                                            <div class="item_details">
                                                <h4><fmt:message bundle="${his}" key="service_name"/>: ${item.serviceName}</h4>
                                                <h4><fmt:message bundle="${his}" key="master_name"/>: ${item.fullName}</h4>
                                                <h4><fmt:message bundle="${his}" key="price"/>: ${item.price}</h4>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no-order__message">
                        <span class="message"><fmt:message bundle="${his}" key="no_order_message"/></span>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <jsp:include page="/footer"/>
    <script src="scripts/lib/jquery.js"></script>
    <script src="scripts/history.js"></script>
</div>
</body>
</html>
