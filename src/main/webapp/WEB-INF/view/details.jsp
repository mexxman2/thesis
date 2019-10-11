<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>${item.title}</h2>
<p>${item.description}</p>
<c:choose>
    <c:when test="${isMovie}">
        <p>Type: Movie</p>
    </c:when>
    <c:otherwise>
        <p>Type: Series</p>
        <p>Number of seasons: ${item.numberOfSeasons}</p>
        <p>Number of episodes: ${item.numberOfEpisodes}</p>
    </c:otherwise>
</c:choose>

<form:form modelAttribute="addToWatchListItem" action="addToWatchList" method="post">
    <form:hidden path="itemId" value="${item.id}" />
    <p>
        <label for="rating">Rate it</label>
        <form:select path="rating" id="rating">
            <form:option value="1" label="1"/>
            <form:option value="2" label="2"/>
            <form:option value="3" label="3"/>
            <form:option value="4" label="4"/>
            <form:option value="5" label="5"/>
            <form:option value="6" label="6"/>
            <form:option value="7" label="7"/>
            <form:option value="8" label="8"/>
            <form:option value="9" label="9"/>
            <form:option value="10" label="10"/>
        </form:select>
    </p>
    <p>
        <button type=submit>Add to list</button>
    </p>
</form:form>