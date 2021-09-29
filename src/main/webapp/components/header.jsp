<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page isELIgnored="false" %>

<fmt:setLocale value="${current_lang}"/>
<fmt:setBundle basename="header" var="head"/>
<div class="header__inner">
    <div class="logo">
        La Beaute
    </div>
    <div class="navbar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/main" class="a_parent ${home}">
                <div class="wrap">
					<span class="icon">
						<i class="fas fa-home" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="home"/></span>
                </div>
            </a></li>
            <li><a href="${pageContext.request.contextPath}/about-us" class="a_parent ${about}">
                <div class="wrap">
					<span class="icon">
						 <i class="fas fa-info-circle" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="about_us"/></span>
                </div>
            </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/price" class="a_parent ${price}">
                <div class="wrap">
					<span class="icon">
					<i class="fas fa-donate" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="price"/></span>
                </div>
            </a>
            </li>
            <li><a href="${pageContext.request.contextPath}/contacts" class="a_parent ${contacts}">
                <div class="wrap">
					<span class="icon">
					 <i class="fas fa-address-book" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="contacts"/></span>
                </div>
            </a></li>
<%--            <li><a href="${pageContext.request.contextPath}/reviews" class="a_parent ${reviews}">--%>
<%--                <div class="wrap">--%>
<%--					<span class="icon">--%>
<%--					 <i class="fas fa-comments" aria-hidden="true"></i>--%>
<%--					</span>--%>
<%--                    <span class="text"><fmt:message bundle="${head}" key="reviews"/></span>--%>
<%--                </div>--%>
<%--            </a></li>--%>
        </ul>
    </div>
    <div class="language_switch">
        <div class="language__current">
            ${current_lang}
            <i class="fas fa-arrow-down"></i>
        </div>
        <ul class="language__switcher">
            <li class="language__switch__item"><a href="${pageContext.request.contextPath}/language?page=${current_page}">${second_lang}</a>
            </li>
        </ul>
    </div>
    <div class="burger-menu">
        <input type="checkbox" id="check_menu">
        <label class="lab" for="check_menu"></label>
        <div class="burger-line first"></div>
        <div class="burger-line second"></div>
        <div class="burger-line third"></div>
        <div class="burger-line fourth"></div>
        <nav class="main-menu">
            <c:forEach items="${menu_items}" var="item">
                <a href="${item.key}">${item.value}</a>
            </c:forEach>
        </nav>
    </div>
</div>