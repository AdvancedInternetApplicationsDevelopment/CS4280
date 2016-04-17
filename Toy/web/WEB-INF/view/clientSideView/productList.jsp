<%-- 
    Document   : productList
    Created on : Apr 4, 2016, 8:16:14 PM
    Author     : suhag
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <!-- Primary Content Starts -->
        <div class="col-md-9">
            <!-- Product List Display Starts -->
            <div class="row">
                <!-- Product #1 Starts -->
                <div class="col-xs-12">
                    <c:choose>
                        <c:when test ="${products[0]== null}">
                            No products to display 
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${products}" var="item">
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
                                        <div class="cart-button button-group col-md-3">
                                            <div class="col-lg-4">
                                                <form action="/ToyStore/addWishList" method="post">
                                                    <input type="hidden"
                                                           name="productId"
                                                           value="${product.id}">
                                                    <button type="button" title="Wishlist" class="btn btn-wishlist">
                                                        <i class="fa fa-heart"></i>
                                                    </button>
                                                </form>
                                            </div>
                                            <div class="col-lg-4">
                                                <form action="/ToyStore/addCompareList" method="post">
                                                    <input type="hidden"
                                                           name="productId"
                                                           value="${product.id}">
                                                    <button type="button" title="Compare" class="btn btn-compare">
                                                        <i class="fa fa-bar-chart-o"></i>
                                                    </button>
                                                </form>
                                            </div>
                                            <div class="col-lg-4"
                                                 <form action="/ToyStore/addCartList" method="post">
                                                    <input type="hidden"
                                                           name="productId"
                                                           value="${product.id}">
                                                    <button type="button" class="btn btn-cart">
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
            </div>
            <!-- Product List Display Ends -->
        </div>
        <!-- Primary Content Ends -->
        <!-- Sidebar Starts -->
        <div class="col-md-3">
            <!-- Shopping Options Starts -->
            <h3 class="side-heading">Shopping Options</h3>
            <div class="list-group">
                <form action="/ToyStore/productList" method="post" role="form">
                <div class="list-group-item">
                    Brands
                </div>
                <div class="list-group-item">
                    <div class="filter-group">
                         <c:choose>
                             <c:when test="${brands[0]==null && brandsChecked[0]==null}">
                                 No brands in database
                             </c:when>
                             <c:otherwise>
                                 <c:forEach items="${brandsChecked}" var="item">
                                     <label class="checkbox">
                                         <input name="brand" type="checkbox" value="${item}" checked="checked" />
                                     ${item}
                                     </label> 
                                 </c:forEach>
                                 <c:forEach items="${brands}" var="item">
                                     <label class="checkbox">
                                         <input name="brand" type="checkbox" value="${item}" />
                                     ${item}
                                     </label> 
                                 </c:forEach>
                             </c:otherwise>

                         </c:choose>
                    </div>
                </div>
                <div class="list-group-item">
                    Categories
                </div>
                <div class="list-group-item">
                    <div class="filter-group">
                         <c:choose>
                             <c:when test="${categoriesChecked[0]==null && categoriesUnChecked[0]==null}">
                                 No brands in database
                             </c:when>
                             <c:otherwise>
                                 <c:forEach items="${categoriesUnChecked}" var="item">
                                     <label class="checkbox">
                                         <input name="category" type="checkbox" value="${item.id}" />
                                     ${item.name}
                                     </label> 
                                 </c:forEach>
                                 <c:forEach items="${categoriesChecked}" var="item">
                                     <label class="checkbox">
                                         <input name="category" type="checkbox" value="${item.id}" checked="checked"/>
                                     ${item.name}
                                     </label> 
                                 </c:forEach>
                             </c:otherwise>

                         </c:choose>
                    </div>
                </div>
                <div class="list-group-item">
                    <button type="submit" class="btn btn-danger" value="/ToyStore/productList">Filter</button>
                </div>
                </form>
            </div>
            <!-- Shopping Options Ends -->
        </div>
        <!-- Sidebar Ends -->
    </div>
</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>