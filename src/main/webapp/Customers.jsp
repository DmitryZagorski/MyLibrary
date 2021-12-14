<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.12.2021
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>My Customers</title>
</head>
<body>

<c:choose>
    <c:when test="${customers ne null}">
        <table>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Birth</th>
                <th>Address</th>
                <th>DateOfSignUp</th>
                <th>Action</th>
            </tr>
        <c:forEach items="${customers}" var = "customer">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.name}</td>
                <td>${customer.surname}</td>
                <td>${customer.birth}</td>
                <td>${customer.address}</td>
                <td>${customer.dateOfSignUp}</td>
                <td><a href="/removeCustomer?customerId=${customer.id}">Remove customer</a></td>
            </tr>
        </c:forEach>
        </table>
         
    </c:when>
    <c:otherwise>
        <c:out value="Not found user"/>
    </c:otherwise>
</c:choose>
<c:if test="${removedId ne null}">Customer with id ${removedId} was removed successfully</c:if>

<c:if test="${addedId ne null}">Customer with id ${addedId} was added successfully</c:if>

<form action="/addCustomer"method="get">
    <label for="fname">First name:</label><br>
    <input type="text" id="fname" name="fname" value="Enter name"><br>
    <label for="lname">Last name:</label><br>
    <input type="text" id="lname" name="lname" value="Enter surname"><br><br>
    <label for="birth">Birth:</label><br>
    <input type="date" id="birth" name="birth" value="Enter date of birth"><br>
    <label for="address">Address:</label><br>
    <input type="text" id="address" name="address" value="Enter address"><br>
    <label for="dateOfSignUp">Date of sign up:</label><br>
    <input type="date" id="dateOfSignUp" name="dateOfSignUp" value="Date of sign up"><br>
    <input type="submit" value="Add customer">
</form>

</body>
</html>