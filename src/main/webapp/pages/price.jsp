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
<fmt:setBundle basename="price" var="pr"/>
<html lang="${current_lang}">
<head>
    <title>Price</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="stylesheet" href="styles/price.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header">
        <jsp:param name="price" value="active"/>
    </jsp:include>
    <div class="container">
        <div class="title">
            <h1 class="title__heading"><fmt:message bundle="${pr}" key="header_text"/></h1>
        </div>
        <div class="price-list">
            <div class="price-list__content">
                <div class="price-list__listing">
                    <c:forEach items="${services}" var="service">
                        <div class="price-list-category">
                            <div class="price-list-category__header">
                                <div class="price-list-category__title">${service.name}</div>
                            </div>
                            <div class="price-list-category__list">
                                <c:forEach items="${service.serviceItems}" var="item">
                                    <div class="price-list-item">
                                        <div class="price-list-item__info">
                                            <div class="price-list-item__name">${item.serviceName}</div>
                                            <div class="price-list-item__price">${item.price}
                                                <i class="fas fa-hryvnia uah"></i>
                                            </div>
                                        </div>
                                        <button class="price-list-item__btn btn_icon">
                                            <i class="fas fa-plus-circle"></i>
                                        </button>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="price-list__sidebar">
                <div class="cart-wrap">
                    <form class="cart form" action="${pageContext.request.contextPath}/create-order">
                        <div class="cart__header"><fmt:message bundle="${pr}" key="cart_header_text"/></div>
                        <div>
                            <div class="alert">
                                <div class="alert__icon">!</div>
                                <div class="alert__content"><fmt:message bundle="${pr}" key="alert_text"/></div>
                            </div>
                        </div>
                        <input id="datetime" type="datetime-local">
                        <div class="cart__footer">
                            <input type="submit" class="reserve__btn" disabled="disabled"
                                   value="<fmt:message bundle="${pr}" key="bt_text"/>" message="<fmt:message bundle="${pr}" key="date_error"/>">
                            <div class="cart__total">
                                <fmt:message bundle="${pr}" key="total_text"/>
                                <div class="cart__total-price">
                                    <div class="cart__price">0</div>
                                    <div class="cart__currency">
                                        <i class="fas fa-hryvnia uah"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/footer"/>
</div>
<script src="scripts/lib/jquery.js"></script>
<script src="scripts/price.js"></script>
</body>
</html>
