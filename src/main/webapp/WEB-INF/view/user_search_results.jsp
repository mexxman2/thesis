<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <div class="row">
        <h2>Search results</h2>
    </div>
    <c:choose>
        <c:when test="${empty users}">
            <div class="row">
                <p>No users found</p>
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="user" items="${users}">
                <div class="row">
                    <a href="<c:url value='/watch_list?userId=${user.id}' />">${user.userName}</a>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <div class="row">
        <a href="<c:url value='/searchUser?substring=${substring}&page=${previous}' />">Previous</a>
        <c:if test="${previous-1 ge 0}">
                <a href="<c:url value='/searchUser?substring=${substring}&page=${previous-1}' />">${previous-1}</a>
            </c:if>
        <c:if test="${previous lt current}">
            <a href="<c:url value='/searchUser?substring=${substring}&page=${previous}' />">${previous}</a>
        </c:if>
        ${current}
        <c:if test="${next lt totalPages}">
            <a href="<c:url value='/searchUser?substring=${substring}&page=${next}' />">${next}</a>
        </c:if>
        <c:if test="${next+1 lt totalPages}">
            <a href="<c:url value='/searchUser?substring=${substring}&page=${next+1}' />">${next+1}</a>
        </c:if>
        <c:if test="${next+2 lt totalPages}">
            <a href="<c:url value='/searchUser?substring=${substring}&page=${next+2}' />">${next+2}</a>
        </c:if>
        <a href="<c:url value='/searchUser?substring=${substring}&page=${next lt totalPages ? next : current}' />">Next</a>
    </div>
</div>