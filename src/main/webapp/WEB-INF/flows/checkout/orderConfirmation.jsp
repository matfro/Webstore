<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div class="row">
        <form:form modelAttribute="order" class="form-horizontal">
            <input type="hidden" name="_flowExecutionKey"
                   value="${flowExecutionKey}"/>

            <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                <div class="text-center">
                    <h1><spring:message code="orderConfirmation.legend"/></h1>
                </div>
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <address>
                            <strong><spring:message code="orderConfirmation.shippingAddress"/></strong> <br>
                                ${order.shippingDetail.firstName} ${order.shippingDetail.lastName}
                            <br>
                                ${order.shippingDetail.shippingAddress.streetName} ${order.shippingDetail.shippingAddress.doorNo}
                            <br>
                                ${order.shippingDetail.shippingAddress.zipCode} ${order.shippingDetail.shippingAddress.areaName}
                            <br>
                                ${order.shippingDetail.shippingAddress.state}, ${order.shippingDetail.shippingAddress.country}
                            <br>
                        </address>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                        <p>
                            <em><spring:message code="order.orderPlaced"/>: <fmt:formatDate type="date"
                                                                          value="${order.shippingDetail.datePlaced}"
                                                                          pattern="dd/MM/yyyy"/></em>
                            <br>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <address>
                            <strong><spring:message code="order.purchaserDetails"/></strong> <br>
                                ${order.customer.firstName} ${order.customer.lastName}
                            <br>
                                ${order.customer.billingAddress.streetName} ${order.customer.billingAddress.doorNo}
                            <br>
                                ${order.customer.billingAddress.zipCode} ${order.customer.billingAddress.areaName}
                            <br>
                                ${order.customer.billingAddress.state}, ${order.customer.billingAddress.country}
                            <br>Telefon: ${order.customer.phoneNumber}
                        </address>
                    </div>

                </div>
                <div class="row">

                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th><spring:message code="order.product"/></th>
                            <th>#</th>
                            <th class="text-center"><spring:message code="order.price"/></th>
                            <th class="text-center"><spring:message code="order.total"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cartItem" items="${order.cart.cartItems}">
                            <tr>
                                <td class="col-md-9"><em>${cartItem.product.name}</em></td>
                                <td class="col-md-1" style="text-align: center">
                                        ${cartItem.quantity}</td>
                                <td class="col-md-1 text-center">${cartItem.product.unitPrice} <spring:message
                                        code="Product.currency.label"/></td>
                                <td class="col-md-1 text-center">${cartItem.totalPrice} <spring:message
                                        code="Product.currency.label"/></td>
                            </tr>
                        </c:forEach>

                        <tr>
                            <td></td>
                            <td></td>
                            <td class="text-right"><h4>
                                <strong><spring:message code="order.grandTotal"/>:</strong>
                            </h4></td>
                            <td class="text-center text-danger"><h4>
                                <strong>${order.cart.grandTotal} <spring:message
                                        code="Product.currency.label"/></strong>
                            </h4></td>
                        </tr>
                        </tbody>
                    </table>
                    <button id="back" class="btn btn-default"
                            name="_eventId_backToCollectShippingDetail"><spring:message code="order.back"/>
                    </button>

                    <button type="submit" class="btn btn-success"
                            name="_eventId_orderConfirmed">
                        <spring:message code="orderConfirmation.confirm"/><span class="glyphicon glyphicon-chevron-right"></span>
                    </button>
                    <button id="btnCancel" class="btn btn-default"
                            name="_eventId_cancel"><spring:message code="orderConfirmation.cancel"/>
                    </button>
                </div>
            </div>
        </form:form>
    </div>
</div>
