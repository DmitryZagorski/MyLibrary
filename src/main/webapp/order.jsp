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
    <c:when test="${booksInOrder ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Book</th>
            </tr>
            <c:forEach items="${booksInOrder}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.bookTitle}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <c:out value="Total quantity: ${totalQuantity}"/>
        <br>
        <c:out value="Customer: ${customerName} ${customerSurname}"/>
        <br>
        <c:out value="Creation date: ${creationDate}"/>
        <br>
        <c:out value="Expiration date: ${expirationDate}"/>
        <br>
        <c:out value="Place of reading: ${placeOfReading}"/>
        <br>
    </c:when>
    <c:otherwise>
        <c:out value="Books not found in order"/>
    </c:otherwise>
</c:choose>

<td><a href="/saveOrderToFile">Save order to file</a></td>

</body>
</html>
