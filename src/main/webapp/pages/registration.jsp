<%--
  Created by IntelliJ IDEA.
  User: eldas
  Date: 24.09.2021
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="registration" var="reg"/>
<html lang="${current_lang}">
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/footer.css">
    <link rel="stylesheet" href="styles/registration.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header"/>
    <div class="container">
        <div class="registration">
            <div class="title"><fmt:message bundle="${reg}" key="registr"/></div>
            <form success="<fmt:message bundle="${reg}" key="register_message"/>"
                  action="${pageContext.request.contextPath}/registration" method="post">
                <div class="user-details">
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="f_name"/></span>
                        <input type="text" name="name" placeholder="<fmt:message bundle="${reg}" key="f_name_text"/>"
                               required>
                    </div>
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="l_name"/></span>
                        <input type="text" name="surname" placeholder="<fmt:message bundle="${reg}" key="l_name_text"/>"
                               required>
                    </div>
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="user_name"/></span>
                        <input type="text" name="login"
                               placeholder="<fmt:message bundle="${reg}" key="user_name_text"/>" required>
                    </div>
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="em"/></span>
                        <input type="text" name="email" placeholder="<fmt:message bundle="${reg}" key="em_text"/>"
                               required>
                    </div>
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="ph"/></span>
                        <input type="tel" name="phone" placeholder="<fmt:message bundle="${reg}" key="ph_text"/>"
                               required>
                    </div>
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="pass"/></span>
                        <input type="password" name="password"
                               placeholder="<fmt:message bundle="${reg}" key="pass_text"/>" required>
                    </div>
                    <div class="input-box">
                        <span class="details"><fmt:message bundle="${reg}" key="conf_pass"/></span>
                        <input id="confirm" message="<fmt:message bundle="${reg}" key="error_confirm_pass"/>"
                               type="password" name="confirm_password"
                               placeholder="<fmt:message bundle="${reg}" key="conf_pass_text"/>" required>
                    </div>
                </div>
                <div class="gender-details">
                    <input type="radio" name="gender" value="male" id="dot-1">
                    <input type="radio" name="gender" value="female" id="dot-2">
                    <input type="radio" name="gender" value="prefer" id="dot-3">
                    <span class="gender-title"><fmt:message bundle="${reg}" key="gender"/></span>
                    <div class="category">
                        <label for="dot-1">
                            <span class="dot one"></span>
                            <span class="gender"><fmt:message bundle="${reg}" key="g_male"/></span>
                        </label>
                        <label for="dot-2">
                            <span class="dot two"></span>
                            <span class="gender"><fmt:message bundle="${reg}" key="g_female"/></span>
                        </label>
                        <label for="dot-3">
                            <span class="dot three"></span>
                            <span class="gender"><fmt:message bundle="${reg}" key="g_prefer"/></span>
                        </label>
                    </div>
                </div>
                <div class="button">
                    <input type="submit" value=<fmt:message bundle="${reg}" key="conf_registr"/>>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="/footer"/>
</div>
<script src="scripts/lib/jquery.js"></script>
<script src="scripts/registration.js"></script>
</body>
</html>
