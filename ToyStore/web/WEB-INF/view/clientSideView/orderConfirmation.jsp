<%-- 
    Document   : orderConfirmation
    Created on : Apr 5, 2016, 4:29:42 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <h2 class="main-heading text-center">
        Order Confirmation<br/>
    </h2>
    <div class="row">
        <!-- Primary Content Starts -->
        <div class="col-md-12">
            <!-- Product List Display Starts -->
            <c:choose>
                <c:when test="${orderHistory[0]==null}">
                    <p>No transactions to display</p>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${orderHistory}" var="item">
                        <div class="panel panel-smart">
                            <div class="panel-heading">
                                <h3 class="panel-title" style="color:red;">
                                    Receipt Number: # ${item.id}
                                </h3>
                            </div>
                            <div class="panel-body">
                                <table class="table borderless">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Product Name</th>
                                            <th>Quantity</th>
                                            <th>Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${item.orderedProducts}" var="oP">
                                            <tr>
                                                <td></td>
                                                <td>${oP.product.name}</td>
                                                <td>${oP.quantity}</td>
                                                <td>$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${oP.quantity * oP.product.price}" /></td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                                <dl class="dl-horizontal">
                                    <dt>Coupon Discount :</dt>
                                    <dd>$ - ${item.discount}</dd>
                                    <dt>Recycle Credit Used :</dt>
                                    <dd>$ ${item.credit}</dd>
                                </dl>
                                <hr />
                                <dl class="dl-horizontal total">
                                    <dt><h3 class="panel-title" style="color:red;">Total :</h3>

                                    </dt>
                                    <dd><h3 class="panel-title" style="color:red;">$ <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.amount}" /></h3></dd>
                                </dl>
                                <hr />
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <!-- Primary Content Ends -->
            <div class="text-uppercase clearfix">
                <a href="/ToyStore/productList" class="btn btn-danger pull-right">
                    <span class="hidden-xs">Continue Shopping</span>
                    <span class="visible-xs">Continue</span>
                </a>
            </div>
            <br/>
        </div>
    </div>
    <!-- Main Container Ends -->
    <%@include file="common/footer.jsp" %>
