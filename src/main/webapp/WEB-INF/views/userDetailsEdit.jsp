<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <ul class="nav nav-pills nav-justified">
        <li><a href="/webstore/settings"><spring:message code="settings.navigation.myDetails"/></a></li>
        <li class="active"><a href="#"><spring:message code="settings.navigation.myDetailsEdit"/></a></li>
        <li><a href="/webstore/settings/passwordChange"><spring:message code="settings.navigation.passwordChange"/></a></li>
        <li><a href="/webstore/settings/emailChange"><spring:message code="settings.navigation.emailChange"/></a></li>
    </ul>

    <div class="row">
        <p>
            <form:form modelAttribute="user" class="form-horizontal" enctype="form-data">
                <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <fieldset>
            <legend><spring:message code="userDetailsEdit.legend"/> </legend>

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
                    <form:input id="doorNo" path="customer.billingAddress.doorNo" type="text" class="form:input-large"/>
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
                    <form:input id="state" path="customer.billingAddress.state" type="text" class="form:input-large"/>
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
                    <input type="submit" class="btn btn-primary" value="<spring:message code="userDetails.confirm"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </div>
        </fieldset>
        </form:form>
        </p>
    </div>
</div>