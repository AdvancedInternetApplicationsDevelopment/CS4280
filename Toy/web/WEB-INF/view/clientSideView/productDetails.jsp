<%-- 
    Document   : productDetails
    Created on : Apr 4, 2016, 3:06:24 PM
    Author     : suhag
--%>

<%@include file="common/header.jsp" %>

<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <!-- Primary Content Starts -->
        <div class="col-md-9">
            <!-- Product Info Starts -->
            <div class="row product-info">
                <!-- Left Starts -->
                <div class="col-sm-5 images-block">
                    <p>
                        <img src="images/product-images/pimg3.jpg" alt="Image" class="img-responsive thumbnail" />
                    </p>
                </div>
                <!-- Left Ends -->
                <!-- Right Starts -->
                <div class="col-sm-7 product-details">
                    <!-- Product Name Starts -->
                    <h2>Simply Organic seeds</h2>
                    <!-- Product Name Ends -->
                    <hr />
                    <!-- Manufacturer Starts -->
                    <ul class="list-unstyled manufacturer">
                        <li>
                            <span>Brand:</span> Indian spices
                        </li>
                        <li><span>Reward Points:</span> 300</li>
                        <li>
                            <span>Availability:</span> <strong class="label label-success">In Stock</strong>
                        </li>
                    </ul>
                    <!-- Manufacturer Ends -->
                    <hr />
                    <!-- Price Starts -->
                    <div class="price">
                        <span class="price-head">Price :</span>
                        <span class="price-new">$199.50</span> 
                    </div>
                    <!-- Price Ends -->
                    <hr />
                    <!-- Available Options Starts -->
                    <div class="options">
                        <div class="cart-button button-group">
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
                            <div class="col-lg-4">
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
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </p>
                    <p>
                        It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </p>
                </div>
            </div>
            <!-- Product Description Ends -->
            <!-- Additional Information Starts -->
            <div class="product-info-box">
                <h4 class="heading">Additional Information</h4>
                <div class="content panel-smart">
                    <p>
                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </p>
                </div>
            </div>
            <!-- Additional Information Ends -->
            <div class="product-info-box">
                <h4 class="heading">Review Product</h4>
                <div class="content panel-smart">
                    <form class="form-horizontal">
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="input-name">Name</label>
                            <div class="col-sm-10">
                                <input name="name" class="form-control" id="input-name" type="text" value="">
                            </div>
                        </div>
                        <div class="form-group required">
                            <label class="col-sm-2 control-label" for="input-review">Review</label>
                            <div class="col-sm-10">
                                <textarea name="text" class="form-control" id="input-review" rows="5"></textarea>
                                <div class="help-block">
                                    Some note goes here..
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
                                <button class="btn btn-danger" id="button-review" type="button">
                                    Submit
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Related Products Starts -->
            <div class="product-info-box">
                <h4 class="heading">Related Products</h4>
                <!-- Products Row Starts -->
                <div class="row">
                    <!-- Product #1 Starts -->
                    <div class="col-md-4 col-sm-6">
                        <div class="product-col">
                            <div class="image">
                                <img src="images/product-images/20.jpg" alt="product" class="img-responsive" />
                            </div>
                            <div class="caption">
                                <h4><a href="product.html">Simply Organic Seeds</a></h4>
                                <div class="description">
                                    We are so lucky living in such a wonderful time. Our almost unlimited ...
                                </div>
                                <div class="price">
                                    <span class="price-new">$199.50</span> 
                                    <span class="price-old">$249.50</span>
                                </div>
                                <div class="cart-button button-group">
                                    <button type="button" title="Wishlist" class="btn btn-wishlist">
                                        <i class="fa fa-heart"></i>
                                    </button>
                                    <button type="button" title="Compare" class="btn btn-compare">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </button>
                                    <button type="button" class="btn btn-cart">
                                        Add to cart
                                        <i class="fa fa-shopping-cart"></i> 
                                    </button>									
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Product #1 Ends -->
                    <!-- Product #2 Starts -->
                    <div class="col-md-4 col-sm-6">
                        <div class="product-col">
                            <div class="image">
                                <img src="images/product-images/17.jpg" alt="product" class="img-responsive" />
                            </div>
                            <div class="caption">
                                <h4><a href="product.html">Simply Organic Seeds</a></h4>
                                <div class="description">
                                    We are so lucky living in such a wonderful time. Our almost unlimited ...
                                </div>
                                <div class="price">
                                    <span class="price-new">$199.50</span> 
                                    <span class="price-old">$249.50</span>
                                </div>
                                <div class="cart-button button-group">
                                    <button type="button" title="Wishlist" class="btn btn-wishlist">
                                        <i class="fa fa-heart"></i>
                                    </button>
                                    <button type="button" title="Compare" class="btn btn-compare">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </button>
                                    <button type="button" class="btn btn-cart">
                                        Add to cart
                                        <i class="fa fa-shopping-cart"></i> 
                                    </button>									
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Product #2 Ends -->
                    <!-- Product #3 Starts -->
                    <div class="col-md-4 col-sm-6">
                        <div class="product-col">
                            <div class="image">
                                <img src="images/product-images/6.jpg" alt="product" class="img-responsive" />
                            </div>
                            <div class="caption">
                                <h4><a href="product.html">Simply Organic Seeds</a></h4>
                                <div class="description">
                                    We are so lucky living in such a wonderful time. Our almost unlimited ...
                                </div>
                                <div class="price">
                                    <span class="price-new">$199.50</span> 
                                    <span class="price-old">$249.50</span>
                                </div>
                                <div class="cart-button button-group">
                                    <button type="button" title="Wishlist" class="btn btn-wishlist">
                                        <i class="fa fa-heart"></i>
                                    </button>
                                    <button type="button" title="Compare" class="btn btn-compare">
                                        <i class="fa fa-bar-chart-o"></i>
                                    </button>
                                    <button type="button" class="btn btn-cart">
                                        Add to cart
                                        <i class="fa fa-shopping-cart"></i> 
                                    </button>									
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Product #3 Ends -->
                </div>
                <!-- Products Row Ends -->
            </div>
            <!-- Related Products Ends -->
        </div>
        <!-- Primary Content Ends -->
        <!-- Sidebar Starts -->
        <div class="col-md-3">
            <!-- Bestsellers Links Starts -->
            <h3 class="side-heading">Bestsellers</h3>
            <div class="product-col">
                <div class="image">
                    <img src="images/product-images/20.jpg" alt="product" class="img-responsive" />
                </div>
                <div class="caption">
                    <h4>
                        <a href="product-full.html">Simply Organic Seeds</a>
                    </h4>
                    <div class="description">
                        We are so lucky living in such a wonderful time. Our almost unlimited ...
                    </div>
                    <div class="price">
                        <span class="price-new">$199.50</span> 
                        <span class="price-old">$249.50</span>
                    </div>
                    <div class="cart-button button-group">
                        <button type="button" title="Wishlist" class="btn btn-wishlist">
                            <i class="fa fa-heart"></i>
                        </button>
                        <button type="button" title="Compare" class="btn btn-compare">
                            <i class="fa fa-bar-chart-o"></i>
                        </button>
                        <button type="button" class="btn btn-cart">
                            Add to cart
                            <i class="fa fa-shopping-cart"></i> 
                        </button>									
                    </div>
                </div>
            </div>
            <!-- Bestsellers Links Ends -->
        </div>
        <!-- Sidebar Ends -->
    </div>
</div>
<!-- Main Container Ends -->

<%@include file="common/footer.jsp" %>