<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Your friends</h2>
<c:choose>
    <c:when test="${empty friends}">
        <p>You have no friends</p>
    </c:when>
    <c:otherwise>
        <table>
          <c:forEach var="user" items="${friends}">
            <tr>
              <td><a href="<c:url value='/watch_list?userId=${user.id}&page=1' />">${user.userName}</a></td>
              <td><a href="<c:url value='/deleteFriend?userId=${user.id}' />">Delete friend</a></td>
            </tr>
          </c:forEach>
        </table>
        <c:if test="${isPrev}">
            <a href="<c:url value='/friends_list?page=${prevPage}' />">Previous</a>
        </c:if>
        <c:if test="${isNext}">
            <a href="<c:url value='/friends_list?page=${nextPage}' />">Next</a>
        </c:if>
    </c:otherwise>
</c:choose>

<p>Invite friends by sending them this link: <c:url value='/register?friendId=${loggedInUserId}' /></p>