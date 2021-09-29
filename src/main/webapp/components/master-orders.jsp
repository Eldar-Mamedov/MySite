<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="master-order" var="mast"/>
<div class="columns">
    <div class="column__header">
        <span><fmt:message bundle="${mast}" key="order"/></span>
        <span><fmt:message bundle="${mast}" key="service"/></span>
        <span><fmt:message bundle="${mast}" key="date"/></span>
        <span><fmt:message bundle="${mast}" key="price"/></span>
        <span><fmt:message bundle="${mast}" key="status"/></span>
    </div>
    <c:choose>
        <c:when test="${not empty orderItems}">
            <c:forEach items="${orderItems}" var="item">
                <div class="column__data">
                    <div class="column-items" action="${pageContext.request.contextPath}/update-order">
                        <span>${item.id}</span>
                        <span>${item.serviceName}</span>
                        <span>${item.dateService}</span>
                        <span>${item.price}</span>
                        <span>${item.status}</span>
                    </div>
                    <button class="item_btn"><fmt:message bundle="${mast}" key="button"/></button>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="no-order__message">
                <span class="message"><fmt:message bundle="${mast}" key="no_order_message"/></span>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="scripts/lib/jquery.js"></script>
<script src="scripts/master-orders.js"></script>