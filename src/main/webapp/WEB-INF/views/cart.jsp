<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
    <script src="<c:url value="/resource/js/controllers.js"/>"></script>
</head>

<section class="container" ng-app="cartApp">
    <div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
        <div>
            <a class="btn btn-danger pull-left"
               ng-click="clearCart()"> <span
                    class="glyphicon glyphicon-remove-sign"></span> <spring:message code="cart.clearCart"/>
            </a> <a href="<spring:url value="/webstore/checkout?cartId=${cartId}"/>" class="btn btn-success pull-right"> <span
                class="glyphicon-shopping-cart glyphicon"></span> <spring:message code="cart.buy"/>
        </a>
        </div>
        <table class="table table-hover">
            <tr>
                <th><spring:message code="cart.product"/></th>
                <th><spring:message code="cart.unitPrice"/></th>
                <th><spring:message code="cart.quantity"/></th>
                <th><spring:message code="cart.price"/></th>
                <th><spring:message code="cart.action"/></th>

            </tr>
            <tr ng-repeat="item in cart.cartItems">
                <td>P{{item.product.productId}}-{{item.product.name}}</td>
                <td>{{item.product.unitPrice}} <spring:message code="cart.currency"/></td>
                <td>{{item.quantity}}</td>
                <td>{{item.totalPrice}} <spring:message code="cart.currency"/></td>
                <td><a href="#" class="label label-danger" ng-click="removeFromCart(item.product.productId)">
                    <span class="glyphicon glyphicon-remove"/></span> <spring:message code="cart.deleteCartItem"/>
                </a></td>
            </tr>
            <tr>
                <th></th>
                <th></th>
                <th><spring:message code="cart.grandTotal"/></th>
                <th>{{cart.grandTotal}} <spring:message code="cart.currency"/></th>
                <th></th>
            </tr>
        </table>

        <a href="<spring:url value="/webstore/products" />" class="btn btn-default">
            <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="cart.back"/>
        </a>
    </div>
</section>
