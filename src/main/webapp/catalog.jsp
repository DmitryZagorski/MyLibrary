<%@ page import="com.epam.library.repositories.JDBCBookRepository" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/4/2022
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>

<c:choose>
    <c:when test="${allCatalog ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Book</th>
                <th>Total Quantity</th>
                <th>Free Quantity</th>
            </tr>
            <c:forEach items="${allCatalog}" var="catalog">

                <tr>
                    <td>${catalog.id}</td>
                    <td>${catalog.bookTitle}</td>
                    <td>${catalog.totalQuantity}</td>
                    <td>${catalog.freeQuantity}</td>
                    <td><a href="/addBookToCartServlet?bookTitle=${catalog.bookTitle}&freeQuantity=${catalog.freeQuantity}">
                        Add book to cart</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Books not found in catalog"/>
    </c:otherwise>
</c:choose>

<c:if test="${addedId ne null}">Book ${addedBookTitle} was added successfully</c:if>

</body>
</html>
