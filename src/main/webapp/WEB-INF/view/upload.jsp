<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h2>Add a new movie or series from imdb</h2>

<form:form modelAttribute="uploadItem" action="uploadItemPost">
    <spring:bind path="titleOrURL">
    <form:errors path="*" element="div" cssClass="validation-error" />
    <p>
        <label for="input_title_or_url">Imdb title or URL</label>
        <form:input path="titleOrURL" id="input_title_or_url" />
    </p>
    <c:if test="${status.error}">
        <p>
            <a href="<c:url value='/confirmUpload' />">Confirm</a>
        </p>
    </c:if>
    <p>
        <label for="movie">Movie</label>
        <form:radiobutton path="type" value="movie" id="movie" checked="checked"/>
    </p>
    <p>
        <label for="series">Series</label>
        <form:radiobutton path="type" value="series" id="series"/>
    </p>
    <p>
        <button type="submit">Add</button>
    </p>
</form:form>