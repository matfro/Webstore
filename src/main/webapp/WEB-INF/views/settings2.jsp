<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <ul class="nav nav-pills">
        <%--<li class="active"><a data-toggle="pill" href="#home">Home</a></li>--%>
        <li class="active"><a data-toggle="pill" href="#menu1">Moje dane</a></li>
        <li><a data-toggle="pill" href="#menu2">Edycja danych</a></li>
        <li><a data-toggle="pill" href="#menu3">Zmiana hasła</a></li>
    </ul>

    <div class="tab-content">
        <%--<div id="home" class="tab-pane fade in active">--%>
        <%--<h3>HOME</h3>--%>
        <%--<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>--%>
        <%--</div>--%>
        <div id="menu1" class="tab-pane fade">
            <div class="row">
                <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                    <div class="text-center">
                        <h1>Moje dane</h1>
                    </div>
                    <div class="pull-left">
                        <br>
                        ${user.customer.firstName} ${user.customer.lastName} <br>
                        Adres email: ${user.email} <br>
                        Nr telefonu: ${user.customer.phoneNumber}<br>
                    </div>
                    <div class="pull-right">
                        <div>
                            <address>
                                <strong>Adres</strong> <br>
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
        </div>

        <div id="menu2" class="tab-pane fade">
            <form:form modelAttribute="user" class="form-horizontal" action="userDetails" enctype="form-data">
                <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                <fieldset>
                    <legend>Edytuj dane</legend>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="firstName"><spring:message
                                code="userdetails.form.firstName.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="firstName" path="customer.firstName" type="text" class="form:input-large"/>
                            <form:errors path="customer.firstName" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="lastName"><spring:message
                                code="userdetails.form.lastName.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="lastName" path="customer.lastName" type="text" class="form:input-large"/>
                            <form:errors path="customer.lastName" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="phoneNumber"><spring:message
                                code="userdetails.form.phoneNumber.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="phoneNumber" path="customer.phoneNumber"/>
                            <form:errors path="customer.phoneNumber" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="streetName"><spring:message
                                code="userdetails.form.streetName.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="streetName" path="customer.billingAddress.streetName" type="text"
                                        class="form:input-large"/>
                            <form:errors path="customer.billingAddress.streetName" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="doorNo"><spring:message
                                code="userdetails.form.doorNo.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="doorNo" path="customer.billingAddress.doorNo" type="text"
                                        class="form:input-large"/>
                            <form:errors path="customer.billingAddress.doorNo" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="zipCode"><spring:message
                                code="userdetails.form.zipCode.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="zipCode" path="customer.billingAddress.zipCode" type="text"
                                        class="form:input-large"/>
                            <form:errors path="customer.billingAddress.zipCode" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="areaName"><spring:message
                                code="userdetails.form.areaName.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="areaName" path="customer.billingAddress.areaName" type="text"
                                        class="form:input-large"/>
                            <form:errors path="customer.billingAddress.areaName" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="state"><spring:message
                                code="userdetails.form.state.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="state" path="customer.billingAddress.state" type="text"
                                        class="form:input-large"/>
                            <form:errors path="customer.billingAddress.state" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-lg-2" for="country"><spring:message
                                code="userdetails.form.country.label"/></label>
                        <div class="col-lg-10">
                            <form:input id="country" path="customer.billingAddress.country" type="text"
                                        class="form:input-large"/>
                            <form:errors path="customer.billingAddress.country" cssClass="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-offset-2 col-lg-10">
                            <input type="submit" id="btnAdd" class="btn btn-primary" value="Zatwierdź"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>

        <div id="menu3" class="tab-pane fade">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Zmiana hasła</h3>
                        </div>
                        <div class="panel-body">
                            <form:form modelAttribute="user" class="form-horizontal" enctype="form-data"
                                       action="password">
                                <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                                <fieldset>
                                    <div class="form-group col-md-12 col-md-offset-4">
                                        <spring:message code="newUser.form.password.label" var="passwordLabel"/>
                                        <form:input id="password" path="password" type="password" class="form-control"
                                                    placeholder="${passwordLabel}"/>
                                        <form:errors path="password" cssClass="text-danger"/>
                                    </div>

                                    <div class="form-group col-md-12 col-md-offset-4">
                                        <spring:message code="newUser.form.matchingPassword.label"
                                                        var="matchingPasswordLabel"/>
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
                                    <input class="btn btn-lg btn-success btn-block" type="submit" value="Zatwierdź">
                                </fieldset>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

            <div id="menu4" class="tab-pane fade">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Zmiana adresu email</h3>
                            </div>
                            <div class="panel-body">
                                <form:form modelAttribute="user" class="form-horizontal" enctype="form-data">
                                    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                                    <fieldset>
                                        <div class="form-group col-md-12 col-md-offset-4">
                                            <spring:message code="newUser.form.email.label" var="emailLabel"/>
                                            <form:input id="email" path="email" type="text" class="form-control"
                                                        placeholder="${emailLabel}"/>
                                            <form:errors path="email" cssClass="text-danger"/>
                                        </div>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Zatwierdź">
                                    </fieldset>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

