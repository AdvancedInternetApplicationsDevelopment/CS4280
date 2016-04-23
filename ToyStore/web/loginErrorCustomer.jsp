<%-- 
    Document   : loginErrorCustomer
    Created on : Apr 3, 2016, 6:02:12 PM
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

	<title>Toy Store login</title>

	<!-- Bootstrap Core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- Google Web Fonts -->
	<link href="http://fonts.googleapis.com/css?family=Roboto+Condensed:300italic,400italic,700italic,400,300,700" rel="stylesheet" type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

	<!-- JavaScript Files -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-hover-dropdown.min.js"></script>
	<script src="js/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/custom.js"></script>

	<!-- CSS Files -->
	<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
	<script src="js/formValidation.js"></script>
	<link href="css/formValidation.css" rel="stylesheet">
	<script type="text/javascript" src="js/framework/bootstrap.js"></script>


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
	<div id="wrapper" class="container" style="margin-top: 50px;">
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

			</div>
		<!-- Nested Row Ends -->
		</header>
	<!-- Header Section Ends -->

	<!-- Main Container Starts -->
		<div id="main-container">
		<!-- Main Heading Starts -->
			<h2 class="main-heading text-center">
				Login or create new account
			</h2>
		<!-- Main Heading Ends -->
		<!-- Login Form Section Starts -->
			<section class="login-area">
				<div class="row">
					<div class="col-sm-12">
					<!-- Login Panel Starts -->
						<div class="panel panel-smart">
							<div class="panel-heading">
								<h3 class="panel-title">Login</h3>
							</div>
							<div class="panel-body">
								<p>
                                                                    Login
								</p>
                                                                <p>Login Error. wrong credentials</p>
                                                                
                                                                <a href="/ToyStore/login.jsp">
                                                                    click to login again
								</a>
							</div>
						</div>
					<!-- Login Panel Ends -->
					</div>
				</div>
			</section>
		<!-- Login Form Section Ends -->
		</div>
	<!-- Main Container Ends -->
	<!-- Footer Section Starts -->
		<footer id="footer-area">
		<!-- Footer Links Starts -->
			<div class="footer-links row">
			<!-- Information Links Starts -->
				<div class="col-md-3 col-sm-6">
					<h5>Information</h5>
					<ul>
						<li><a href="about.html">About Us</a></li>
						<li><a href="#">Delivery Information</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#">Terms &amp; Conditions</a></li>
					</ul>
				</div>
			<!-- Information Links Ends -->
			<!-- Customer Service Links Starts -->
				<div class="col-md-3 col-sm-6">
					<h5>Service</h5>
					<ul>
						<li><a href="contact.html">Contact Us</a></li>
						<li><a href="#">Returns</a></li>
						<li><a href="#">Site Map</a></li>
						<li><a href="#">Affiliates</a></li>
						<li><a href="#">Specials</a></li>
					</ul>
				</div>
			<!-- Customer Service Links Ends -->
			<!-- Follow Us Links Starts -->
				<div class="col-md-3 col-sm-6">
					<h5>Follow Us</h5>
					<ul>
						<li><a href="#">Facebook</a></li>
						<li><a href="#">Twitter</a></li>
						<li><a href="#">RSS</a></li>
						<li><a href="#">YouTube</a></li>
					</ul>
				</div>
			<!-- Follow Us Links Ends -->
			<!-- Contact Us Starts -->
				<div class="col-md-3 col-sm-12 last">
					<h5>Contact Us</h5>
					<ul>
						<li>My Company</li>
						<li>
							22 cornwall Street, Kowloon Tong City University
						</li>
						<li>
							Email: <a href="#">info@demolink.com</a>
						</li>
					</ul>
					<h4 class="lead">
						Tel: <span>1(234) 567-9842</span>
					</h4>
				</div>
			<!-- Contact Us Ends -->
			</div>
		<!-- Footer Links Ends -->
		</footer>
	<!-- Footer Section Ends -->
	</div>
<!-- Wrapper Ends -->
<!-- Copyright Area Starts -->
	<div class="copyright container">
		<div class="clearfix">
		<!-- Starts -->
			<p class="pull-left">
				<i class="fa fa-copyright" aria-hidden="true"></i> Toy Store. designed By <a href="#">Suhag Byaravalli Arun</a> and <a href="#">Ninad Tungare</a>. This web site exists to fulfill the coursework requirement of CS4280.
                            Do not use your real personal data as input.
			</p>
		<!-- Ends -->
		</div>
	</div>
<!-- Copyright Area Ends -->
</body>
</html>



