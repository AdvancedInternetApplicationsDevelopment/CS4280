<%-- 
    Document   : accountdetails
    Created on : Apr 4, 2016, 9:50:21 PM
    Author     : suhag
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>

<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <!-- Primary Content Starts -->
        <c:choose>
            <c:when test="${customerDetails==null}">
                <div class="col-md-12">
                    <p>Customer not found</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-12">
                    <div class="product-info-box">
                        <h4 class="heading">Personal Information</h4>
                        <div class="content panel-smart" style="font-weight: 700;">
                            <ul class="list-unstyled manufacturer">
                                <li>
                                    <span>First Name:</span> ${customerDetails.fname}
                                </li>

                                <li><span>Last Name:</span> ${customerDetails.lname}</li>
                                <li><span>Email:</span>${customerDetails.email}</li>
                                <li><span>Phone:</span>${customerDetails.phone}</li>
                                <li><span>Fax:</span>${customerDetails.fax}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="product-info-box">
                        <h4 class="heading">Delivery Information</h4>
                        <div class="content panel-smart" style="font-weight: 700;">
                            <ul class="list-unstyled manufacturer">
                                <li>
                                    <span>Address/1:</span> ${customerDetails.address1}
                                </li>

                                <li><span>Address/2:</span> ${customerDetails.address2}</li>
                                <li><span>city:</span>${customerDetails.city}</li>
                                <li><span>Postal Code:</span>${customerDetails.postal}</li>
                                <li><span>Country:</span>${customerDetails.country}</li>
                                <li><span>Region:</span>${customerDetails.region}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="product-info-box">
                        <h4 class="heading">Payment Information</h4>
                        <div class="content panel-smart" style="font-weight: 700;">
                            <ul class="list-unstyled manufacturer">
                                <li>
                                    <span>Credits Accumulated:</span> ${customerDetails.credits}
                                </li>

                                <li><span>Card number:</span>  ...... ${ fn:substring(customerDetails.ccNumber, fn:length(customerDetails.ccNumber)-4, fn:length(customerDetails.ccNumber))}</li>
                            </ul>
                        </div>
                    </div>
                    <br/>
                    <div class="options pull-right">
                        <div class="cart-button button-group">
                            <form action="/ToyStore/editPassword" method="post" style="display: inline-flex;">

                                <button type="submit" class="btn btn-danger">
                                    Change password
                                </button>
                            </form>

                            <form action="/ToyStore/editCustomer" method="post" style="display: inline-flex;">

                                <button type="submit" class="btn btn-danger">
                                    Edit Customer Details
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>

