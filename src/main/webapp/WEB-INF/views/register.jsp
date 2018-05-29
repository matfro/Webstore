<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="register.legend"/></h3>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="newUser" class="form-horizontal" enctype="form-data">
                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                        <fieldset>
                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="newUser.form.username.label" var="userLabel"/>
                                <form:input id="username" path="username" type="text" class="form-control"
                                            placeholder="${userLabel}"/>
                                <form:errors path="username" cssClass="text-danger"/>
                            </div>
                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="newUser.form.password.label" var="passwordLabel"/>
                                <form:input id="password" path="password" type="password" class="form-control"
                                            placeholder="${passwordLabel}"/>
                                <form:errors path="password" cssClass="text-danger"/>
                            </div>

                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="newUser.form.matchingPassword.label" var="matchingPasswordLabel"/>
                                <form:input id="matchingPassword" path="matchingPassword" type="password"
                                            class="form-control" placeholder="${matchingPasswordLabel}"/>
                            </div>
                            <div class="form-group col-md-12 col-md-offset-4">
                                <spring:message code="newUser.form.email.label" var="emailLabel"/>
                                <form:input id="email" path="email" type="text" class="form-control"
                                            placeholder="${emailLabel}"/>
                                <form:errors path="email" cssClass="text-danger"/>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="<spring:message code="register.confirm"/>">
                        </fieldset>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>