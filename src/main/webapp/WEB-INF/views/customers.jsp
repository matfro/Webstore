<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="container">
    <div class="row">
        <c:forEach items="${customers}" var="customers">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <div class="caption">
                        <h3>${customers.customerId}</h3>
                        <p>${customers.firstName}</p>
                        <p>${customers.lastName}</p>
                        <p>${customers.billingAddress}</p>
                        <p><spring:message code="customers.noOfOrdersMade"/>: ${customers.noOfOrdersMade}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
