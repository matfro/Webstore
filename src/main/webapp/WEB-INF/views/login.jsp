<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="login.form.legend"/> </h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            <spring:message code="AbstractUserDetailsAuthenticationProvider.badCredentials"/><br/>
                        </div>
                    </c:if>
                    <form action="<c:url value="/login"/>" method="post">
                        <fieldset>
                            <div class="form-group">
                                <spring:message code="login.form.username.label" var="usernameLabel"/>
                                <input class="form-control" placeholder="${usernameLabel}" name='username' type="text">
                            </div>
                            <div class="form-group">
                                <spring:message code="login.form.password.label" var="passwordLabel"/>
                                <input class="form-control" placeholder="${passwordLabel}" name='password' type="password"
                                       value="">
                            </div>
                            <input class="btn btn-lg btn-success btn-block" type="submit" value="<spring:message code="login.form.signIn"/>" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </fieldset>
                    </form>
                   <spring:message code="login.form.notUser"/> <a href="<spring:url value="/webstore/users/register"/>"> <spring:message code="login.form.register"/> </a>
                </div>
            </div>
        </div>
    </div>
</section>