<%-- 
    Document   : header
    Created on : Apr 4, 2016, 2:51:39 PM
    Author     : suhag
--%>

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
						<a href="index.html"><img src="images/logo.png" title="Grocery Shoppe" alt="Grocery Shoppe" class="img-responsive" /></a>
					</div>
				</div>
			<!-- Logo Ends -->
			<!-- Header Right Starts -->
				<div class="col-md-8 col-xs-12">
					<div class="row header-top">
					<!-- Header Links Starts -->
						<div class="col-md-9 col-xs-12">
							<div class="header-links">
								<ul class="list-unstyled list-inline pull-left">
									<li><a href="index.html">Home</a></li>
									<li><a href="#">Wish List(0)</a></li>
									<li><a href="#">My Account</a></li>
									<li><a href="cart.html">Shopping Cart</a></li>
									<li><a href="#">Checkout</a></li>
									<li><a href="register.html">Register</a></li>
									<li><a href="login.html">Login</a></li>
								</ul>
							</div>
						</div>
					<!-- Header Links Ends -->
					<!-- Currency & Languages Starts -->
						<div class="col-md-3 col-xs-12">
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
							<!-- Currency Starts -->
								<div class="btn-group">
									<button class="btn btn-link dropdown-toggle" data-toggle="dropdown">
										$
										<i class="fa fa-caret-down"></i>
									</button>
									<ul class="pull-right dropdown-menu">
										<li><a tabindex="-1" href="#">Pound </a></li>
										<li><a tabindex="-1" href="#">US Dollar</a></li>
										<li><a tabindex="-1" href="#">Euro</a></li>
									</ul>
								</div>
							<!-- Currency Ends -->
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
									<span id="cart-total">0 item(s) - $0.00</span>
									<i class="fa fa-caret-down"></i>
								</button>
								<ul class="dropdown-menu">
									<li>
										<p class="text-center">Your shopping cart is empty!</p>
									</li>									
								</ul>
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
					<li><a href="category-list.html">Asian Food</a></li>
					<li class="dropdown">
						<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">
							Bread &amp; Bakery
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a tabindex="-1" href="#">Brown Bread</a></li>
							<li><a tabindex="-1" href="#">Milk Break</a></li>
							<li><a tabindex="-1" href="#">Spl Bread</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">Drinks</a>
						<div class="dropdown-menu">
							<div class="dropdown-inner">
								<ul class="list-unstyled">
									<li class="dropdown-header">Sub Category</li>
									<li><a tabindex="-1" href="#">item 1</a></li>
									<li><a tabindex="-1" href="#">item 2</a></li>
									<li><a tabindex="-1" href="#">item 3</a></li>
								</ul>										
								<ul class="list-unstyled">
									<li class="dropdown-header">Sub Category</li>
									<li><a tabindex="-1" href="#">item 1</a></li>
									<li><a tabindex="-1" href="#">item 2</a></li>
									<li><a tabindex="-1" href="#">item 3</a></li>
								</ul>
								<ul class="list-unstyled">
									<li class="dropdown-header">Sub Category</li>
									<li><a tabindex="-1" href="#">item 1</a></li>
									<li><a tabindex="-1" href="#">item 2</a></li>
									<li><a tabindex="-1" href="#">item 3</a></li>
								</ul>
							</div>
						</div>
					</li>
					<li><a href="category-list.html">Fresh Meats</a></li>
					<li><a href="category-list.html">Fresh Fish</a></li>
					<li><a href="category-list.html">Fruits</a></li>
					<li><a href="category-list.html">Vegetables</a></li>
                    <li class="dropdown">
						<a href="category-list.html" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="10">
							Pages
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a tabindex="-1" href="index.html">Home</a></li>
                            <li><a tabindex="-1" href="about.html">About</a></li>
							<li><a tabindex="-1" href="category-list.html">Category List</a></li>
							<li><a tabindex="-1" href="category-grid.html">Category Grid</a></li>
							<li><a tabindex="-1" href="product.html">Product</a></li>
							<li><a tabindex="-1" href="product-full.html">Product Full Width</a></li>
                            <li><a tabindex="-1" href="cart.html">Cart</a></li>
                            <li><a tabindex="-1" href="login.html">Login</a></li>
                            <li><a tabindex="-1" href="compare.html">Compare Products</a></li>
							<li><a tabindex="-1" href="typography.html">Typography</a></li>
							<li><a tabindex="-1" href="register.html">Register</a></li>
                            <li><a tabindex="-1" href="contact.html">Contact</a></li>
							<li><a tabindex="-1" href="404.html">404</a></li>
						</ul>
					</li>

				</ul>
			</div>
		<!-- Navbar Cat collapse Ends -->
		</nav>
	<!-- Main Menu Ends -->
