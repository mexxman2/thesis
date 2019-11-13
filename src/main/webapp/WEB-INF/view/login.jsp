<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1>Welcome</h1>
<h2>Login or Register to start!</h2>
<form:form modelAttribute="loginRequest" action="login">
    <div class="form-row">
        <form:errors path="*" element="div" class="alert alert-danger" role="alert" />
    </div>
    <div class="form-row">
        <label for="username">Username</label>
        <form:input path="username" id="username" placeholder="Username" class="form-control" />
    </div>
    <div class="form-row">
        <label for="password">Password</label>
        <form:password path="password" id="password" placeholder="Password" class="form-control" />
    </div>
    <div class="form-row">
        <button type="submit" class="btn btn-primary">Login</button>
    </div>
</form:form>