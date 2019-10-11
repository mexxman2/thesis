<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sports betting</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <h1 id="header">Welcome</h1>
        </div>
        <h2>Login or Register to start!</h2>
        <form:form modelAttribute="loginRequest" action="login">
            <form:errors path="*" element="div" cssClass="validation-error" />
            <p>
                <label for="username">Username</label>
                <form:input path="username" id="username" placeholder="Username" />
            </p>
            <p>
                <label for="password">Password</label>
                <form:password path="password" id="password" placeholder="Password" />
            </p>
            <p>
                <button type="submit">Login</button>
            </p>
        </form:form>
    </body>
</html>