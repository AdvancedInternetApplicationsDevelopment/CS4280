<%-- 
    Document   : register
    Created on : Apr 3, 2016, 3:27:48 PM
    Author     : suhag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <!--[if IE]>
                <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <![endif]-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Register</title>

        <!-- Bootstrap Core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-1.11.1.min.js"></script>

        <!-- Google Web Fonts -->
        <link href="http://fonts.googleapis.com/css?family=Roboto+Condensed:300italic,400italic,700italic,400,300,700" rel="stylesheet" type="text/css">
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

        <!-- CSS Files -->
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <link href="css/responsive.css" rel="stylesheet">


        <script src="js/jquery-migrate-1.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-hover-dropdown.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/custom.js"></script>
        <script src="js/formValidation.js"></script>
        <link href="css/formValidation.css" rel="stylesheet">
        <script type="text/javascript" src="js/framework/bootstrap.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#registerationForm')
                        .formValidation({
                            icon: {
                                valid: 'fa fa-check',
                                invalid: 'fa fa-times',
                                validating: 'fa fa-refresh'
                            },
                            fields: {
                                cardHolder: {
                                    selector: '#inputCardHolderName',
                                    validators: {
                                        notEmpty: {
                                            message: 'The card holder is required'
                                        },
                                        stringCase: {
                                            message: 'The card holder must contain upper case characters only',
                                            case: 'upper'
                                        }
                                    }
                                },
                                creditCardNumber: {
                                    selector: '#inputCreditCardNumber',
                                    validators: {
                                        notEmpty: {
                                            message: 'The credit card number is required'
                                        },
                                        creditCard: {
                                            message: 'The credit card number is not valid'
                                        }
                                    }
                                },
                                agreeBox: {
                                    validators: {
                                        notEmpty: {
                                            message: 'You have to accept the terms and policies'
                                        }
                                    }
                                },
                                email: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The email address is required and can\'t be empty'
                                        },
                                        emailAddress: {
                                            message: 'The input is not a valid email address'
                                        }
                                    }
                                },
                                password: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The password is required and can\'t be empty'
                                        }
                                    }
                                },
                                rePassword: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The confirm password is required and can\'t be empty'
                                        },
                                        identical: {
                                            field: 'password',
                                            message: 'The password and its confirm are not the same'
                                        }
                                    }
                                },
                                country: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The country is required and can\'t be empty'
                                        }
                                    }
                                },
                                city: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The city is required and can\'t be empty'
                                        }
                                    }
                                },
                                region: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The region is required and can\'t be empty'
                                        }
                                    }
                                },
                                addressL1: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The Address line 1 is required and can\'t be empty'
                                        }
                                    }
                                },
                                addressL2: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The Address line 2 is required and can\'t be empty'
                                        }
                                    }
                                },
                                fax: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The fax is required and can\'t be empty'
                                        }
                                    }
                                },
                                phoneNumber: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The phone number is required and can\'t be empty'
                                        }
                                    }
                                },
                                postalCode: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The postal code is required and can\'t be empty'
                                        }
                                    }
                                },
                                firstName: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The First name is required and can\'t be empty'
                                        }
                                    }
                                },
                                lastName: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The last name is required and can\'t be empty'
                                        }
                                    }
                                },
                                expiryDate: {
                                    validators: {
                                        notEmpty: {
                                            message: 'The mm/yy is required and can\'t be empty'
                                        }
                                    }
                                },
                                cvvNumber: {
                                    selector: '#inputCCV',
                                    validators: {
                                        notEmpty: {
                                            message: 'The CVV number is required'
                                        },
                                        cvv: {
                                            message: 'The value is not a valid CVV',
                                            creditCardField: 'ccNumber'
                                        }
                                    }
                                }
                            }
                        })
                        .on('success.validator.fv', function (e, data) {
                            if (data.field === 'ccNumber' && data.validator === 'creditCard') {
                                var $icon = data.element.data('fv.icon');
                                switch (data.result.type) {
                                    case 'AMERICAN_EXPRESS':
                                        $icon.removeClass().addClass('form-control-feedback fa fa-cc-amex');
                                        break;
                                    case 'DISCOVER':
                                        $icon.removeClass().addClass('form-control-feedback fa fa-cc-discover');
                                        break;
                                    case 'MASTERCARD':
                                        $icon.removeClass().addClass('form-control-feedback fa fa-cc-mastercard');
                                        break;
                                    case 'VISA':
                                        $icon.removeClass().addClass('form-control-feedback fa fa-cc-visa');
                                        break;
                                    default:
                                        $icon.removeClass().addClass('form-control-feedback fa fa-credit-card');
                                        break;
                                }
                            }
                        })
                        .on('err.field.fv', function (e, data) {
                            if (data.field === 'ccNumber') {
                                var $icon = data.element.data('fv.icon');
                                $icon.removeClass().addClass('form-control-feedback fa fa-times');
                            }
                        });
            });
        </script>

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
                            <a href="index.html"><img src="images/logo.png" title="Toy Store" alt="Toy Store" class="img-responsive" /></a>
                        </div>
                    </div>
                </div>
                <!-- Logo Ends -->
                <!-- Main Container Starts -->
                <div id="main-container">
                    <!-- Main Heading Starts -->
                    <h2 class="main-heading text-center">
                        Register <br />
                        <span>Create New Account</span>
                    </h2>
                    <!-- Main Heading Ends -->
                    <!-- Registration Section Starts -->
                    <section class="registration-area">
                        <div class="row">
                            <div class="col-sm-6">
                                <!-- Registration Block Starts -->
                                <div class="panel panel-smart">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Personal Information</h3>
                                    </div>
                                    <div class="panel-body">
                                        <!-- Registration Form Starts -->
                                        <form class="form-horizontal" role="form" id="registerationForm" novalidate='novalidate'>
                                            <!-- Personal Information Starts -->
                                            <div class="form-group">
                                                <label for="inputFname" class="col-sm-3 control-label">First Name :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputFname" placeholder="First Name" name="firstName">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputLname" class="col-sm-3 control-label">Last Name :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputLname" placeholder="Last Name" name="lastName">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputEmail" class="col-sm-3 control-label">Email :</label>
                                                <div class="col-sm-9">
                                                    <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="email">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputPhone" class="col-sm-3 control-label">Phone :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputPhone" placeholder="Phone" name="phoneNumber">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputFax" class="col-sm-3 control-label">Fax :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputFax" placeholder="Fax" name="fax">
                                                </div>
                                            </div>
                                            <!-- Personal Information Ends -->
                                            <h3 class="panel-heading inner">
                                                Delivery Information
                                            </h3>
                                            <!-- Delivery Information Starts -->
                                            <div class="form-group">
                                                <label for="inputAddress1" class="col-sm-3 control-label">Address/1 :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputAddress1" placeholder="Address/1" name="addressL1">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputAddress2" class="col-sm-3 control-label">Address/2 :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputAddress2" placeholder="Address/2" name="addressL2">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputCity" class="col-sm-3 control-label">City :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputCity" placeholder="City" name="city">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputPostCode" class="col-sm-3 control-label">Postal Code :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputPostCode" placeholder="Postal Code" name="postalCode">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputCountry" class="col-sm-3 control-label">Country :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputCountry" placeholder="Country" name="country">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputRegion" class="col-sm-3 control-label">Region :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputRegion" placeholder="Region" name="region">
                                                </div>
                                            </div>
                                            <!-- Delivery Information Ends -->
                                            <h3 class="panel-heading inner">
                                                Password
                                            </h3>
                                            <!-- Password Area Starts -->
                                            <div class="form-group">
                                                <label for="inputPassword" class="col-sm-3 control-label">Password :</label>
                                                <div class="col-sm-9">
                                                    <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputRePassword" class="col-sm-3 control-label">Re-Password :</label>
                                                <div class="col-sm-9">
                                                    <input type="password" class="form-control" id="inputRePassword" placeholder="Re-Password" name="rePassword">
                                                </div>
                                            </div>
                                            <!-- Password Area Ends -->

                                            <h3 class="panel-heading inner">
                                                Payment
                                            </h3>
                                            <!-- Payment Area Starts -->
                                            <div class="form-group">
                                                <label for="inputCardHolderName" class="col-sm-3 control-label">Card Holder :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputCardHolderName" placeholder="Credit Card Holder Name" name="cardHolder">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputCreditCardNumber" class="col-sm-3 control-label">Credit Card Number :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputCreditCardNumber" placeholder="Credit Card Number" name="creditCardNumber">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputExpiryDate" class="col-sm-3 control-label">Expiry Date :</label>
                                                <div class="col-sm-9">
                                                    <input type="text" class="form-control" id="inputExpiryDate" placeholder="mm/yy" name="expiryDate">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="inputCCV" class="col-sm-3 control-label">CCV :</label>
                                                <div class="col-sm-9">
                                                    <input type="password" class="form-control" id="inputCCV" placeholder="CCV" name="cvvNumber">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                    <div class="checkbox">
                                                        <label>
                                                            <input type="checkbox" name="agreeBox"> I'v read and agreed on Conditions
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                    <button  disabled ="disabled" type="submit" class="btn btn-danger disabled">
                                                        Register
                                                    </button>
                                                </div>
                                            </div>
                                            <!-- Payment Area Ends -->
                                        </form>
                                    </div>
                                </div>
                                <!-- Registration Block Ends -->
                            </div>
                            <div class="col-sm-6">
                                <!-- Conditions Panel Starts -->
                                <div class="panel panel-smart">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">
                                            Conditions
                                        </h3>
                                    </div>
                                    <div class="panel-body">
                                        <p>
                                            Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including  versions of Lorem Ipsum.
                                        </p>
                                        <ol>
                                            <li>Lorem ipsum dolor sit amet</li>
                                            <li>Consectetur adipiscing elit</li>
                                            <li>Integer molestie lorem at massa</li>
                                            <li>Facilisis in pretium nisl aliquet</li>
                                            <li>Nulla volutpat aliquam velit</li>
                                            <li>Faucibus porta lacus fringilla vel</li>
                                            <li>Aenean sit amet erat nunc</li>
                                            <li>Eget porttitor lorem</li>
                                        </ol>
                                        <p>
                                            HTML Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris.
                                        </p>
                                        <p>
                                            Integer in mauris eu nibh euismod gravida. Duis ac tellus et risus vulputate vehicula. Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros est euismod turpis, id tincidunt sapien risus a quam. Maecenas fermentum consequat mi. Donec fermentum. Pellentesque malesuada nulla a mi. Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque.
                                        </p>
                                        <p>
                                            Aliquam faucibus, elit ut dictum aliquet, felis nisl adipiscing sapien, sed malesuada diam lacus eget erat. Cras mollis scelerisque nunc. Nullam arcu. Aliquam consequat. Curabitur augue lorem, dapibus quis, laoreet et, pretium ac, nisi.
                                        </p>
                                        <p>
                                            Aenean magna nisl, mollis quis, molestie eu, feugiat in, orci. In hac habitasse platea dictumst.
                                        </p>
                                    </div>
                                </div>
                                <!-- Conditions Panel Ends -->
                            </div>
                            <div class="col-sm-6">
                                <div class="panel-smart">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">
                                            LOGIN 
                                        </h3>
                                    </div>
                                    <div class="panel-body">
                                        <a href="/ToyStore/login">Click here for login page</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                    <!-- Registration Section Ends -->
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
