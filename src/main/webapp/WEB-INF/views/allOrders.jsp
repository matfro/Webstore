<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="container">
    <div class="row">
        <c:forEach items="${orders}" var="order">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <div class="caption">
                        <h3><spring:message code="allOrders.orderId"/>: ${order.value.orderId}</h3>
                        <p><spring:message code="allOrders.user"/>: ${order.key}</p>
                        <p>${order.value.customer.firstName} ${order.value.customer.lastName}</p>
                        <p><spring:message code="allOrders.grandTotal"/>: ${order.value.grandTotal}</p>
                        <p>
                            <a
                                    href=" <spring:url value="/webstore/orders/order?id=${order.value.orderId}" /> "
                                    class="btn btn-primary"> <span
                                    class="glyphicon-info-sign glyphicon"/></span> <spring:message code="allOrders.details"/>
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
