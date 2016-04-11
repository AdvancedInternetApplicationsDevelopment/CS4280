<%-- 
    Document   : adminApprovals
    Created on : Apr 3, 2016, 3:19:23 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Approvals</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <style>
        .btn:hover {
                background: black;
        }
        .btn {
            background: #009688;
        }
    </style>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/ToyStore/adminDashboard">Admin Console</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Admin <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="/ToyStore/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="/ToyStore/adminDashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="/ToyStore/adminCustomer"><i class="fa fa-fw fa-bar-chart-o"></i> Customers</a>
                    </li>
                    <li>
                        <a href="/ToyStore/adminProducts"><i class="fa fa-fw fa-table"></i>Products</a>
                    </li>
                    <li class="active">
                        <a href="/ToyStore/adminApprovals"><i class="fa fa-fw fa-edit"></i>Approvals</a>
                    </li>
                    <li >
                        <a href="/ToyStore/adminAddProducts"><i class="fa fa-plus-square-o"></i> Add products</a>
                    </li>
                    <li>
                        <a href="/ToyStore/adminTransactions"><i class="fa fa-money fa-fw"></i> Transactions/ Order</a>
                    </li>
                    <li>
                        <a href="/ToyStore/adminRecycledProducts"><i class="fa fa-fw fa-table"></i>Recycled Products</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Approvals
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-fw fa-edit"></i> Approvals
                            </li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Approvals Panel</h3>
                            </div>
                            <div class="panel-body">
                                <div class="list-group">
                                    <!-- Product List Display Starts -->
                    					<div class="row">
                    					<!-- Product #1 Starts -->
                    						<div class="col-xs-12">
                                                                    <c:choose>
                                                                        <c:when test="${approveProducts == null}">
                                                                            <div><p>No pending approvals</p></div>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <c:forEach var="approveProduct" items="${approveProducts}">
                                                                                <div class="product-col list clearfix" style=" padding: 15px 15px 10px;  margin-bottom: 20px; background: #fff; border: 1px solid #e8e8e8;">
                                                                                        <div class="image" style="float: left; padding: 20px 30px 10px 0;">
                                                                                                <img src="/ToyStore/getImage?productId=${approveProduct.id}" alt="product" class="img-responsive" />
                                                                                        </div>
                                                                                        <div class="caption" style="color: #252a2f;font: 14px/22px 'Open Sans', Arial, Helvetica, sans-serif;padding: 20px 0;">
                                                                                                <h4 style="color: #252a2f; font-weight: bold; font-size: 16px; text-transform: uppercase;"><a style= "color: #252a2f;"href="/ToyStore/adminViewRecycled?productId=${approveProduct.name}">${approveProduct.name}</a></h4>
                                                                                                <div class="description" style=" padding-right: 20px; padding: 5px 0;font: 14px/22px 'Open Sans', Arial, Helvetica, sans-serif; line-height: 18px; text-align: justify;">
                                                                                                     ${approveProduct.description}  
                                                                                                </div>
                                                                                                <div class="price" style=" color: #252a2f;font: 14px/22px 'Open Sans', Arial, Helvetica, sans-serif; padding: 10px 0;">
                                                                                                        <p class="product-owner" style=" font-size: 14px; color: #2f353b;">Product Owner: ${approveProduct.owner}</p>
                                                                                                        <span class="price-new" style=" color: #ef4135; font-size: 24px;padding-right: 5px;">$199.50</span>
                                                                                                        <span class="price-old" style =" font-size: 18px; color: #808080;text-decoration: line-through;">$ ${approveProduct.price}</span>
                                                                                                </div>
                                                                                                <div class="cart-button button-group" style="padding-top:10px">
                                                                                                        <c:choose>
                                                                                                            <c:when test="${approveProduct.approved == false}">
                                                                                                                <form action="/ToyStore/approveProduct" method="post">
                                                                                                                    <input type="hidden" name="productId" value="${approveProduct.id}">
                                                                                                                    <button type="submit" class="btn btn-cart" style="font-size: 14px;color: #fff;text-transform: uppercase;" value="/ToyStore/approveProduct">
                                                                                                                        approve
                                                                                                                        <i class="fa fa-check" style="margin-right: 5px;"></i>
                                                                                                                    </button>
                                                                                                                </form>
                                                                                                            </c:when>
                                                                                                            <c:otherwise>
                                                                                                                <button type="button" class="btn btn-cart disabled" style="font-size: 14px;color: #fff;text-transform: uppercase;" disabled="disabled">
                                                                                                                    approved
                                                                                                                    <i class="fa fa-check" style="margin-right: 5px;"></i>
                                                                                                                </button>
                                                                                                            </c:otherwise>
                                                                                                        </c:choose>
                                                                                                </div>
                                                                                        </div>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </c:otherwise>
                                                                    </c:choose>
                    							
                    						</div>
                    					<!-- Product #1 Ends -->
                    					</div>
                                    <!-- Product List Display Ends -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="js/plugins/morris/raphael.min.js"></script>
    <script src="js/plugins/morris/morris.min.js"></script>
    <script src="js/plugins/morris/morris-data.js"></script>

</body>

</html>

