<%--
  Created by IntelliJ IDEA.
  User: eldas
  Date: 24.09.2021
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="login" var="log"/>
<html lang="${current_lang}">
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="stylesheet" href="styles/login.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header"/>
    <div class="container">
        <div class="center">
            <h1><fmt:message bundle="${log}" key="title_text"/></h1>
            <form method="post">
                <div class="txt_field">
                    <input type="text" required>
                    <span></span>
                    <label><fmt:message bundle="${log}" key="username_text"/></label>
                </div>
                <div class="txt_field">
                    <input type="text" required>
                    <span></span>
                    <label><fmt:message bundle="${log}" key="password_text"/></label>
                </div>
                <div class="pass"><fmt:message bundle="${log}" key="forgot_password_text"/></div>
                <input type="submit" value=<fmt:message bundle="${log}" key="bt_login"/>>
                <div class="signup_link">
                    <fmt:message bundle="${log}" key="member"/> <a href="${pageContext.request.contextPath}/registration"><fmt:message bundle="${log}" key="signup"/></a>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="/footer"/>
</div>
</body>
</html>
