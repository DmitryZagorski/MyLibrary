<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/12/2022
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>

<c:choose>
    <c:when test="${allCart ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Book</th>
                <th>Book Quantity</th>
                <th>Customer</th>
                <th>Order</th>
            </tr>
            <c:forEach items="${allCart}" var="cart">
                <tr>
                    <td>${cart.id}</td>
                    <td>${cart.bookId}</td>
                    <td>${cart.bookQuantity}</td>
                    <td>${cart.customerId}</td>
                    <td>${cart.orderId}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Books not found in cart"/>
    </c:otherwise>
</c:choose>

<form action="/createOrderServlet"method="get">
    <label for="cartId">CartId:</label><br>
    <input type="text" id="cartId" name="cartId"><br>
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
    <input type="submit" value="Add customer">

</form>










<td>Place of reading:</td>
<form>


    <c:forEach items="${allPlaces}" var="place">
    <div class="col-md-12 text-center">
        <input type = "radio" id = ${place.placeTitle} name="place"
               value="${place.placeTitle}">${place.placeTitle}</input><br>
    </div>

    </c:forEach>


</body>
</html>
