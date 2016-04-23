<%-- 
    Document   : recycleProductList
    Created on : Apr 4, 2016, 9:49:15 PM
    Author     : suhag
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">
    <!-- Main Heading Starts -->
    <h2 class="main-heading text-center">
        Recycled Products Added
    </h2>
    <!-- Main Heading Ends -->
    <div class="row">
        <!-- Primary Content Starts -->
        <div class="col-md-12">
            <!-- Product List Display Starts -->
            <div class="row">
                <c:choose>
                    <c:when test="${recycleProduct[0]==null}">
                        <div><p>No products added to compare list</p></div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${recycleProduct}" var="item">
                            <div class="col-xs-12">
                                <div class="product-col list clearfix">
                                    <div class="image">
                                        <img src="/ToyStore/getImage?productId=${item.id}" alt="product" class="img-responsive" />
                                    </div>
                                    <div class="caption">
                                        <h4><a href="#">${item.name}</a></h4>
                                        <div class="description">
                                            ${item.description}
                                        </div>
                                        <div class="price">
                                            <span class="price-new">$ ${item.price}</span>
                                        </div>
                                        <div class="cart-button button-group">
                                            <c:choose>
                                                <c:when test="${not item.approved}">
                                                    <button type="button" class="btn btn-danger disabled" disabled="disabled">
                                                        Needs Approval
                                                        <i class="fa fa-check"></i>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" class="btn btn-success disabled" disabled="disabled">
                                                        Approved
                                                        <i class="fa fa-check"></i>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>

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

