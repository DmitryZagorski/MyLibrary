<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/4/2022
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>
    <c:when test="${allBooks ne null}">
        <table>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Author</th>
                <th>IssueDate</th>
                <th>Genre</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${allBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.issueDate}</td>
                    <td>${book.genreId}</td>
                    <td>
                    <form action="/addBookToCatalogServlet">
                        <label for="bookId">BookId</label><br>
                        <input type="text" id="bookId" name="bookId" value="${book.id}"><br>
                        <label for="quantity">Quantity</label><br>
                        <input type="text" id="quantity" name="quantity"><br>
                        <input type="submit" value="Add book to catalog">
                    </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found book"/>
    </c:otherwise>
</c:choose>
<c:if test="${addedId ne null}">Book with title ${addedBookTitle} was added successfully</c:if>

</body>
</html>
