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
    <link rel="stylesheet" href="styles/profile.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header"/>
    <div class="container">
        <h1 class="header"><fmt:message bundle="${prof}" key="prof"/></h1>
        <div class="user-container">
            <i class="fas fa-user"></i>
            <div class="user-details">
                <h3><fmt:message bundle="${prof}" key="info_prof"/>:</h3>
                <h4><fmt:message bundle="${prof}" key="name"/>:${user.getName()}</h4>
                <h4><fmt:message bundle="${prof}" key="lastName"/>:${user.getSurname()}</h4>
                <div class="info_user">
                    <div class="info_data">
                        <div class="data">
                            <h4><fmt:message bundle="${prof}" key="email"/>:${user.getEmail()}</h4>
                        </div>
                        <div class="data">
                            <h4><fmt:message bundle="${prof}" key="login_prof"/>:${user.getLogin()}</h4>
                        </div>
                        <div class="data">
                            <h4><fmt:message bundle="${prof}" key="number_prof"/>:${user.getPhoneNumber()}</h4>
                        </div>
                        <div class="data">
                            <h4><fmt:message bundle="${prof}" key="gender_prof"/>:${user.getGender()}</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="/footer"/>
</div>
</body>
</html>
