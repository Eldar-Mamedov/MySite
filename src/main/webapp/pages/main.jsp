<%--
  Created by IntelliJ IDEA.
  User: eldas
  Date: 22.09.2021
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="main" var="main"/>
<html lang="${current_lang}">
<head>
    <title>Beauty Saloon</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="stylesheet" href="styles/main.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header">
        <jsp:param name="home" value="active"/>
    </jsp:include>
    <div class="container">
        <div class="maintenance">
            <h1 class="title"><fmt:message bundle="${main}" key="first_header"/></h1>
            <p class="text"><strong><fmt:message bundle="${main}" key="first_text_strong"/></strong> <fmt:message bundle="${main}" key="first_text"/></p>
            <h1 class="title"><fmt:message bundle="${main}" key="second_header"/></h1>
            <p class="text"><fmt:message bundle="${main}" key="second_text"/></p>
            <p class="text"><fmt:message bundle="${main}" key="third_text"/></p>
            <p class="text"><fmt:message bundle="${main}" key="fourth_text"/></p>
            <p class="text"><fmt:message bundle="${main}" key="fifth_text"/></p>
            <h1 class="class"><fmt:message bundle="${main}" key="third_header"/></h1>
            <p class="text"><fmt:message bundle="${main}" key="sixth_text"/></p>
            <p class="text"><fmt:message bundle="${main}" key="seventh_text"/></p>
            <p class="text"></p>
            <ul>
                <li><fmt:message bundle="${main}" key="first_li"/></li>
                <li><fmt:message bundle="${main}" key="second_li"/></li>
                <li><fmt:message bundle="${main}" key="third_li"/></li>
                <li><fmt:message bundle="${main}" key="fourth_li"/></li>
                <li><fmt:message bundle="${main}" key="fifth_li"/></li>
                <li><fmt:message bundle="${main}" key="sixth_li"/></li>
                <li><fmt:message bundle="${main}" key="seventh_li"/></li>
                <li><fmt:message bundle="${main}" key="eighth_li"/></li>
            </ul>
            <p class="text"><fmt:message bundle="${main}" key="eighth_text"/></p>
            <p class="text"><fmt:message bundle="${main}" key="ninth_text"/></p>
        </div>
    </div>
    <jsp:include page="/footer"/>
</div>
</body>
</html>
