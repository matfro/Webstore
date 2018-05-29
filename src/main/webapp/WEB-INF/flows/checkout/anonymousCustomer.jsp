<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="anonymousCustomer.signIn"/></h3>
                </div>
                <div class="panel-body">
                    <form:form class="form-horizontal">
                        <input type="hidden" name="_flowExecutionKey">
                        <button type="submit" class="btn btn-success"
                                name="_eventId_login">
                            <spring:message code="anonymousCustomer.signIn"/><span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                        <button type="submit" class="btn btn-success"
                                name="_eventId_withoutLogging">
                            <spring:message code="anonymousCustomer.withoutLogin"/><span class="glyphicon glyphicon-chevron-right"></span>
                        </button>
                        <br>
                        <button id="btnCancel" class="btn btn-default"
                                name="_eventId_cancel"><spring:message code="anonymousCustomer.cancel"/>
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>