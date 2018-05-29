<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<li><a href="<spring:url value="/webstore/"/>"><spring:message code="pl.matfro.webstore.navigation.mainPage"/></a></li>
<li><a href="<spring:url value="/webstore/products/"/>"><spring:message code="pl.matfro.webstore.navigation.products"/></a></li>

<sec:authorize access="!hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
    <li><a href="<spring:url value="/webstore/cart/"/>"><spring:message code="pl.matfro.webstore.navigation.cart"/></a></li>
</sec:authorize>

<sec:authorize access="isAnonymous()">
    <li><a href="<spring:url value="/webstore/login/"/>"><spring:message code="pl.matfro.webstore.navigation.singIn"/></a></li>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_USER')">

    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><spring:message code="pl.matfro.webstore.navigation.welcome"/>, <sec:authentication
                property="principal.username"/>!
            <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                <li><a href="<spring:url value="/webstore/users/supervisors/"/>"><spring:message code="pl.matfro.webstore.navigation.supervisors"/></a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
                <li><a href="<spring:url value="/webstore/users/"/>"><spring:message code="pl.matfro.webstore.navigation.users"/></a></li>
                <li><a href="<spring:url value="/webstore/orders/"/>"><spring:message code="pl.matfro.webstore.navigation.orders"/></a></li>
                <li><a href="<spring:url value="/webstore/products/add/"/>"><spring:message code="pl.matfro.webstore.navigation.addProduct"/></a></li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('ROLE_USER')">
                <li><a href="<spring:url value="/webstore/myOrders/"/>"><spring:message code="pl.matfro.webstore.navigation.myOrders"/></a></li>
                <li><a href="<spring:url value="/webstore/settings/"/>"><spring:message code="pl.matfro.webstore.navigation.settings"/></a></li>
            </sec:authorize>
            <li>
                <a href="#" onclick="document.myForm.submit()"><spring:message code="pl.matfro.webstore.navigation.logout"/></a>
                <form name="myForm" action="/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </li>
        </ul>
    </li>
</sec:authorize>
