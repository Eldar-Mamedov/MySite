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
<fmt:setBundle basename="profile" var="prof"/>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/footer.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header"/>
    <div class="container">
        Profile<br>
        <fmt:message bundle="${prof}" key="name"/>:${user.getName()}
        <fmt:message bundle="${prof}" key="lastName"/>:${user.getSurname()}
    </div>
    <jsp:include page="/footer"/>
</div>
</body>
</html>
