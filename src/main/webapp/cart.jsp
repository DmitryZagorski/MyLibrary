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



<c:forEach items="${allPlaces}" var="place">
    <div class="col-md-12 ">
        <input type="radio" id="${place.placeTitle}" name="place"
               value="${place.placeTitle}" checked> <label for="${place.placeTitle}">${place.placeTitle}</label>
    </div>
</c:forEach>

<td><a href="/prepareCartToOrderServlet?place=${place}">Create order</a></td>

<c:if test="${addedId ne null}">Book ' ${addedBookTitle} ' was added successfully</c:if>

</body>
</html>
