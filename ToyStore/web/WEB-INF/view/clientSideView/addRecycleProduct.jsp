<%-- 
    Document   : addRecycleProduct
    Created on : Apr 4, 2016, 9:48:50 PM
    Author     : suhag
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <!-- Primary Content Starts -->
        <div class="col-md-12">
            <!-- Product List Display Starts -->
            <div class="row">
                <!-- Product #1 Starts -->
                <div class="col-sm-6">
                    <!-- Registration Block Starts -->
                    <!-- Personal Information Ends -->
                    
                    <div class="panel panel-smart">
                       <div class="panel-heading">
                            <h3 class="panel-title">
                                Add Recycled product
                            </h3>
                        </div>
                        <!-- Delivery Information Starts -->
                        <div class="panel-body">
                            <!-- Registration Form Starts -->
                            <form class="form-horizontal" role="form" action="/ToyStore/uploadProduct" method="post" enctype="multipart/form-data">
                                <!-- Form element Starts -->
                                <div class="form-group">
                                    <label for="inputPname" class="col-sm-3 control-label" >Product Name :</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputPname" placeholder="Product Name" name="pName"  size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputModelNumber" class="col-sm-3 control-label">Model Number:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputModelNumber" placeholder="Model Number" name="mNo" size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputBrand" class="col-sm-3 control-label">Brand:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputBrand" placeholder="Brand" name="brand" size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputDescription" class="col-sm-3 control-label">Description:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputDescription" placeholder="Description" name="description" size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputAvailability" class="col-sm-3 control-label">Quantity:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputAvailability" placeholder="quantity" name="quantity" size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPrice" class="col-sm-3 control-label">Price:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputDescription" placeholder="Price" name="price" size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputAdditionalInfo" class="col-sm-3 control-label">Additional Information:</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="inputAdditionalInfo" placeholder="Additional Information" name="addInfo" size="50">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-3 control-label">Category:</label>
                                    <div class="col-sm-9">
                                        <select name="category" >
                                            <c:forEach items="${categories}" var="cat">
                                                <c:choose>
                                                    <c:when test="${categoryId == cat.id}">
                                                        <option value="${cat.id}" selected="selected"> ${cat.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${cat.id}"> ${cat.name}</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="inputPicture" class="col-sm-3 control-label">Picture:</label>
                                    <div class="col-sm-9">
                                        <input type="file" class="form-control" id="inputPicture" placeholder="Picture" name="image" size="50" m>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-3 col-sm-9">
                                        <button type="submit" class="btn btn-danger" value="uploadProduct">
                                            Add Product
                                        </button>
                                    </div>
                                </div>
                                <!-- Form Area Ends -->
                            </form>
                        </div>
                        <c:if test="${success == true}">
                            <div class="alert alert-success">
                                <p> Add product Successful </p>
                            </div> 
                        </c:if>
                        <c:if test="${error ==true}">
                            <div class="alert alert-danger">
                                <p> Error in adding product. Error message: ${errorMessage} </p>
                            </div> 
                        </c:if>
                    </div>
                </div>
                <div class="col-sm-6">
                    <!-- Conditions Panel Starts -->
                    <div class="panel panel-smart">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Conditions for adding a Recycled product
                            </h3>
                        </div>
                        <div class="panel-body">
                            <p>
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including  versions of Lorem Ipsum.
                            </p>
                            <ol>
                                <li>Lorem ipsum dolor sit amet</li>
                                <li>Consectetur adipiscing elit</li>
                                <li>Integer molestie lorem at massa</li>
                                <li>Facilisis in pretium nisl aliquet</li>
                                <li>Nulla volutpat aliquam velit</li>
                                <li>Faucibus porta lacus fringilla vel</li>
                                <li>Aenean sit amet erat nunc</li>
                                <li>Eget porttitor lorem</li>
                            </ol>
                            <p>
                                HTML Nullam varius, turpis et commodo pharetra, est eros bibendum elit, nec luctus magna felis sollicitudin mauris.
                            </p>
                            <p>
                                Integer in mauris eu nibh euismod gravida. Duis ac tellus et risus vulputate vehicula. Donec lobortis risus a elit. Etiam tempor. Ut ullamcorper, ligula eu tempor congue, eros est euismod turpis, id tincidunt sapien risus a quam. Maecenas fermentum consequat mi. Donec fermentum. Pellentesque malesuada nulla a mi. Duis sapien sem, aliquet nec, commodo eget, consequat quis, neque.
                            </p>
                        </div>
                    </div>
                    <!-- Conditions Panel Ends -->
                </div>
                <!-- Product #1 Ends -->
                <!-- Product List Display Ends -->
            </div>
            <!-- Primary Content Ends -->
        </div>
    </div>
</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>
