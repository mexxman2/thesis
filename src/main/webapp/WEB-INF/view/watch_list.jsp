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
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></td>
                            <td><a href="<c:url value='/deleteItem?itemId=${item.id}' />">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${isPrev}">
                    <a href="<c:url value='/watch_list?page=${prevPage}' />">Previous</a>
                </c:if>
                <c:if test="${isNext}">
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
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${isPrev}">
                    <a href="<c:url value='/watch_list?userId=${user.id}&page=${prevPage}' />">Previous</a>
                </c:if>
                <c:if test="${isNext}">
                    <a href="<c:url value='/watch_list?userId=${user.id}&page=${nextPage}' />">Next</a>
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
