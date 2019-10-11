<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Registration</h2>

<form:form modelAttribute="registerUser" method="POST" action="registerUserPost">
    <form:errors path="*" element="div" />
    <p>
        <label for="input_username">Username</label>
        <form:input path="userName" id="input_username" />
    </p>
    <p>
        <label for="input_password">Password</label>
        <form:password path="password" id="input_password" />
    </p>
    <p>
        <button type="submit">Register</button>
    </p>
</form:form>