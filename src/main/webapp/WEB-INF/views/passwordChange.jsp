<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <ul class="nav nav-pills nav-justified">
        <li><a href="/webstore/settings"><spring:message code="settings.navigation.myDetails"/></a></li>
        <li><a href="/webstore/settings/userDetailsEdit"><spring:message code="settings.navigation.myDetailsEdit"/></a></li>
        <li class="active"><a href="#"><spring:message code="settings.navigation.passwordChange"/></a></li>
        <li><a href="/webstore/settings/emailChange"><spring:message code="settings.navigation.emailChange"/></a></li>
    </ul>

    <div class="row">
        <p>
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="passwordChange.legend"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="tempPass" class="form-horizontal" enctype="form-data">
                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                        <fieldset>

                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="user.form.oldPassword.label" var="passwordLabel"/>
                                <form:input id="oldPassword" path="oldPassword" type="password" class="form-control"
                                            placeholder="${passwordLabel}"/>
                                <form:errors path="oldPassword" cssClass="text-danger"/>
                            </div>


                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="user.form.password.label" var="passwordLabel"/>
                                <form:input id="password" path="password" type="password" class="form-control"
                                            placeholder="${passwordLabel}"/>
                                <form:errors path="password" cssClass="text-danger"/>
                            </div>

                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="user.form.matchingPassword.label" var="matchingPasswordLabel"/>
                                <form:input id="matchingPassword" path="matchingPassword" type="password"
                                            class="form-control" placeholder="${matchingPasswordLabel}"/>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="<spring:message code="passwordChange.confirm"/>">
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </p>
    </div>
</div>