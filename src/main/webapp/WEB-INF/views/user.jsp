<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="row">
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="text-center">
                <h1>${user.username}</h1>
            </div>
            <div class="pull-left">
                <br>
                ${user.customer.firstName} ${user.customer.lastName} <br>
                <spring:message code="settings.emailAddress"/>: ${user.email} <br>
                <spring:message code="settings.phoneNumber"/>: ${user.customer.phoneNumber}<br>
                <p>
                    <a href="<spring:url value="/webstore/users/orders/user?id=${user.username}" />"
                       class="btn btn-default">
                        <span class="glyphicon-hand-right glyphicon"></span> <spring:message code="user.noOfOrdersMade"/>: ${user.customer.noOfOrdersMade}
                    </a>
                </p>
            </div>
            <div class="pull-right">
                <div>
                    <address>
                        <strong><spring:message code="settings.address"/></strong> <br>
                        ${user.customer.billingAddress.streetName} ${user.customer.billingAddress.doorNo}
                        <br>
                        ${user.customer.billingAddress.zipCode} ${user.customer.billingAddress.areaName}
                        <br>
                        ${user.customer.billingAddress.state}, ${user.customer.billingAddress.country}
                        <br>
                    </address>
                </div>
            </div>
        </div>
    </div>
    <p>
        <a href="<spring:url value="/webstore/users" />" class="btn btn-default">
            <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="user.back"/>
        </a>
    </p>
</div>
