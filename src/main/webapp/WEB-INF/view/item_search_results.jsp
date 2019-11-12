<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Search results</h2>
<div class="row">
    <p><a href="<c:url value='/upload' />">Want to add an item?</a></p>
</div>
<div class="row">
    <c:choose>
        <c:when test="${empty items}">
            <p>No items found</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="item" items="${items}">
                <p><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></p>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<div class="row">
    <a href="<c:url value='/itemSearch/${substring}?page=${previous}' />">Previous</a>
    <c:if test="${previous lt current}">
        <a href="<c:url value='/itemSearch/${substring}?page=${previous}' />">${previous}</a>
    </c:if>
    ${current}
    <c:if test="${next le totalPages}">
        <a href="<c:url value='/itemSearch/${substring}?page=${next}' />">${next}</a>
    </c:if>
    <c:if test="${next+1 le totalPages}">
        <a href="<c:url value='/itemSearch/${substring}?page=${next+1}' />">${next+1}</a>
    </c:if>
    <c:if test="${next+2 le totalPages}">
        <a href="<c:url value='/itemSearch/${substring}?page=${next+2}' />">${next+2}</a>
    </c:if>
    <a href="<c:url value='/itemSearch/${substring}?page=${next}' />">Next</a>
</div>