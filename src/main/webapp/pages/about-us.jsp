<%--
  Created by IntelliJ IDEA.
  User: eldas
  Date: 22.09.2021
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="about-us" var="us"/>
<html lang="${current_lang}">
<head>
    <title>About Us</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/about-us.css">
    <link rel="stylesheet" href="styles/footer.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header">
        <jsp:param name="about" value="active"/>
    </jsp:include>
    <div class="container">
        <div class="info">
            <div class="info__item">
                <div class="item__container">
                    <img class="item__img" src="images/about-us.png" alt="About Us">
                    <div class="item__text">
                        <h1><fmt:message bundle="${us}" key="first_text_strong"/></h1>
                        <p><fmt:message bundle="${us}" key="first_text"/></p>
                        <p><fmt:message bundle="${us}" key="second_text"/></p>
                        <p><fmt:message bundle="${us}" key="third_text"/></p>
                    </div>
                </div>
            </div>
            <div class="info__item">
                <div class="item__container">
                    <div class="item__text">
                        <p class="first"><fmt:message bundle="${us}" key="fourth_text"/></p>
                        <p><fmt:message bundle="${us}" key="fifth_text"/></p>
                        <p><fmt:message bundle="${us}" key="sixth_text"/></p>
                        <p><fmt:message bundle="${us}" key="seventh_text"/></p>
                    </div>
                    <img class="item__img" src="images/salon.jpg" alt="About Us">
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/footer" />
</div>
</body>
</html>
