<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Search results</h2>
<h3>Items</h2>
<c:choose>
    <c:when test="${empty items}">
        <p>No items found</p>
        <a href="<c:url value='/upload' />">Want to add an item?</a>
    </c:when>
    <c:otherwise>
        <c:forEach var="item" items="${items}">
            <p><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></p>
        </c:forEach>
    </c:otherwise>
</c:choose>
<br>
<h3>Users</h3>
<c:choose>
    <c:when test="${empty users}">
        <p>No users found</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="user" items="${users}">
            <p><a href="<c:url value='/watch_list?userId=${user.id}&page=1' />">${user.userName}</a></p>
        </c:forEach>
    </c:otherwise>
</c:choose>