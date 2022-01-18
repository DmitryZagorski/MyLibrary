<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/8/2022
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Cart</title>
</head>
<body>


<c:choose>
    <c:when test="${allCart ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Book</th>
            </tr>
            <c:forEach items="${allCart}" var="cart">
                <tr>
                    <td>${cart.id}</td>
                    <td>${cart.bookTitle}</td>
                    <td><a href="/removeCartBookServlet?cartId=${cart.id}">Remove book from cart</a></td>
                </tr>
            </c:forEach>
        </table>

        <form action="/prepareCartToOrderServlet" method="get">
            <td>Place of reading:</td>
            <br>
            <c:forEach items="${allPlaces}" var="place">
                <div class="col-md-12 text-center">
                    <input type = "radio" id = ${place.placeTitle} name="placeTitle"
                           value="${place.placeTitle}">${place.placeTitle}</input><br>
                </div>
            </c:forEach>
            <br>
            <input type="submit" value="Create order">
            <br>
        </form>

    </c:when>
    <c:otherwise>
        <c:out value="Books not found in cart"/>
    </c:otherwise>

</c:choose>

<c:if test="${removedId ne null}">Cart with id ${removedId} was removed successfully</c:if>

<c:if test="${addedId ne null}">Book ' ${addedBookTitle} ' was added successfully</c:if>

</body>
</html>