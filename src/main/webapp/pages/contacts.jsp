<%--
  Created by IntelliJ IDEA.
  User: eldas
  Date: 22.09.2021
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>
<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="contacts" var="con"/>
<html lang="${current_lang}">
<head>
    <title>Contacts</title>
    <link rel="stylesheet" href="styles/header.css">
    <link rel="stylesheet" href="styles/contacts.css">
    <link rel="stylesheet" href="styles/footer.css">
    <script src="https://kit.fontawesome.com/e983c4e4ff.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/header">
        <jsp:param name="contacts" value="active"/>
    </jsp:include>
    <div class="container">
        <div class="info">
            <h2><fmt:message bundle="${con}" key="first_text_strong"/></h2>
            <p><fmt:message bundle="${con}" key="first_text"/></p>
        </div>
        <div class="contacts__block">
            <div class="contacts__item">
                <div class="item">
                    <div class="item__title"><fmt:message bundle="${con}" key="inst"/></div>
                    <a class="item__value" href="https://www.instagram.com/">La Beauté</a>
                </div>
                <div class="item">
                    <div class="item__title"><fmt:message bundle="${con}" key="email"/></div>
                    <a href="mailto: labeauté@gmail.com" class="item__value">labeauté@gmail.com</a>
                </div>
                <div class="item">
                    <div class="item__title"><fmt:message bundle="${con}" key="third_text"/></div>
                    <a href="tel: (050)812-91-63" class="item__value"> (050)812-91-63</a>
                    <a href="tel: (093)813-92-64" class="item__value"> (093)813-92-64</a>
                </div>
                <div class="item">
                    <div class="item__title"><fmt:message bundle="${con}" key="fourth_text"/></div>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2564.4059987390892!2d36.24625341605192!3d50.003745079416234!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4127a0c3257691b5%3A0x881bf9cbde032f2c!2z0J_Rg9GI0LrQuNC90YHQutCw0Y8!5e0!3m2!1sru!2sua!4v1632320633038!5m2!1sru!2sua"
                            width="480" height="470" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                </div>
                <div class="item">
                    <div class="item__title"><fmt:message bundle="${con}" key="fifth_text"/></div>
                    <p class="item__value"><fmt:message bundle="${con}" key="sixth_text"/></p>
                    <p class="item__value"><fmt:message bundle="${con}" key="seventh_text"/></p>
                </div>
            </div>
            <div class="contacts__item">
                <img src="images/salon2.jpg" alt="salon2">
            </div>
        </div>
    </div>
    <jsp:include page="/footer" />
</div>
</body>
</html>
