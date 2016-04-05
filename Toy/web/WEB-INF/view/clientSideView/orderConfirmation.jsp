<%-- 
    Document   : orderConfirmation
    Created on : Apr 5, 2016, 4:29:42 PM
    Author     : suhag
--%>

<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
		<div id="main-container">
			<div class="row">
			<!-- Primary Content Starts -->
				<div class="col-md-12">
				<!-- Product List Display Starts -->
					<!-- Product #1 Starts -->
                                                <div class="panel panel-smart">
							<div class="panel-heading">
								<h3 class="panel-title" style="color:red;">
									Receipt Number: #123AFGDWOIQ
								</h3>
							</div>
							<div class="panel-body">
                                                                <table class="table borderless">
                                                                    <thead>
                                                                        <tr>
                                                                            <th>No.</th>
                                                                            <th>Product Name</th>
                                                                            <th>Quantity</th>
                                                                            <th>Price</th>
                                                                        </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>1</td>
                                                                            <td>Toy 1</td>
                                                                            <td>2</td>
                                                                            <td>$ 286</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>2</td>
                                                                            <td>Toy 2</td>
                                                                            <td>2</td>
                                                                            <td>$ 300</td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                                <dl class="dl-horizontal">
									<dt>Coupon Discount :</dt>
									<dd>$-25.00</dd>
									<dt>Subtotal :</dt>
									<dd>$300.00</dd>
									<dt>Payment Fee :</dt>
									<dd>$10.00</dd>
									<dt>Shipment Fee :</dt>
									<dd>$15.00</dd>
									<dt>Tax Total :</dt>
									<dd>$315.00</dd>
								</dl>
								<hr />
								<dl class="dl-horizontal total">
                                                                    <dt><h3 class="panel-title" style="color:red;">Total :</h3>
                                                                            
                                                                        </dt>
                                                                        <dd><h3 class="panel-title" style="color:red;">$325.00</h3></dd>
								</dl>
								<hr />
                                                                <div class="text-uppercase clearfix">
									<a href="#" class="btn btn-danger pull-right">
										<span class="hidden-xs">Continue Shopping</span>
										<span class="visible-xs">Continue</span>
									</a>
								</div>
							</div>
						</div>
			<!-- Primary Content Ends -->
			
			</div>
		</div>
	<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>
