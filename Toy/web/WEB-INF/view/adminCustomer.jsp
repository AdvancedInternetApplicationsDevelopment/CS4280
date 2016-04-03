<%-- 
    Document   : adminCustomer
    Created on : Apr 3, 2016, 3:20:01 PM
    Author     : suhag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Customer database</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
                <a class="navbar-brand" href="index.html">Admin Console</a>
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
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
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
                    <li class="active">
                        <a href="/ToyStore/adminCustomer"><i class="fa fa-fw fa-bar-chart-o"></i> Customers</a>
                    </li>
                    <li>
                        <a href="/ToyStore/adminProducts"><i class="fa fa-fw fa-table"></i>Products</a>
                    </li>
                    <li>
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
                            Customer details
                        </h1>
                        <ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-fw fa-bar-chart-o"></i> Customer
                            </li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-6">
                    <!-- Registration Block Starts -->
                    <!-- Personal Information Ends -->
                        <h3 class="panel-heading">
                            Search Customer
                        </h3>
                        <div class="panel panel-smart">

                            <!-- Delivery Information Starts -->
                            <div class="panel-body">
                            <!-- Registration Form Starts -->
                                <form class="form-horizontal" role="form">
                                <!-- Personal Information Starts -->
                                    <div class="form-group">
                                        <label for="inputFname" class="col-sm-3 control-label">First Name :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputFname" placeholder="First Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputLname" class="col-sm-3 control-label">Last Name :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputLname" placeholder="Last Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputEmail" class="col-sm-3 control-label">Email :</label>
                                        <div class="col-sm-9">
                                            <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPhone" class="col-sm-3 control-label">Phone :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputPhone" placeholder="Phone">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputFax" class="col-sm-3 control-label">Fax :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputFax" placeholder="Fax">
                                        </div>
                                    </div>
                                <!-- Personal Information Ends -->
                                <!-- Delivery Information Starts -->
                                    <div class="form-group">
                                        <label for="inputCity" class="col-sm-3 control-label">City :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputCity" placeholder="City">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPostCode" class="col-sm-3 control-label">Postal Code :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputPostCode" placeholder="Postal Code">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputCountry" class="col-sm-3 control-label">Country :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputCountry" placeholder="Country">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputRegion" class="col-sm-3 control-label">Region :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputRegion" placeholder="Region">
                                        </div>
                                    </div>
                                <!-- Delivery Information Ends -->
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="submit" class="btn btn-danger">
                                            Search
                                        </button>
                                    </div>
                                </div>
                            <!-- Payment Area Ends -->
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><i class="fa fa-money fa-fw"></i> Customer Search Results</h3>
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                            <tr>
                                                <th>Customer #</th>
                                                <th>Email ID</th>
                                                <th>First Name</th>
                                                <th>Last Name</th>
                                                <th>Phone</th>
                                                <th>Fax</th>
                                                <th>Address/ 1</th>
                                                <th>Address/2</th>
                                                <th>City</th>
                                                <th>Postal Code</th>
                                                <th>Country</th>
                                                <th>Region</th>
                                                <th>Credit Card Number</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                            <tr>
                                                <td>Customer #</td>
                                                <td>Email ID</td>
                                                <td>First Name</td>
                                                <td>Last Name</td>
                                                <td>Phone</td>
                                                <td>Fax</td>
                                                <td>Address/ 1</td>
                                                <td>Address/2</td>
                                                <td>City</td>
                                                <td>Postal Code</td>
                                                <td>Country</td>
                                                <td>Region</td>
                                                <td>Credit Card Number</td>
                                            </tr>
                                        </tbody>
                                    </table>
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

