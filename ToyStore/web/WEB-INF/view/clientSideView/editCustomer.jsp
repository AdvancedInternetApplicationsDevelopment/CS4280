<%-- 
    Document   : editCustomer
    Created on : Apr 23, 2016, 6:20:22 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>

<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <div class="col-sm-12">
            <!-- Registration Block Starts -->
            <div class="panel panel-smart">
                <div class="panel-heading">
                    <h3 class="panel-title">Personal Information</h3>
                </div>
                <div class="panel-body">
                    <!-- Registration Form Starts -->
                    <form class="form-horizontal" role="form" id="registerationForm" novalidate='novalidate' action="/ToyStore/editCustomer" method="post">
                        <!-- Personal Information Starts -->
                        <div class="form-group">
                            <label for="inputFname" class="col-sm-3 control-label">First Name :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputFname" placeholder="First Name" name="fname" value="${fname}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputLname" class="col-sm-3 control-label">Last Name :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputLname" placeholder="Last Name" name="lname" value="${lname}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPhone" class="col-sm-3 control-label">Phone :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputPhone" placeholder="Phone" name="phone" value="${phone}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputFax" class="col-sm-3 control-label">Fax :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputFax" placeholder="Fax" name="fax" value="${fax}">
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
                                <input type="text" class="form-control" id="inputAddress1" placeholder="Address/1" name="addressL1" value="${addressL1}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAddress2" class="col-sm-3 control-label">Address/2 :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputAddress2" placeholder="Address/2" name="addressL2" value="${addressL2}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputCity" class="col-sm-3 control-label">City :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputCity" placeholder="City" name="city" value="${city}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPostCode" class="col-sm-3 control-label">Postal Code :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputPostCode" placeholder="Postal Code" name="postal" value="${postal}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputCountry" class="col-sm-3 control-label">Country :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputCountry" placeholder="Country" name="country" value="${country}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputRegion" class="col-sm-3 control-label">Region :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputRegion" placeholder="Region" name="region" value="${region}">
                            </div>
                        </div>
                        <h3 class="panel-heading inner">
                            Payment
                        </h3>
                        <!-- Payment Area Starts -->
                        <div class="form-group">
                            <label for="inputCardHolderName" class="col-sm-3 control-label">Card Holder :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputCardHolderName" placeholder="Credit Card Holder Name" name="cardHolder" value="${cardHolder}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputCreditCardNumber" class="col-sm-3 control-label">Credit Card Number :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputCreditCardNumber" placeholder="Credit Card Number" name="ccNumber" value="${ccNumber}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputExpiryDate" class="col-sm-3 control-label">Expiry Date :</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="inputExpiryDate" placeholder="mm/yy" name="expiryDate" value="${expiryDate}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputCCV" class="col-sm-3 control-label">CCV :</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="inputCCV" placeholder="CCV" name="ccv" value="${ccv}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9 pull-right">
                                <button type="submit" class="btn btn-danger">
                                    Update 
                                </button>
                            </div>
                        </div>
                        <!-- Payment Area Ends -->
                    </form>
                    <c:if test="${success == true}">
                        <div class="alert alert-success">
                            <p> Add product Successful </p>
                        </div> 
                    </c:if>
                    <c:if test="${error ==true}">
                        <div class="alert alert-danger">
                            <p> Error in adding product. Error message: ${errorMessage} </p>
                        </div> 
                    </c:if>
                </div>
            </div>
            <!-- Registration Block Ends -->
        </div>
        <!-- Main Container Ends -->
    </div>
</div>
<%@include file="common/footer.jsp" %>