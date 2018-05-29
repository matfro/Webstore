<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sping" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section class="container">

    <form:form modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <fieldset>
            <legend><spring:message
                    code="addProduct.form.legend"/></legend>

            <div class="form-group">
                <label class="control-label col-lg-2" for="name"><spring:message
                        code="addProduct.form.name.label"/></label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" type="text" class="form:input-large"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitPrice"><spring:message
                        code="addProduct.form.unitPrice.label"/></label>
                <div class="col-lg-10">
                    <div class="form:input-prepend">
                        <form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
                        <form:errors path="unitPrice" cssClass="text-danger"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="description"><spring:message
                        code="addProduct.form.description.label"/></label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="description" rows="2"/>
                    <form:errors path="description" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="manufacturer"><spring:message
                        code="addProduct.form.manufacturer.label"/></label>
                <div class="col-lg-10">
                    <form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
                    <form:errors path="manufacturer" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="category"><spring:message
                        code="addProduct.form.category.label"/></label>
                <div class="col-lg-10">
                    <form:input id="category" path="category" type="text" class="form:input-large"/>
                    <form:errors path="category" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock"><spring:message
                        code="addProduct.form.unitsInStock.label"/></label>
                <div class="col-lg-10">
                    <form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
                    <form:errors path="unitsInStock" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="conditionn"><spring:message
                        code="addProduct.form.condition.label"/></label>
                <div class="col-lg-10">
                    <form:radiobutton path="conditionn" value="Nowy"/><spring:message
                        code="addProduct.form.condition.new"/>
                    <form:radiobutton path="conditionn" value="Używany"/><spring:message
                        code="addProduct.form.condition.used"/>
                    <form:radiobutton path="conditionn" value="Odnowiony"/><spring:message
                        code="addProduct.form.condition.refurbished"/>
                    <form:errors path="conditionn" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="productImage">
                    <spring:message code="addProduct.form.productImage.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="productImage" path="productImage" type="file" class="form:input-large"/>
                    <form:errors path="productImage" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="productManual">
                    <spring:message code="addProduct.form.productManual.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="productManual" path="productManual" type="file" class="form:input-large"/>
                    <form:errors path="productManual" cssClass="text-danger"/>
                </div>
            </div>


            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value=<spring:message
                            code="addProduct.form.submit"/>/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </div>

        </fieldset>
    </form:form>
</section>
