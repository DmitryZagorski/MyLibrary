<div class="content about" id="menu-4">

    <c:if test="${sessionScope.customer eq null}">
        <li><a class="show-4 templatemo_page4" href="#">Logout</a></li>

        <div class="container">

            <h1>
                Sign in
            </h1>
            <div class="col-md-6 ">
                <form action="/loginServlet">
                    <div class="row">
                        <div class="col-md-12 ">
                            <input class="form_contril" placeholder="Login " type="text" name="login">
                        </div>
                        <div class="col-md-12">
                            <input class="form_contril" placeholder="Password" type="password" name="password">
                        </div>
                        <div class="col-sm-12">
                            <input class="send_btn" type="submit" value="Sign in">
                        </div>
                    </div>
                </form>
                <br>
                <h1>
                    Sign up
                </h1>
                <div class="col-md-6 ">
                    <form action="/registrationServlet">
                        <div class="row">
                            <div class="col-md-12 ">
                                <input class="form_contril" placeholder="Name" type="text" name="name">
                            </div>
                            <div class="col-md-12 ">
                                <input class="form_contril" placeholder="Surname" type="text" name="surname">
                            </div>
                            <div class="col-md-12 ">
                                <input class="form_contril" placeholder="Address" type="text" name="address">
                            </div>
                            <div class="col-md-12 ">
                                <input class="form_contril" placeholder="Email" type="text" name="email">
                            </div>
                            <div class="col-md-12 ">
                                <input class="form_contril" placeholder="DateOfSignUp" type="date"
                                       name="dateOfSignUp">
                            </div>
                            <div class="col-md-12 ">
                                <input class="form_contril" placeholder="Login" type="text" name="login">
                            </div>
                            <div class="col-md-12">
                                <input class="form_contril" placeholder="Password" type="password" name="password">
                            </div>
                            <div class="col-sm-12">
                                <input class="send_btn" type="submit" value="Sign up">
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <!-- /.services -->
    </c:if>

    <c:if test="${sessionScope.customer ne null}">
        <li><a class="show-4 templatemo_page4" href="/logoutServlet">Logout</a></li>
    </c:if>

</div>
<!-- /#menu-container -->
