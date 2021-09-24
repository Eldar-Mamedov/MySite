<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="footer" var="footer"/>
<div class="footer">
    <div class="develop">
        <p><fmt:message bundle="${footer}" key="first_text"/></p>
        <a href="mailto: eldasha02@gmail.com">eldasha02@gmail.com</a>
    </div>
    <div class="card">
        <img src="images/visa-mc.png" alt="visa">
        <p><fmt:message bundle="${footer}" key="second_text"/></p>
    </div>
    <div class="copy-write"><fmt:message bundle="${footer}" key="third_text"/></div>
</div>