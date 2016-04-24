<%-- 
    Document   : cart
    Created on : Apr 4, 2016, 7:21:16 PM
    Author     : suhag
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <!-- Main Heading Starts -->
    <h2 class="main-heading text-center">
        Shopping Cart
    </h2>
    <!-- Main Heading Ends -->
    <!-- Shopping Cart Table Starts -->
    <c:choose>
        <c:when test="${sessionScope.cart.items[0]==null}">
            <div><p>No products added to cart</p></div>
        </c:when>
        <c:otherwise>
            <div class="table-responsive shopping-cart-table">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <td class="text-center">
                                Image
                            </td>
                            <td class="text-center">
                                Product Details
                            </td>
                            <td class="text-center">
                                Price per unit
                            </td>
                            <td class="text-center">
                                Total
                            </td>
                            <td class="text-center">
                                Quantity
                            </td>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sessionScope.cart.items}" var="item">
                        <tr>
                            <td class="text-center">
                                <a href="product.html">
                                    <img src="/ToyStore/getImage?productId=${item.product.id}" alt="Product Name" title="Product Name" class="img-thumbnail" />
                                </a>
                            </td>
                            <td class="text-center">
                                <a href="/ToyStore/productDetails?productId=${item.product.id}">${item.product.name}</a>
                            </td>
                            <td class="text-center">
                                $ ${item.product.price}
                            </td>
                            <td class="text-center">
                                $ ${item.total}
                            </td>
                            <td class="text-center">
                                <form action="/ToyStore/updateCart" method="post">
                                    <div class="input-group btn-block">
                                        <input type="text" name="quantity" value="${item.quantity}" size="1" class="form-control"  />
                                    </div>
                                    <input type="hidden" name="productId" value="${item.product.id}">
                                    <button type="submit" title="Update" class="btn btn-default tool-tip">
                                        <i class="fa fa-refresh"></i>
                                    </button>

                                </form>
                                <form action="/ToyStore/removeCartItem" method="post">
                                    <input type="hidden" name="productId" value="${item.product.id}"> 
                                    <button type="submit" title="Remove" class="btn btn-default tool-tip">
                                        <i class="fa fa-times-circle"></i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <!-- Shopping Cart Table Ends -->
            <!-- Shipping Section Starts -->
            <section class="registration-area">
                <div class="row">
                    <!-- Discount & Conditions Blocks Starts -->
                    <div class="col-sm-12">
                        <!-- Conditions Panel Starts -->
                        <div class="panel panel-smart">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Terms &amp; Conditions
                                </h3>
                            </div>
                            <div class="panel-body">
                                <p>
                                    HTML Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris. Integer in mauris eu nibh euismod gravida. Duis ac tellus et risus vulputate vehicula.
                                </p>
                                <p>
                                    Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros est euismod turpis, id tincidunt sapien risus a quam. Maecenas fermentum consequat mi. Donec fermentum. Pellentesque malesuada nulla a mi.
                                </p>
                                <p>
                                    Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque. Aliquam faucibus, elit ut dictum aliquet, felis nisl adipiscing sapien, sed malesuada diam lacus eget erat. Cras mollis scelerisque nunc. Nullam arcu. Aliquam consequat.
                                </p>
                            </div>
                        </div>
                        <!-- Conditions Panel Ends -->
                        <!-- Discount Coupon Block Starts -->
                        <div class="panel panel-smart">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Discount and Credit use
                                </h3>
                            </div>
                            <div class="panel-body">
                                <!-- Form Starts -->
                                <form class="form-horizontal" role="form" action="/ToyStore/updateCoupon" method="post">
                                    <div class="form-group">
                                        <label for="inputCouponCode" class="col-sm-3 control-label">Coupon Code :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputCouponCode" name="discount" placeholder="Coupon Code" value="${sessionScope.cart.discount}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputCouponCode" class="col-sm-3 control-label">Number of Credits to use :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputCouponCode"  name="credit"placeholder="Credits" value="${sessionScope.cart.credit}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <button type="submit" class="btn btn-danger">
                                                Apply 
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <!-- Form Ends -->
                            </div>
                        </div>
                        <!-- Discount Coupon Block Ends -->

                        <!-- Total Panel Starts -->
                        <div class="panel panel-smart">
                            <div class="panel-heading">
                                <h3 class="panel-title">
                                    Total
                                </h3>
                            </div>
                            <div class="panel-body">
                                <dl class="dl-horizontal">
                                    <dt>credit used :</dt>
                                    <dd>$ ${sessionScope.cart.credit}</dd>
                                    <dt>Total :</dt>
                                    <dd>$ ${sessionScope.cart.total}</dd>
                                </dl>
                                <hr />
                                <dl class="dl-horizontal total">
                                    <dt>Total :</dt>
                                    <dd>$ ${sessionScope.cart.total}</dd>
                                </dl>
                                <hr />
                                <div class="text-uppercase clearfix">
                                    <a href="/ToyStore/cart?clear=true" class="btn btn-danger pull-left">
                                        Clear cart
                                    </a>
                                    <a href="/ToyStore/checkout" class="btn btn-danger pull-right">
                                        Checkout
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- Total Panel Ends -->
                        <c:if test="${error ==true}">
                            <!-- Conditions Panel Starts -->
                        <div class="panel panel-smart">
                            <div class="panel-heading">
                                <h3 class="panel-title" style="color:red;">
                                    Error
                                </h3>
                            </div>
                            <div class="panel-body">
                                <p>
                                    ${errorMessage}
                                </p>
                            </div>
                        </div>
                        <!-- Conditions Panel Ends -->
                        </c:if>
                        
                    </div>
                    <!-- Discount & Conditions Blocks Ends -->
                </div>
            </section>
            <!-- Shipping Section Ends -->
        </c:otherwise>
    </c:choose>

</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>