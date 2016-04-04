<%-- 
    Document   : productList
    Created on : Apr 4, 2016, 8:16:14 PM
    Author     : suhag
--%>
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
							<div class="product-col list clearfix">
								<div class="image">
									<img src="images/product-images/2.jpg" alt="product" class="img-responsive" />
								</div>
								<div class="caption">
									<h4><a href="product-full.html">Simply Organic Seeds</a></h4>
									<div class="description">
										Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
									</div>
									<div class="price">
										<p class="price-tax">Ex Tax: $279.99</p>
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
						<div class="col-xs-12">
							<div class="product-col list clearfix">
								<div class="image">
									<img src="images/product-images/3.jpg" alt="product" class="img-responsive" />
								</div>
								<div class="caption">
									<h4><a href="product-full.html">Simply Organic Seeds</a></h4>
									<div class="description">
										Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
									</div>
									<div class="price">
										<p class="price-tax">Ex Tax: $279.99</p>
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
						<div class="col-xs-12">
							<div class="product-col list clearfix">
								<div class="image">
									<img src="images/product-images/5.jpg" alt="product" class="img-responsive" />
								</div>
								<div class="caption">
									<h4><a href="product-full.html">Simply Organic Seeds</a></h4>
									<div class="description">
										Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
									</div>
									<div class="price">
										<p class="price-tax">Ex Tax: $279.99</p>
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
				<!-- Product List Display Ends -->
				</div>
			<!-- Primary Content Ends -->
			<!-- Sidebar Starts -->
				<div class="col-md-3">
				<!-- Shopping Options Starts -->
					<h3 class="side-heading">Shopping Options</h3>
					<div class="list-group">
						<div class="list-group-item">
							Brands
						</div>
						<div class="list-group-item">
							<div class="filter-group">
								<label class="checkbox">
									<input name="filter1" type="checkbox" value="br1" checked="checked" />
									Brand Name 1
								</label>
								<label class="checkbox">
									<input name="filter2" type="checkbox" value="br2" />
									Brand Name 2
								</label>
								<label class="checkbox">
									<input name="filter2" type="checkbox" value="br2" />
									Brand Name 3
								</label>
							</div>
						</div>
						<div class="list-group-item">
							Categories
						</div>
						<div class="list-group-item">
							<div class="filter-group">
								<label class="radio">
									<input name="filter-manuf" type="radio" value="mr1" checked="checked" />
									Category 1
								</label>
								<label class="radio">
									<input name="filter-manuf" type="radio" value="mr2" />
									category Name 2
								</label>
								<label class="radio">
									<input name="filter-manuf" type="radio" value="mr3" />
									category
								</label>
							</div>
						</div>
						<div class="list-group-item">
							<button type="button" class="btn btn-danger">Filter</button>
						</div>
					</div>
				<!-- Shopping Options Ends -->
				</div>
			<!-- Sidebar Ends -->
			</div>
		</div>
	<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>