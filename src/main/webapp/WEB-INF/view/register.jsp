<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Registration</h2>

<form:form modelAttribute="registerUser" method="POST" action="registerUserPost" class="form-inline">
    <div class="form-row">
        <form:errors path="*" element="div" class="alert alert-danger" role="alert" />
    </div>
    <div class="form-row">
        <label for="input_username">Username</label>
        <form:input path="userName" id="input_username" class="form-control" />
    </div>
    <div class="form-row">
        <label for="input_password">Password</label>
        <form:password path="password" id="input_password" class="form-control" />
    </div>
    <div class="form-row">
        <button type="submit" class="btn btn-primary">Register</button>
    </div>
</form:form>