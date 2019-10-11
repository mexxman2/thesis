<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>Welcome</h1>

<h2>Top 10 most popular items</h2>
<table>

  <c:forEach var="item" items="${topTenItems}">
    <tr>
      <td><a href="<c:url value='/details?itemId=${item.id}' />">${item.title}</a></td>
    </tr>
  </c:forEach>

</table>