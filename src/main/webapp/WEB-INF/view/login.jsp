<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Recommendation</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <h1 id="header">Welcome</h1>
        </div>
        <h2>Login or Register to start!</h2>
        <form:form modelAttribute="loginRequest" action="login">
            <form:errors path="*" element="div" cssClass="validation-error" />
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
    </body>
</html>