<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <ul class="nav nav-pills nav-justified">
        <li class="active"><a href="#"><spring:message code="settings.navigation.myDetails"/></a></li>
        <li><a href="/webstore/settings/userDetailsEdit"><spring:message code="settings.navigation.myDetailsEdit"/></a></li>
        <li><a href="/webstore/settings/passwordChange"><spring:message code="settings.navigation.passwordChange"/></a></li>
        <li><a href="/webstore/settings/emailChange"><spring:message code="settings.navigation.emailChange"/></a></li>
    </ul>

    <div class="row">
        <p>
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="text-center">
                <h1><spring:message code="settings.myDetails"/></h1>
            </div>
            <div class="pull-left">
                <br>
                ${user.customer.firstName} ${user.customer.lastName} <br>
                <spring:message code="settings.emailAddress"/>: ${user.email} <br>
                <spring:message code="settings.phoneNumber"/>: ${user.customer.phoneNumber}<br>
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
        </p>
    </div>
</div>