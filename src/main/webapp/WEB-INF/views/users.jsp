<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="container">
    <div class="row">
        <c:forEach items="${users}" var="user">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <div class="caption">
                        <h3>${user.username}</h3>
                        <p><spring:message code="users.emailAddress"/>: ${user.email}</p>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:forEach items="${user.authorities}" var="authority">
                                <p><spring:message code="users.authorities"/>: ${authority.authority}</p>
                            </c:forEach>
                        </sec:authorize>
                        <p>
                            <a
                                    href=" <spring:url value="/webstore/users/user?id=${user.username}" /> "
                                    class="btn btn-primary"> <span
                                    class="glyphicon-info-sign glyphicon"/></span> <spring:message code="users.details"/>
                            </a>
                        </p>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
