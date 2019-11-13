<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Your friends</h2>
<c:choose>
    <c:when test="${empty friends}">
        <p>You have no friends</p>
    </c:when>
    <c:otherwise>
        <table>
          <c:forEach var="user" items="${friends}">
            <tr>
              <td><a href="<c:url value='/watch_list?userId=${user.id}' />">${user.userName}</a></td>
              <td><a href="<c:url value='/deleteFriend?userId=${user.id}' />">Delete friend</a></td>
            </tr>
          </c:forEach>
        </table>
        <div class="row">
            <nav>
                <ul class="pagination">
                    <li class="page-item ${previous == current ? 'disabled' : ''}"><a class="page-link" href="<c:url value='/friends_list?page=${previous}' />">Previous</a></li>
                    <c:if test="${previous-1 ge 0}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/friends_list?page=${previous-1}' />">${previous-1}</a></li>
                    </c:if>
                    <c:if test="${previous lt current}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/friends_list?page=${previous}' />">${previous}</a></li>
                    </c:if>
                    <li class="page-item active"><a class="page-link" href="#' />">${current}</a></li>
                    <c:if test="${next lt totalPages}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/friends_list?page=${next}' />">${next}</a></li>
                    </c:if>
                    <c:if test="${next+1 lt totalPages}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/friends_list?page=${next+1}' />">${next+1}</a></li>
                    </c:if>
                    <c:if test="${next+2 lt totalPages}">
                        <li class="page-item"><a class="page-link" href="<c:url value='/friends_list?page=${next+2}' />">${next+2}</a></li>
                    </c:if>
                    <li class="page-item ${next == totalPages ? 'disabled' : ''}"><a class="page-link" href="<c:url value='/friends_list?page=${next lt totalPages ? next : current}' />">Next</a></li>
                </ul>
            </nav>
        </div>
    </c:otherwise>
</c:choose>

<form:form modelAttribute="email" action="sendEmail" method="post">
    <form:errors path="*" element="div" cssClass="validation-error" />
    <p>
        <label for="email">Invite friends by entering their email address:</label>
        <form:input path="email" id="email" placeholder="Email address" />
    </p>
    <p>
        <button type="submit">Send</button>
    </p>
</form:form>
<c:if test="${emailSent}">
    <p>Email sent</p>
</c:if>