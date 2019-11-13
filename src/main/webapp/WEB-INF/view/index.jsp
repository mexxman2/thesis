<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Welcome</h1>
<h2>Recommended to you</h2>
<c:forEach var="item" items="${recommended}">
    <div class="row">
        <td><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></td>
    </div>
</c:forEach>
<h2>Top 10 most popular items</h2>
<c:forEach var="item" items="${topTenItems}">
    <div class="row">
        <td><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></td>
    </div>
</c:forEach>