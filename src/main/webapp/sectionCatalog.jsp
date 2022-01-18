<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="content products" id="menu-2">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">

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

            </div> <!-- /.col-md-4 -->
        </div> <!-- /.row -->
    </div> <!-- /.slide-item -->
</div> <!-- /.products -->
