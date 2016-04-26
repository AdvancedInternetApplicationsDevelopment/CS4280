<%-- 
    Document   : compareProducts
    Created on : Apr 4, 2016, 8:37:58 PM
    Author     : suhag
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="common/header.jsp" %>
<!-- Main Container Starts -->
<div id="main-container">

    <!-- Main Heading Starts -->
    <h2 class="main-heading text-center">
        Compare Products
    </h2>
    <!-- Main Heading Ends -->
    <c:choose>
        <c:when test="${compareProductList[0]==null}">
            <div><p>No products added to compare list</p></div>
        </c:when>
        <c:otherwise>
            <!-- Compare Table Starts -->
            <div class="table-responsive compare-table">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <td>Image</td>
                            <td>Product</td>
                            <td>Price</td>
                            <td>Model Number</td>
                            <td>Brand</td>
                            <td>Category</td>
                            <td>Availability</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${compareProductList}" var="item">
                            <tr>
                                <td>
                                    <img src="/ToyStore/getImage?productId=${item.id}" alt="image" title="image" class="img-thumbnail" style="width: 100px; height: 100px" />
                                </td>
                                <td class="name">
                                    <a href="/ToyStore/productDetails?productId=${item.id}">${item.name}</a>
                                </td>							
                                <td>
                                    $ ${item.price}
                                </td>
                                <td>
                                    ${item.modelNum}
                                </td>
                                <td>
                                    ${item.brand} 
                                </td>
                                <td>
                                    ${item.categoryId.name}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.quantity gt 0}">
                                            <span class="label label-success">In Stock</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="label label-danger">Out of Stock</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    
                                    <form action="/ToyStore/addCartList" method="post" style="display: inline-flex;">
                                        <input type="hidden"
                                               name="productId"
                                               value="${item.id}">
                                        <button type="submit" class="btn btn-cart">
                                            Add to cart
                                            <i class="fa fa-shopping-cart"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
            <!-- Compare Table Ends -->
        </c:otherwise>
    </c:choose>

</div>
<!-- Main Container Ends -->
<%@include file="common/footer.jsp" %>
