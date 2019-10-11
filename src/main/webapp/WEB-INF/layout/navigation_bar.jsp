<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="nav-link" href="<c:url value='/' />">Home</a>
    <a class="nav-link" href="<c:url value='/watch_list?page=1' />">Watch List</a>
    <a class="nav-link" href="<c:url value='/friends_list?page=1' />">Friends List</a>
    <sec:authorize access="isAnonymous()">
        <a class="nav-link" href="<c:url value='/register' />">Register</a>
        <a class="nav-link" href="<c:url value='/login' />">Login</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a class="nav-link" href="<c:url value='/logout' />">Logout</a>
    </sec:authorize>
</nav>