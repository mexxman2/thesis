<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Add a new movie or series</h2>

<form:form modelAttribute="uploadItem" action="uploadItemPost">
    <form:errors path="*" element="div" cssClass="validation-error" />
    <p>
        <label for="input_title">Title</label>
        <form:input path="title" id="input_title" />
    </p>
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