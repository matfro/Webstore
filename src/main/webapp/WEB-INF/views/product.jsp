<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
<script src="<c:url value="/resource/js/controllers.js"/>"></script>
</head>

<section class="container" ng-app="cartApp">
    <div class="row">
        <div class="col-md-5">
            <img src="<c:url value="/resource/images/${product.productId}.png"></c:url>" alt="image"
                 style="width:100%"/>
        </div>

        <div class="col-md-5">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>
                <strong><spring:message code="Product.productId.label"/></strong>: <span
                    class="label label-warning">P${product.productId}</span>
            </p>
            <p>
                <strong><spring:message code="Product.manufacturer.label"/></strong>: ${product.manufacturer}
            </p>
            <p>
                <strong><spring:message code="Product.category.label"/></strong>: ${product.category}
            </p>
            <p>
                <strong><spring:message code="Product.conditionn.label"/></strong>: ${product.conditionn}
            </p>
            <p>
                <strong><spring:message code="Product.unitsInStock.label"/></strong>: ${product.unitsInStock}
            </p>
            <h4>${product.unitPrice} <spring:message code="Product.currency.label"/></strong></h4>
            <p ng-controller="cartCtrl">
                <a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')">
                    <span class="glyphicon-shopping-cart glyphicon"></span> <spring:message code="Product.orderNow"/> </a>
                <a href="<spring:url value="/webstore/cart" />" class="btn btn-default">
                    <span class="glyphicon-hand-right glyphicon"></span> <spring:message code="Product.cart"/>
                </a>

                <a href="<spring:url value="/webstore/products" />" class="btn btn-default">
                    <span class="glyphicon-hand-left glyphicon"></span> <spring:message code="Product.back"/>
                </a>

            </p>

        </div>
    </div>
</section>
