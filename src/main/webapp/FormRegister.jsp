<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12/25/2021
  Time: 5:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Greeting</title>
</head>
<body>

<h1>
    <p>
        Registration
    </p>
</h1>
<form action="/registrationServlet">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="surname">Surname:</label><br>
    <input type="text" id="surname" name="surname"><br><br>
    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address"><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email"><br>
    <label for="dateOfSignUp">Date of sign up:</label><br>
    <input type="date" id="dateOfSignUp" name="dateOfSignUp"><br>
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Register">
</form>

<h1>
    <p>
        Login
    </p>
</h1>

<form action="/login">
    <label for="login">Login:</label><br>
    <input type="text" id="login1" name="login"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password1" name="password"><br>

    <input type="submit" value="Login">
</form>

<br>
<br>


</body>
</html>