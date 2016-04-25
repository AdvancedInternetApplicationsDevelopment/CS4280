<%-- 
    Document   : editPassword
    Created on : Apr 23, 2016, 6:19:46 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <div class="row">
        <div class="col-md-6 center-block">
            <div class="product-info-box">
                <h4 class="heading">Change Password</h4>
                <!-- Password Area Starts -->
                <form action="/ToyStore/editPassword" method="post">
                    <div class="content panel-smart" style="font-weight: 700;">
                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-3 control-label">Password :</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputRePassword" class="col-sm-3 control-label">Re-Password :</label>
                            <div class="col-sm-9">
                                <input type="password" class="form-control" id="inputRePassword" placeholder="Re-Password" name="rePassword">
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-danger">
                                Change Password
                            </button>
                        </div>
                        <c:if test="${success == true}">
                            <div class="alert alert-success">
                                <p> Change Successful </p>
                            </div> 
                        </c:if>
                        <c:if test="${error ==true}">
                            <div class="alert alert-danger">
                                <p> Error in changing password. Error message: ${errorMessage} </p>
                            </div> 
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Content Ends -->
</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>
