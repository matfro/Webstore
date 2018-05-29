<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <ul class="nav nav-pills nav-justified">
        <li><a href="/webstore/settings"><spring:message code="settings.navigation.myDetails"/> </a></li>
        <li><a href="/webstore/settings/userDetailsEdit"><spring:message code="settings.navigation.myDetailsEdit"/></a></li>
        <li><a href="/webstore/settings/passwordChange"><spring:message code="settings.navigation.passwordChange"/></a></li>
        <li class="active"><a href="#"><spring:message code="settings.navigation.emailChange"/></a></li>
    </ul>

    <div class="row">
        <p>
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="emailChange.legend"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="tempEmail" class="form-horizontal" enctype="form-data">
                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                        <fieldset>
                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="user.form.email.label" var="newEmailLabel"/>
                                <form:input id="email" path="email" type="text" class="form-control"
                                            placeholder="${newEmailLabel}"/>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="<spring:message code="changeEmail.confirm"/>">
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>
        </p>
    </div>