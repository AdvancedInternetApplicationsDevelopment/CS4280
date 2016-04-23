<%-- 
    Document   : wishList
    Created on : Apr 4, 2016, 8:16:14 PM
    Author     : suhag
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <!-- Main Heading Starts -->
    <h2 class="main-heading text-center">
        Wish List
    </h2>
    <!-- Main Heading Ends -->
    <div class="row">
        <!-- Primary Content Starts -->
        <div class="col-md-12">
            <!-- Product List Display Starts -->
            <div class="row">
                <c:choose>
                    <c:when test="${wishList[0]==null}">
                        <div><p>No products added to compare list</p></div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${wishList}" var="item">
                            <div class="col-xs-12">
                                <div class="product-col list clearfix">
                                    <div class="image">
                                        <img src="/ToyStore/getImage?productId=${item.id}" alt="product" class="img-responsive" />
                                    </div>
                                    <div class="caption">
                                        <h4><a href="/ToyStore/productDetails?productId=${item.id}">${item.name}</a></h4>
                                        <div class="description">
                                            ${item.description}
                                        </div>
                                        <div class="price">
                                            <span class="price-new">$ ${item.price}</span>
                                        </div>
                                        <div class="cart-button button-group">
                                            <form action="/ToyStore/addCompareList" method="post" style="
                                                  display: inline-flex;
                                                  ">
                                                <input type="hidden"
                                                       name="productId"
                                                       value="${item.id}">
                                                <button type="submit" title="Compare" class="btn btn-compare">
                                                    <i class="fa fa-bar-chart-o"></i>
                                                </button>
                                            </form>

                                            <form action="/ToyStore/addCartList" method="post" style="
                                                  display: inline-flex;
                                                  ">
                                                <input type="hidden"
                                                       name="productId"
                                                       value="${item.id}">
                                                <button type="submit" class="btn btn-cart">
                                                    Add to cart
                                                    <i class="fa fa-shopping-cart"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </div>
            <!-- Product List Display Ends -->
        </div>
        <!-- Primary Content Ends -->

    </div>
</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>