<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-dark bg-dark">
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
    <form:form modelAttribute="searchString" action="searchBySubstring" method="post" class="form-inline my-2 my-lg-0">
        <form:input class="form-control mr-sm-2" path="searchSubstring" id="search_bar" placeholder="Search item or user" aria-label="Search" />
        <div class="btn-group">
            <input type="submit" value="Search item" name="itemSearch" class="btn btn-outline-success my-2 my-sm-0">
            <input type="submit" value="Search user" name="userSearch" class="btn btn-outline-success my-2 my-sm-0">
        </div>
    </form:form>
</nav>