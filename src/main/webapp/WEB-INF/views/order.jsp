<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div class="row">
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="text-center">
                <h1><spring:message code="order.orderId"/>: ${order.orderId}</h1>
            </div>

            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>
                        <strong><spring:message code="order.shippingAddress"/> </strong> <br>
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
                        <br><spring:message code="order.phoneNumber"/>: ${order.customer.phoneNumber}
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
                    <c:forEach var="cartItem" items="${order.cartItemsList}">
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
                            <strong>${order.grandTotal} <spring:message code="Product.currency.label"/></strong>
                        </h4></td>
                    </tr>
                    </tbody>
                </table>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
                    <p>
                        <a href="<spring:url value="/webstore/users/orders/user?id=${username}" />"
                           class="btn btn-default">
                            <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="order.back"/>
                        </a>
                    </p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>
                        <a href="<spring:url value="/webstore/myOrders" />"
                           class="btn btn-default">
                            <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="order.back"/>
                        </a>
                    </p>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
