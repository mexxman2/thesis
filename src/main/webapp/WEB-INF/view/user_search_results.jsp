<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Search results</h2>
<div class="row">
    <c:choose>
        <c:when test="${empty userPage.content}">
            <p>No users found</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="user" items="${userPage.content}">
                <p><a href="<c:url value='/watch_list?userId=${user.id}&page=1' />">${user.userName}</a></p>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<div class="row">
    <>
</div>