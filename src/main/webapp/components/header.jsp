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
            <li><a href="#" class="a_parent">
                <div class="wrap">
					<span class="icon">
					<i class="fas fa-donate" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="price"/></span>
                </div>
            </a>
            </li>
            <li><a href="#" class="a_parent">
                <div class="wrap">
					<span class="icon">
					<i class="fab fa-gg-circle" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="services"/></span>
                </div>
            </a>
                <div class="dd_menu">
                    <ul>
                        <li>
                            <a href="#" class="dd_menu_a">
                                <div class="wrap">
                                    <span class="text">Nail Service</span>
                                </div>
                            </a>
                            <div class="dd_submenu">
                                <ul>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Running</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Biking</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Football</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Swimmer</span>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a href="#" class="dd_menu_a">
                                <div class="wrap">
                                    <span class="text">Barbershop</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="dd_menu_a">
                                <div class="wrap">
                                    <span class="text">Make-up</span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#" class="dd_menu_a">
                                <div class="wrap">
                                    <span class="text">Depilation</span>
                                </div>
                            </a>
                            <div class="dd_submenu">
                                <ul>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Running</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Biking</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Football</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <div class="wrap">
                                                <span class="text">Swimmer</span>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </li>
            <li><a href="${pageContext.request.contextPath}/contacts" class="a_parent ${contacts}">
                <div class="wrap">
					<span class="icon">
					 <i class="fas fa-address-book" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="contacts"/></span>
                </div>
            </a></li>
            <li><a href="#" class="a_parent">
                <div class="wrap">
					<span class="icon">
					 <i class="fas fa-comments" aria-hidden="true"></i>
					</span>
                    <span class="text"><fmt:message bundle="${head}" key="reviews"/></span>
                </div>
            </a></li>
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
    <div class="user">
        <i class="fas fa-user"></i>
    </div>
</div>


<script src="scripts/header.js"></script>