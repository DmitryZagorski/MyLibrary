<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 07.12.2021
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Books</title>
    <h1>
        All books:
    </h1>

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
            </tr>
            <c:forEach items="${allBooks}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.issueDate}</td>
                    <td>${book.genreId}</td>
                    <td><a href="/removeBookServlet?bookId=${book.id}">Remove book</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <c:out value="Not found book"/>
    </c:otherwise>
</c:choose>
<c:if test="${removedId ne null}">Book with id ${removedId} was removed successfully</c:if>

<c:if test="${addedId ne null}">Book with id ${addedId} was added successfully</c:if>

<form action="/addBookServlet" method="get">
    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title"><br>
    <label for="author">Author:</label><br>
    <input type="text" id="author" name="author"><br>
    <label for="issueDate">IssueDate:</label><br>
    <input type="date" id="issueDate" name="issueDate"><br>
    <td>Genre:</td>
    <br>
    <c:forEach items="${allGenres}" var="genre">
    <div class="col-md-12 text-center">
            <input type = "radio" id = ${genre.title} name="genre"
                   value="${genre.title}">${genre.title}</input><br>
    </div>
    </c:forEach>
    <br>

    <input type="submit" value="Add book">
    <br>

</form>

</body>
</html>