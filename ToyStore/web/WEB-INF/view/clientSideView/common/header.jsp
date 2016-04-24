<%-- 
    Document   : header
    Created on : Apr 4, 2016, 2:51:39 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <!--[if IE]>
                <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <![endif]-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Toy Store</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Google Web Fonts -->
        <link href="http://fonts.googleapis.com/css?family=Roboto+Condensed:300italic,400italic,700italic,400,300,700" rel="stylesheet" type="text/css">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!-- CSS Files -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="css/magnific-popup.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <link href="css/responsive.css" rel="stylesheet">
        <!-- JavaScript Files -->
        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/jquery-migrate-1.2.1.min.js"></script>	
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-hover-dropdown.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/custom.js"></script>

        <!--[if lt IE 9]>
                <script src="js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <!-- Wrapper Starts -->
        <div id="wrapper" class="container">
            <!-- Header Section Starts -->
            <header id="header-area">
                <!-- Nested Row Starts -->
                <div class="row">
                    <!-- Logo Starts -->
                    <div class="col-md-4 col-xs-12">
                        <div id="logo">
                            <a href="/ToyStore/home"><img src="images/logo.png" title="Grocery Shoppe" alt="Grocery Shoppe" class="img-responsive" /></a>
                        </div>
                    </div>
                    <!-- Logo Ends -->
                    <!-- Header Right Starts -->
                    <div class="col-md-8 col-xs-12">
                        <div class="row header-top">
                            <!-- login & Languages Starts -->
                            <div class="col-md-3 col-xs-12 pull-right">
                                <div class="pull-right">							
                                    <!-- Languages Starts -->
                                    <div class="btn-group">
                                        <button class="btn btn-link dropdown-toggle" data-toggle="dropdown">
                                            ENG
                                            <i class="fa fa-caret-down"></i>
                                        </button>
                                        <ul class="pull-right dropdown-menu">
                                            <li>
                                                <a tabindex="-1" href="#">English</a>
                                            </li>
                                            <li>
                                                <a tabindex="-1" href="#">French</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <!-- Languages Ends -->
                                    <!-- login Starts -->
                                    <div class="btn-group">
                                        <button class="btn btn-link dropdown-toggle" data-toggle="dropdown">
                                            ${customerFname}
                                            <i class="fa fa-caret-down"></i>
                                        </button>
                                        <ul class="pull-right dropdown-menu">
                                            <li><a tabindex="-1" href="/ToyStore/logoutClient"><i class="fa fa-sign-out" aria-hidden="true"></i>logout</a></li>
                                        </ul>
                                    </div>
                                    <!-- login Ends -->
                                </div>
                            </div>
                            <!-- Currency & Languages Ends -->
                        </div>
                        <div class="row">
                            <!-- Search Starts -->					
                            <div class="col-md-7 col-xs-12">
                                <div id="search">
                                    <div class="input-group">
                                        <input type="text" class="form-control input-lg" placeholder="Search">
                                        <span class="input-group-btn">
                                            <button class="btn btn-lg" type="button">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>	
                            </div>
                            <!-- Search Ends -->						
                            <!-- Shopping Cart Starts -->					
                            <div class="col-md-5 col-xs-12">
                                <div id="cart" class="btn-group btn-block">
                                    <button type="button" data-toggle="dropdown" class="btn btn-block btn-lg dropdown-toggle">
                                        <i class="fa fa-shopping-cart"></i>
                                        <span class="hidden-xs">Cart:</span> 
                                        <c:choose>
                                            <c:when test="${sessionScope.cart.items[0]==null}">
                                                <span id="cart-total">0 item(s) - $0.00</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span id="cart-total">${sessionScope.cart.numberOfItems} item(s) - $ ${sessionScope.cart.total}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </button>
                                </div>
                            </div>
                            <!-- Shopping Cart Ends -->						
                        </div>
                    </div>
                    <!-- Header Right Ends -->				
                </div>
                <!-- Nested Row Ends -->
            </header>
            <!-- Header Section Ends -->
            <!-- Main Menu Starts -->
            <nav id="main-menu" class="navbar" role="navigation">
                <!-- Nav Header Starts -->
                <div class="navbar-header">
                    <button type="button" class="btn btn-navbar navbar-toggle" data-toggle="collapse" data-target=".navbar-cat-collapse">
                        <span class="sr-only">Toggle Navigation</span>
                        <i class="fa fa-bars"></i>
                    </button>
                </div>
                <!-- Nav Header Ends -->
                <!-- Navbar Cat collapse Starts -->
                <div class="collapse navbar-collapse navbar-cat-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/ToyStore/home">Home</a></li>
                        <li class="dropdown">
                            <a href="/ToyStore/accountDetails" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">My Account</a>
                            <div class="dropdown-menu">
                                <div class="dropdown-inner">
                                    <ul class="list-unstyled">
                                        <li><a tabindex="-1" href="/ToyStore/accountDetails">Account Details</a></li>
                                        <li><a tabindex="-1" href="/ToyStore/editCustomer">Edit Details</a></li>
                                        <li><a tabindex="-1" href="/ToyStore/editPassword">Change Password</a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li><a href="/ToyStore/compareProducts">Compare List</a></li>
                        <li><a href="/ToyStore/wishList">Wish List</a></li>
                        <li><a href="/ToyStore/orderHistory">Order History</a></li>
                        <li><a href="/ToyStore/productList">Browse toys</a></li>
                        <li><a href="/ToyStore/recycleProductList">View Recycled Toys</a></li>
                        <li><a href="/ToyStore/addRecycleProduct">Add Recycled Toy</a></li>
                        <li><a href="/ToyStore/cart">Cart</a></li>
                        <li></li>
                    </ul>
                </div>
                <!-- Navbar Cat collapse Ends -->
            </nav>
            <!-- Main Menu Ends -->
