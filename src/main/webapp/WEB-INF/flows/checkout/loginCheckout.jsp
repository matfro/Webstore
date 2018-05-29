<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<section>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Zaloguj się</h3>
                </div>

                <div class="panel-body">
                    <form action="<c:url value="/login"/>" method="post">
                        <fieldset>
                            <div class="form-group">
                                <spring:message code="login.form.username.label" var="usernameLabel"/>
                                <input class="form-control" placeholder=${usernameLabel}name='username' type="text">
                            </div>
                            <div class="form-group">
                                <spring:message code="login.form.password.label" var="passwordLabel"/>
                                <input class="form-control" placeholder=${passwordLabel} name='password' type="password"
                                       value="">
                            </div>
                            <button name="_eventId_logged" class="btn btn-lg btn-success btn-block" type="submit"
                                    value=<spring:message code="login.form.signIn"/> />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                        </fieldset>
                    </form>

                    <form>
                        <div class="form-group">
                            <div class="col-lg-offset-2 col-lg-10">
                                <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
                                <input type="submit" id="btnAdd" class="btn btn-primary"
                                       value=<spring:message code="anonymousCustomer.signIn"/> name="_eventId_withoutLogging"/>
                                <button id="btnCancel" class="btn btn-default" name="_eventId_cancel"><spring:message code="loginCheckout.cancel"/> </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>