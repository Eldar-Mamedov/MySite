<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="admin-order" var="adm"/>
<div class="columns">
    <div class="column__header">
        <span><fmt:message bundle="${adm}" key="order"/></span>
        <span><fmt:message bundle="${adm}" key="client"/></span>
        <span><fmt:message bundle="${adm}" key="phone"/></span>
        <span><fmt:message bundle="${adm}" key="total"/></span>
        <span><fmt:message bundle="${adm}" key="date"/></span>
        <span><fmt:message bundle="${adm}" key="status"/></span>
    </div>
    <c:choose>
        <c:when test="${not empty orderItems}">
            <c:forEach items="${orderItems}" var="item">
                <div class="column__data">
                    <div class="column-items">
                        <span>${item.id}</span>
                        <span>${item.clientName}</span>
                        <span>${item.phone}</span>
                        <span>${item.totalPrice}</span>
                        <span>${item.dateOrder}</span>
                        <span>${item.status}</span>
                    </div>
                    <button class="item_btn"><fmt:message bundle="${adm}" key="button"/></button>
                    <c:choose>
                        <c:when test="${item.status == 'В обработке' || item.status == 'In process'}">
                            <div class="item-processing">
                                <form>
                                    <c:forEach items="${item.orderItemEntities}" var="order">
                                        <div class="service-item">
                                            <span>${order.serviceName}</span>
                                            <select id="${order.id}" name="employee"
                                                    message="<fmt:message bundle="${adm}" key="error_employee"/>">
                                                <option selected="selected" value="default"><fmt:message bundle="${adm}"
                                                                                                         key="employee_select"/></option>
                                                <c:forEach items="${order.employeeEntities}" var="empoyee">
                                                    <option value="${empoyee.id}">${empoyee.fullName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </c:forEach>
                                    <div class="buttons">
                                        <button id="process" action="${pageContext.request.contextPath}/process-order">
                                            <fmt:message bundle="${adm}" key="process_btn"/></button>
                                        <button id="cancelled" parent="${item.parentOrderId}"
                                                action="${pageContext.request.contextPath}/cancel-order"><fmt:message
                                                bundle="${adm}" key="cancel_btn"/></button>
                                    </div>
                                    <div class="date">
                                        <span><fmt:message bundle="${adm}" key="date_service"/></span>
                                        <input id="datetime" type="datetime-local"
                                               message="<fmt:message bundle="${adm}" key="error_date"/>">
                                    </div>
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="item_cont">
                                <c:forEach items="${item.orderItemEntities}" var="item">
                                    <div class="item_det">
                                        <span>Услуга: ${item.serviceName}</span>
                                        <span>Работник: ${item.employeeName}</span>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <div class="no-order__message">
                <span class="message"><fmt:message bundle="${adm}" key="no_order_message"/></span>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script src="scripts/lib/jquery.js"></script>
<script src="scripts/admin-orders.js"></script>
