<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h2>Add a new movie or series from imdb</h2>

<form:form modelAttribute="uploadItem" action="uploadItemPost">
    <spring:bind path="titleOrURL">
        <div class="form-group row">
            <div class="col-sm-2">
                <label for="input_title_or_url">Imdb title or URL</label>
            </div>
            <div class="col-sm-10">
                <form:input path="titleOrURL" id="input_title_or_url" class="form-control" />
            </div>
        </div>
        <div class="form-group row">
            <form:errors path="*" element="div" class="alert alert-danger" role="alert" />
        </div>
        <c:if test="${status.error}">
            <a class="btn btn-primary" href="<c:url value='/confirmUpload' />">Confirm</a>
        </c:if>
    </spring:bind>
    <div class="form-row">
        <label for="movie">Movie</label>
        <form:radiobutton path="type" value="movie" id="movie" checked="checked"/>
    </div>
    <div class="form-row">
        <label for="series">Series</label>
        <form:radiobutton path="type" value="series" id="series"/>
    </div>
    <div class="form-row">
        <button type="submit" class="btn btn-primary">Add</button>
    </div>
</form:form>