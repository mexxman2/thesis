<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
    <c:when test="${empty user}">
        <h2>Your list</h2>
        <c:choose>
            <c:when test="${empty items}">
                <p>List is empty</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Title</th>
                        <th>Rating</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="watched" items="${items}">
                        <tr>
                            <td><a href="<c:url value='/details?itemId=${watched.item.id}' />">${watched.item.title}</a></td>
                            <td>${watched.rating}</td>
                            <td><a href="<c:url value='/deleteWatched?watchedId=${watched.id}&page=${prevPage+1}' />">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${hasPrev}">
                    <a href="<c:url value='/watch_list?page=${prevPage}' />">Previous</a>
                </c:if>
                <c:if test="${hasNext}">
                    <a href="<c:url value='/watch_list?page=${nextPage}' />">Next</a>
                </c:if>
            </c:otherwise>
        </c:choose>
        <%-- recommendation --%>
    </c:when>
    <c:otherwise>
        <h2>${user.userName}&#39;s list</h2>
        <c:choose>
            <c:when test="${isFriend}">
                <a href="<c:url value='/deleteFriend?userId=${user.id}' />">Delete friend</a>
            </c:when>
            <c:otherwise>
                <a href="<c:url value='/addFriend?userId=${user.id}' />">Add friend</a>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${empty items}">
                <p>List is empty</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Title</th>
                        <th>Rating</th>
                    </tr>
                    <c:forEach var="watched" items="${items}">
                        <tr>
                            <td><a href="<c:url value='/details?itemId=${watched.item.id}' />">${watched.item.title}</a></td>
                            <td>${watched.rating}</td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${hasPrev}">
                    <a href="<c:url value='/watch_list?userId=${user.id}&page=${prevPage}' />">Previous</a>
                </c:if>
                <c:if test="${hasNext}">
                    <a href="<c:url value='/watch_list?userId=${user.id}&page=${nextPage}' />">Next</a>
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
