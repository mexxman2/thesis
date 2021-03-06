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
            <div class="row">
                <nav>
                    <ul class="pagination">
                        <li class="page-item ${previous == current ? 'disabled' : ''}"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${previous}' />">Previous</a></li>
                        <c:if test="${previous-1 ge 0}">
                                <li class="page-item"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${previous-1}' />">${previous-1}</a></li>
                            </c:if>
                        <c:if test="${previous lt current}">
                            <li class="page-item"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${previous}' />">${previous}</a></li>
                        </c:if>
                        <li class="page-item active"><a class="page-link" href="#">${current}</a></li>
                        <c:if test="${next lt totalPages}">
                            <li class="page-item"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${next}' />">${next}</a></li>
                        </c:if>
                        <c:if test="${next+1 lt totalPages}">
                            <li class="page-item"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${next+1}' />">${next+1}</a></li>
                        </c:if>
                        <c:if test="${next+2 lt totalPages}">
                            <li class="page-item"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${next+2}' />">${next+2}</a></li>
                        </c:if>
                        <li class="page-item ${next == totalPages ? 'disabled' : ''}"><a class="page-link" href="<c:url value='/searchUser?substring=${substring}&page=${next lt totalPages ? next : current}' />">Next</a></li>
                    </ul>
                </nav>
            </div>
        </c:otherwise>
    </c:choose>
</div>