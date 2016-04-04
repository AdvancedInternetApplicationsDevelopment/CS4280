<%-- 
    Document   : addRecycleProduct
    Created on : Apr 4, 2016, 9:48:50 PM
    Author     : suhag
--%>

<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
		<div id="main-container">
			<div class="row">
			<!-- Primary Content Starts -->
				<div class="col-md-12">
				<!-- Product List Display Starts -->
					<div class="row">
					<!-- Product #1 Starts -->
						<div class="col-xs-12">
							            <h3 class="panel-heading">
                            Add Recycled Product
                        </h3>
                        <div class="panel panel-smart">

                            <!-- Delivery Information Starts -->
                            <div class="panel-body">
                            <!-- Registration Form Starts -->
                                <form class="form-horizontal" role="form">
                                <!-- Personal Information Starts -->
                                    <div class="form-group">
                                        <label for="inputPname" class="col-sm-3 control-label" >Product Name :</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputPname" placeholder="Product Name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputModelNumber" class="col-sm-3 control-label">Model Number:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputModelNumber" placeholder="Model Number">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputBrand" class="col-sm-3 control-label">Brand:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputBrand" placeholder="Brand">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputDescription" class="col-sm-3 control-label">Description:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputDescription" placeholder="Description">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputAvailability" class="col-sm-3 control-label">Availability:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputAvailability" placeholder="Availability">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPrice" class="col-sm-3 control-label">Price:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputDescription" placeholder="Price">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputAdditionalInfo" class="col-sm-3 control-label">Additional Information:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputAdditionalInfo" placeholder="Additional Information">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputSpecifications" class="col-sm-3 control-label">Specifications:</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="inputSpecifications" placeholder="Specifications">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPicture" class="col-sm-3 control-label">Picture:</label>
                                        <div class="col-sm-9">
                                            <input type="file" class="form-control" id="inputPicture" placeholder="Picture">
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="submit" class="btn btn-danger">
                                            Add Product
                                        </button>
                                    </div>
                                </div>
                            <!-- Payment Area Ends -->
                                </form>
                            </div>
                        </div>
						</div>
					<!-- Product #1 Ends -->
				<!-- Product List Display Ends -->
				</div>
			<!-- Primary Content Ends -->
			
			</div>
		</div>
	<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>
