<%-- 
    Document   : productDetails
    Created on : Apr 4, 2016, 3:06:24 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>

<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <!-- Primary Content Starts -->
        <c:choose>
            <c:when test="${product==null}">
                <div class="col-md-9">
                    <p>Product not found</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-9">
                    <!-- Product Info Starts -->
                    <div class="row product-info">
                        <!-- Left Starts -->
                        <div class="col-sm-5 images-block">
                            <p>
                                <img src="/ToyStore/getImage?productId=${product.id}" alt="Image" class="img-responsive thumbnail" />
                            </p>
                        </div>
                        <!-- Left Ends -->
                        <!-- Right Starts -->
                        <div class="col-sm-7 product-details">
                            <!-- Product Name Starts -->
                            <h2>${product.name}</h2>
                            <!-- Product Name Ends -->
                            <hr />
                            <!-- Manufacturer Starts -->
                            <ul class="list-unstyled manufacturer">
                                <li>
                                    <span>Brand:</span> ${product.brand}
                                </li>

                                <li><span>Product sold By:</span> ${product.owner}</li>
                                <li>
                                    <span>Availability:</span> 
                                    <c:choose>
                                        <c:when test="${product.quantity gt 0}">
                                            <strong class="label label-success">In Stock</strong>
                                        </c:when>
                                        <c:otherwise>
                                            <strong class="label label-danger">Out of Stock</strong>
                                        </c:otherwise>
                                    </c:choose>

                                </li>
                                <li>
                                    <span>Rating:</span>
                                    <span>
                                        <c:forEach begin="1" end="${ratingRecived}" var="val">
                                            <i class="fa fa-star" aria-hidden="true"></i>
                                        </c:forEach>
                                        <c:forEach begin="1" end="${ratingLeft}" var="val">
                                            <i class="fa fa-star-o" aria-hidden="true"></i>
                                        </c:forEach>
                                    </span>


                                </li>
                            </ul>
                            <!-- Manufacturer Ends -->
                            <hr />
                            <!-- Price Starts -->
                            <div class="price">
                                <span class="price-new">$ ${product.price}</span> 
                            </div>
                            <!-- Price Ends -->
                            <hr />
                            <!-- Available Options Starts -->
                            <div class="options">
                                <div class="cart-button button-group">

                                    <form action="/ToyStore/addWishList" method="post" style="display: inline-flex;">
                                        <input type="hidden"
                                               name="productId"
                                               value="${product.id}">
                                        <button type="submit" title="Wishlist" class="btn btn-wishlist">
                                            <i class="fa fa-heart"></i>
                                        </button>
                                    </form>

                                    <form action="/ToyStore/addCompareList" method="post" style="display: inline-flex;">
                                        <input type="hidden"
                                               name="productId"
                                               value="${product.id}">
                                        <button type="submit" title="Compare" class="btn btn-compare">
                                            <i class="fa fa-bar-chart-o"></i>
                                        </button>
                                    </form>

                                    <form action="/ToyStore/addCartList" method="post" style="display: inline-flex;">
                                        <input type="hidden"
                                               name="productId"
                                               value="${product.id}">
                                        <button type="submit" class="btn btn-cart">
                                            Add to cart
                                            <i class="fa fa-shopping-cart"></i>
                                        </button>
                                    </form>

                                </div>
                            </div>
                            <!-- Available Options Ends -->
                            <hr />
                        </div>
                        <!-- Right Ends -->
                    </div>
                    <!-- product Info Ends -->
                    <!-- Product Description Starts -->
                    <div class="product-info-box">
                        <h4 class="heading">Description</h4>
                        <div class="content panel-smart">
                            ${product.description}
                        </div>
                    </div>
                    <!-- Product Description Ends -->
                    <!-- Additional Information Starts -->
                    <div class="product-info-box">
                        <h4 class="heading">Additional Information</h4>
                        <div class="content panel-smart">
                            ${product.addInfo}
                        </div>
                    </div>
                    <!-- Additional Information Ends -->
                    <!-- Related Products Starts -->
                    <div class="product-info-box">
                        <h4 class="heading">Related Products</h4>
                        <c:choose>
                            <c:when test="${Relatedproducts[0]==null}">
                                <div class="col-md-9">
                                    <p>Related products not found</p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <c:forEach items="${Relatedproducts}" var="item">
                                        <div class="col-md-4 col-sm-6">
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
                            </c:otherwise>
                        </c:choose>
                        <!-- Products Row Starts -->

                        <!-- Products Row Ends -->
                    </div>
                    <!-- Related Products Ends -->
                    <div class="product-info-box">
                        <h4 class="heading">Product reviews</h4>
                        <div class="content panel-smart">
                            <c:choose>
                                <c:when test="${reviews[0] == null}">
                                    <div><p>No reviews to display</p></div>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${reviews}" var="item">
                                        
                                            <h4 class="heading">
                                                <span><strong>Customer Id :</strong></span>
                                                <span><strong>${item.customer.email}</strong></span>
                                            </h4>
                                            <div class="content panel-smart">

                                                <dl class="list-unstyled manufacturer">
                                                    <dt style="padding-left:4%;">
                                                        <span><strong><h4>Customer Review:</h4> </strong></span> 
                                                    </dt>
                                                    <dd style="padding-left:20%; font-size: 18px;">
                                                        <span><strong>${item.comments}</strong></span>
                                                    </dd>
                                                    <c:if test="${not empty item.adminReply}">
                                                    <dt style="padding-left:4%;">
                                                        <span><strong><h4>Admin Reply:</h4> </strong></span> 
                                                    </dt>
                                                    <dd style="padding-left:20%; font-size: 18px;">
                                                        <span style="p"><strong>${item.adminReply}</strong></span>
                                                    </dd>
                                                    </c:if>
                                                </dl>
                                            </div>
                                       
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="product-info-box">
                        <h4 class="heading">Review Product</h4>
                        <div class="content panel-smart">
                            <form class="form-horizontal" action="/ToyStore/productDetails" method="post">
                                <div class="form-group required">
                                    <label class="col-sm-2 control-label" for="input-review">Review</label>
                                    <div class="col-sm-10">
                                        <input type="hidden"
                                               name="productId"
                                               value="${product.id}">
                                        <textarea name="review" class="form-control" id="input-review" rows="5" ></textarea>
                                        <div class="help-block">
                                            Ask admin a question or write a review for the product..
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group required">
                                    <label class="col-sm-2 control-label ratings">Ratings</label>
                                    <div class="col-sm-10">
                                        Bad&nbsp;
                                        <input name="rating" type="radio" value="1">
                                        &nbsp;
                                        <input name="rating" type="radio" value="2">
                                        &nbsp;
                                        <input name="rating" type="radio" value="3">
                                        &nbsp;
                                        <input name="rating" type="radio" value="4">
                                        &nbsp;
                                        <input name="rating" type="radio" value="5">
                                        &nbsp;Good
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button class="btn btn-danger" id="button-review" type="submit" value="reviewProduct">
                                            Submit
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <c:if test="${success == true}">
                                <div class="alert alert-success">
                                    <p> Review Successful </p>
                                </div> 
                            </c:if>
                            <c:if test="${error ==true}">
                                <div class="alert alert-danger">
                                    <p> Error in review. Error message: ${errorMessage} </p>
                                </div> 
                            </c:if>
                        </div>
                    </div>


                </div>
            </c:otherwise>
        </c:choose>

        <!-- Primary Content Ends -->
        <!-- Sidebar Starts -->
        <div class="col-md-3">
            <!-- Bestsellers Links Starts -->
            <h3 class="side-heading">Bestsellers</h3>
            <c:choose>
                <c:when test="${bestProduct==null}">
                    <div class="col-md-9">
                        <p>Best seller not found</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="product-col">
                        <div class="image">
                            <img src="/ToyStore/getImage?productId=${bestProduct.id}" alt="product" class="img-responsive" />
                        </div>
                        <div class="caption">
                            <h4><a href="/ToyStore/productDetails?productId=${bestProduct.id}">${bestProduct.name}</a></h4>
                            <div class="price">
                                <span class="price-new">$ ${bestProduct.price}</span>
                            </div>
                            <div class="cart-button button-group">

                                <form action="/ToyStore/addWishList" method="post" style="
                                      display: inline-flex;
                                      ">
                                    <input type="hidden"
                                           name="productId"
                                           value="${bestProduct.id}">
                                    <button type="submit" title="Wishlist" class="btn btn-wishlist">
                                        <i class="fa fa-heart"></i>
                                    </button>
                                </form>


                                <form action="/ToyStore/addCompareList" method="post" style="
                                      display: inline-flex;
                                      ">
                                    <input type="hidden"
                                           name="productId"
                                           value="${bestProduct.id}">
                                    <button type="submit" title="Compare" class="btn btn-compare">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </button>
                                </form>

                                <form action="/ToyStore/addCartList" method="post" style="
                                      display: inline-flex;
                                      ">
                                    <input type="hidden"
                                           name="productId"
                                           value="${bestProduct.id}">
                                    <button type="submit" class="btn btn-cart">
                                        Add to cart
                                        <i class="fa fa-shopping-cart"></i>
                                    </button>
                                </form>

                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>


            <!-- Bestsellers Links Ends -->
        </div>
        <!-- Sidebar Ends -->
    </div>
</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>