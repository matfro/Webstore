<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="container">
    <form:form modelAttribute="order.shippingDetail" class="form-horizontal">
        <fieldset>
            <legend><spring:message code="collectShippingDetail.legend"/></legend>
            <div class="form-group">
                <label class="control-label col-lg-2" for="firstName"><spring:message code="collectCustomerInfo.firstName"/></label>
                <div class="col-lg-10">
                    <form:input id="firstName" path="firstName" type="text" class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="lastName"><spring:message code="collectCustomerInfo.lastName"/></label>
                <div class="col-lg-10">
                    <form:input id="lastName" path="lastName" type="text" class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="streetName"><spring:message code="collectCustomerInfo.streetName"/></label>
                <div class="col-lg-10">
                    <form:input id="streetName" path="shippingAddress.streetName" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="doorNo"><spring:message code="collectCustomerInfo.doorNo"/></label>
                <div class="col-lg-10">
                    <form:input id="doorNo" path="shippingAddress.doorNo" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="zipCode"><spring:message code="collectCustomerInfo.zipCode"/></label>
                <div class="col-lg-10">
                    <form:input id="zipCode" path="shippingAddress.zipCode" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="areaName"><spring:message code="collectCustomerInfo.areaName"/></label>
                <div class="col-lg-10">
                    <form:input id="areaName" path="shippingAddress.areaName" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="state"><spring:message code="collectCustomerInfo.state"/></label>
                <div class="col-lg-10">
                    <form:input id="state" path="shippingAddress.state" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="country"><spring:message code="collectCustomerInfo.country"/></label>
                <div class="col-lg-10">
                    <form:input id="country" path="shippingAddress.country" type="text" class="form:input-large"/>
                </div>
            </div>
            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button id="back" class="btn btn-default" name="_eventId_backToCollectCustomerInfo"><spring:message code="collectCustomerInfo.back"/></button>
                    <input type="submit" id="btnAdd" class="btn btnprimary" value=<spring:message code="collectShippingDetail.confirm"/>
                           name="_eventId_shippingDetailCollected"/>
                    <button id="btnCancel" class="btn btn-default" name="_eventId_cancel"><spring:message code="collectCustomerInfo.cancel"/></button>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>