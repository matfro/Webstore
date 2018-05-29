<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section>
    <div class="container">
        <p>
        <h1 class="alert alert-danger"> <spring:message code="thankCustomer.thankYou"/>!</h1>
        <p><spring:message code="thankCustomer.thankForOrder"/>!</p>
        <p><spring:message code="thankCustomer.orderId"/> ${order.orderId}.</p>
        <a href="<spring:url value="/webstore/products" />" class="btn btn-primary">
            <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="thankCustomer.products"/>
        </a>
        </p>
    </div>
</section>