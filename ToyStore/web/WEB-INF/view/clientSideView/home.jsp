<%-- 
    Document   : home
    Created on : Apr 21, 2016, 12:23:41 AM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->

<!-- Slider Section Starts -->
<div class="slider">
    <div id="main-carousel" class="carousel slide" data-ride="carousel">
        <!-- Wrapper For Slides Starts -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="images/slider-imgs/slide1-img.jpg" alt="Slider" class="img-responsive" />
            </div>
            <div class="item">
                <img src="images/slider-imgs/slide2-img.jpg" alt="Slider" class="img-responsive" />
            </div>
            <div class="item">
                <img src="images/slider-imgs/slide3-img.jpg" alt="Slider" class="img-responsive" />
            </div>
            <div class="item">
                <img src="images/slider-imgs/slide4-img.jpg" alt="Slider" class="img-responsive" />
            </div>
        </div>
        <!-- Wrapper For Slides Ends -->
        <!-- Controls Starts -->
        <a class="left carousel-control" href="#main-carousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
        </a>
        <a class="right carousel-control" href="#main-carousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
        </a>
        <!-- Controls Ends -->
    </div>
</div>
<!-- Slider Section Ends -->
<!-- 2 Column Banners Starts -->
<div class="col2-banners">
    <ul class="row list-unstyled">
        <li class="col-sm-8">
            <img src="images/banners/2col-banner1.jpg" alt="banners" class="img-responsive" />
        </li>
        <li class="col-sm-4">
            <img src="images/banners/2col-banner2.jpg" alt="banners" class="img-responsive" />
        </li>
    </ul>
</div>
<!-- 2 Column Banners Ends -->
<!-- Latest Products Starts -->
<section class="product-carousel">
    <!-- Heading Starts -->
    <h2 class="product-head"><fmt:message key='typeLatest'/></h2>
    <!-- Heading Ends -->
    <!-- Products Row Starts -->
    <div class="row">
        <div class="col-xs-12">
            <c:choose>
                <c:when test="${latestProducts[0]==null}">
                    <p>No products to display</p>
                </c:when>
                <c:otherwise>
                    <!-- Product Carousel Starts -->
                    <div id="owl-product" class="owl-carousel">
                        <c:forEach items="${latestProducts}" var="item">
                            <div class="item">
                                <div class="product-col">
                                    <div class="image">
                                        <img src="/ToyStore/getImage?productId=${item.id}" alt="product" class="img-responsive" />
                                    </div>
                                    <div class="caption">
                                        <h4><a href="/ToyStore/productDetails?productId=${item.id}">${item.name}</a></h4>
                                        <div class="price">
                                            <span class="price-new">$ ${item.price}</span>
                                        </div>
                                        <div class="cart-button button-group">
                                            <form action="/ToyStore/addWishList" method="post" style="
                                                  display: inline-flex;
                                                  ">
                                                <input type="hidden"
                                                       name="productId"
                                                       value="${item.id}">
                                                <button type="submit" title="Wishlist" class="btn btn-wishlist">
                                                    <i class="fa fa-heart"></i>
                                                </button>
                                            </form>


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
                    </div>
                    <!-- Product Carousel Ends -->
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!-- Products Row Ends -->
</section>
<!-- Latest Products Ends -->
<!-- 3 Column Banners Starts -->
<div class="col3-banners">
    <ul class="row list-unstyled">
        <li class="col-sm-4">
            <img src="images/banners/3col-banner1.jpg" alt="banners" class="img-responsive" />
        </li>
        <li class="col-sm-4">
            <img src="images/banners/3col-banner2.jpg" alt="banners" class="img-responsive" />
        </li>
        <li class="col-sm-4">
            <img src="images/banners/3col-banner3.jpg" alt="banners" class="img-responsive" />
        </li>
    </ul>
</div>
<!-- 3 Column Banners Ends -->
<!-- Featured Products Starts -->
<section class="product-list">
    <!-- Heading Starts -->
    <h2 class="product-head"> <fmt:message key='typeRecycled'/></h2>
    <!-- Heading Ends -->
    <!-- Products Row Starts -->
    <div class="row">
        <div class="col-xs-12">
            <c:choose>
                <c:when test="${latestRecycledProducts[0]==null}">
                    <p>No products to display</p>
                </c:when>
                <c:otherwise>
                    <!-- Product Carousel Starts -->
                        <c:forEach items="${latestRecycledProducts}" var="item2">
                            <div class="col-md-3 col-sm-6">
                                <div class="product-col">
                                    <div class="image">
                                        <img src="/ToyStore/getImage?productId=${item2.id}" alt="product" class="img-responsive" />
                                    </div>
                                    <div class="caption">
                                        <h4><a href="/ToyStore/productDetails?productId=${item2.id}">${item2.name}</a></h4>
                                        <div class="price">
                                            <span class="price-new">$ ${item2.price}</span>
                                        </div>
                                        <div class="cart-button button-group">
                                            <form action="/ToyStore/addWishList" method="post" style="
                                                  display: inline-flex;
                                                  ">
                                                <input type="hidden"
                                                       name="productId"
                                                       value="${item2.id}">
                                                <button type="submit" title="Wishlist" class="btn btn-wishlist">
                                                    <i class="fa fa-heart"></i>
                                                </button>
                                            </form>


                                            <form action="/ToyStore/addCompareList" method="post" style="
                                                  display: inline-flex;
                                                  ">
                                                <input type="hidden"
                                                       name="productId"
                                                       value="${item2.id}">
                                                <button type="submit" title="Compare" class="btn btn-compare">
                                                    <i class="fa fa-bar-chart-o"></i>
                                                </button>
                                            </form>

                                            <form action="/ToyStore/addCartList" method="post" style="
                                                  display: inline-flex;
                                                  ">
                                                <input type="hidden"
                                                       name="productId"
                                                       value="${item2.id}">
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
                    <!-- Product Carousel Ends -->
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!-- Products Row Ends -->
</section>
<!-- Latest Products Ends -->


<%@include file="common/footer.jsp" %>
<link href="css/owl.carousel.css" rel="stylesheet">
<script src="js/custom.js"></script>

