<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content contact" id="menu-5">
    <div class="container">
        <div class="row">

            <title>All Customers</title>
            <h1>
                All customers:
            </h1>


            <c:choose>
                <c:when test="${allCustomers ne null}">
                    <table>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Address</th>
                            <th>Email</th>
                            <th>DateOfSignUp</th>
                            <th>Login</th>
                            <th>Password</th>
                        </tr>
                        <c:forEach items="${allCustomers}" var="customer">
                            <tr>
                                <td>${customer.id}</td>
                                <td>${customer.name}</td>
                                <td>${customer.surname}</td>
                                <td>${customer.address}</td>
                                <td>${customer.email}</td>
                                <td>${customer.dateOfSignUp}</td>
                                <td>${customer.login}</td>
                                <td>${customer.password}</td>
                                <td><a href="/removeCustomerServlet?customerId=${customer.id}">Remove customer</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <c:out value="Not found customer"/>
                </c:otherwise>
            </c:choose>
            <c:if test="${removedId ne null}">Customer with id ${removedId} was removed successfully</c:if>

            <c:if test="${addedId ne null}">Customer with id ${addedId} was added successfully</c:if>

            <form action="/addCustomerServlet" method="get">
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
                <input type="submit" value="Add customer">

            </form>

        </div> <!-- /.row -->
    </div> <!-- /.container -->
</div>
<!-- /.contact -->