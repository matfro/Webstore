<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="container">
    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
    <div class="row">
        <p>
            <a href="<spring:url value="/webstore/users/user?id=${username}" />"
               class="btn btn-default">
                <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="order.back"/>
            </a>
        </p>
        <div class="row">
            </sec:authorize>
            <div class="row">
                <c:forEach items="${orders}" var="order">
                    <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3><spring:message code="order.orderId"/>: ${order.orderId}</h3>
                                <p>${order.customer.firstName} ${order.customer.lastName}</p>
                                <p><spring:message code="order.grandTotal"/>: ${order.grandTotal}</p>
                                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
                                    <p>
                                        <a
                                                href=" <spring:url value="/webstore/users/orders/userId=${username};orderId=${order.orderId}" /> "
                                                class="btn btn-primary"> <span
                                                class="glyphicon-info-sign glyphicon"/></span> <spring:message code="order.orderDetails"/>
                                        </a>
                                    </p>
                                </sec:authorize>
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <p>
                                        <a
                                                href=" <spring:url value="/webstore/myOrders/order?id=${order.orderId}" /> "
                                                class="btn btn-primary"> <span
                                                class="glyphicon-info-sign glyphicon"/></span> <spring:message code="order.orderDetails"/>
                                        </a>
                                    </p>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
</section>
