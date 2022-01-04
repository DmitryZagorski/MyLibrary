<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en">
<![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8" lang="en">
<![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->

<head>


    <title>Concept HTML5 Layout</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <!--
    Concept Template
    http://www.templatemo.com/tm-397-concept
    -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,700italic,400,600,700,800'
          rel='stylesheet' type='text/css'>

    <!-- CSS Bootstrap & Custom -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/templatemo_misc.css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="css/templatemo_style.css">

    <!-- Favicons -->
    <link rel="shortcut icon" href="images/ico/favicon.ico">

</head>

<body>
<div class="site-header">
    <div class="main-navigation">
        <div class="responsive_menu">
            <ul>
                <li><a class="show-1 templatemo_home" href="#">Home</a></li>
                <li><a class="show-2 templatemo_page2" href="#">Catalog</a></li>
                <li><a class="show-3 templatemo_page3" href="#">Contact us</a></li>
                <c:if test="${sessionScope.customer eq null}">
                    <li><a class="show-4 templatemo_page4" href="#">Sign in / Sign up</a></li>
                </c:if>
                <c:if test="${sessionScope.customer ne null}">
                    <li><a class="show-4 templatemo_page4" href="#">Logout</a></li>
                </c:if>
                <li><a class="show-5 templatemo_page5" href="#">Service</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12 responsive-menu">
                    <a href="#" class="menu-toggle-btn">
                        <i class="fa fa-bars"></i>
                    </a>
                </div> <!-- /.col-md-12 -->
                <div class="col-md-12 main_menu">
                    <ul>
                        <li><a class="show-1 templatemo_home" href="#">Home</a></li>
                        <li><a class="show-2 templatemo_page2" href="#">Catalog</a></li>
                        <li><a class="show-3 templatemo_page3" href="#">Contact us</a></li>
                        <c:if test="${sessionScope.customer eq null}">
                            <li><a class="show-4 templatemo_page4" href="#">Sign in / Sign up</a></li>
                        </c:if>
                        <c:if test="${sessionScope.customer ne null}">
                            <li><a class="show-4 templatemo_page4" href="#">Logout</a></li>
                        </c:if>
                        <li><a class="show-5 templatemo_page5" href="#">Service</a></li>
                    </ul>
                </div> <!-- /.col-md-12 -->
            </div> <!-- /.row -->
        </div> <!-- /.container -->
    </div> <!-- /.main-navigation -->
    <c:if test="${sessionScope.customer ne null}">
        <div class="col-md-12 main_menu">
            <li class="nav-item"> Hello, ${sessionScope.customer.name} ${sessionScope.customer.surname}
            </li>
        </div>
    </c:if>
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <form action="/searchServlet">
                    <form id="form" role="search">
                        <input type="search" id="query" name="q"
                               placeholder="Search..."
                               aria-label="Search through site content">
                        <button>Search</button>
                    </form>
                </form>
                <a href="#" class="templatemo_logo">
                    <h1>Library</h1>
                </a> <!-- /.logo -->
            </div> <!-- /.col-md-12 -->
        </div> <!-- /.row -->
    </div> <!-- /.container -->
</div> <!-- /.site-header -->

<div id="menu-container">

    <%@include file="/sectionHome.jsp" %>

    <%@include file="/sectionCatalog.jsp" %>

    <%@include file="/sectionContactUs.jsp" %>

    <%@include file="/sectionSignInSignUp.jsp" %>

    <%@include file="/sectionService.jsp" %>


    <div id="templatemo_footer">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <p>Copyright &copy; 2084 Your Company Name</p>
                </div> <!-- /.col-md-12 -->
            </div> <!-- /.row -->
        </div> <!-- /.container -->
    </div> <!-- /.templatemo_footer -->
</div>

    <!-- Scripts -->
    <script src="js/jquery-1.10.2.min.js"></script>
    <script src="js/jquery-migrate-1.2.1.min.js"></script>
    <script src="js/modernizr.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/tabs.js"></script>
    <script src="js/jquery.lightbox.js"></script>
    <script src="js/templatemo_custom.js"></script>
    <!--
        Free Responsive Template by templatemo
        http://www.templatemo.com
    -->
    <!-- templatemo 397 concept -->
</body>
</html>