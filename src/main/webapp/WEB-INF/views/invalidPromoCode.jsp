<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section>
    <div class="container">
        <p>
            <a href="<spring:url value="/webstore/products" />" class="btn btn-primary">
                <span class="glyphicon-hand-left glyphicon"></span>
                <spring:message code="invalidPromoCode.back"/>
            </a>
        </p>
    </div>
</section>