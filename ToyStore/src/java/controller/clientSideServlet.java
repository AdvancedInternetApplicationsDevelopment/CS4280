/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import dbaccessor.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CCInfo;
import model.Category;
import model.Customer;
import model.Order;
import model.OrderHistory;
import model.Product;
import model.Review;
import validate.Validator;

/**
 *
 * @author suhag
 */
public class clientSideServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userPath = request.getServletPath();

        if (userPath.equals("/productList")) {
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> products = productDAO.getAll();
            List<String> brands = productDAO.listAllBrands();
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            List<Category> categorys = categoryDAO.getAll();

            request.setAttribute("brands", brands);
            request.setAttribute("categoriesUnChecked", categorys);
            request.setAttribute("products", products);
        } else if (userPath.equals("/productDetails")) {
            String productId = request.getParameter("productId");
            ProductDAO productDAO = new ProductDAOImpl();
            ReviewDAO reviewDAO = new ReviewDAOImpl();
            int ratingRecived = reviewDAO.getAvgStarFromProductID(productId);
            int ratingLeft = 5 - ratingRecived;

            Product product = productDAO.getProductFromID(productId);
            String[] relatedProducts = productDAO.getRelated(productId);
            Product bestProduct = productDAO.getBestSelling();
            List<Product> relProducts = new ArrayList<Product>();
            for (String pid : relatedProducts) {
                relProducts.add(productDAO.getProductFromID(pid));
            }

            request.setAttribute("product", product);
            List<Review> reviews = reviewDAO.getReviewFromProductID(productId);
            request.setAttribute("reviews", reviews);
            request.setAttribute("bestProduct", bestProduct);
            request.setAttribute("Relatedproducts", relProducts);
            request.setAttribute("ratingRecived", ratingRecived);
            request.setAttribute("ratingLeft", ratingLeft);
        } else if (userPath.equals("/compareProducts")) {
            //TODO
            //to remove later 
            List<String> cp = new ArrayList<String>();
            cp.add("1");
            cp.add("2");
            cp.add("3");
            cp.add("9");
            cp.add("10");
            session.setAttribute("compareProducts", cp);
            @SuppressWarnings("unchecked")
            List<String> compareProducts = (List<String>) session.getAttribute("compareProducts");

            List<Product> compareProductList = new ArrayList<Product>();
            try {
                ProductDAO productDAO = new ProductDAOImpl();
                for (String p : compareProducts) {
                    compareProductList.add(productDAO.getProductFromID(p));
                }
            } catch (Exception e) {
            }
            request.setAttribute("compareProductList", compareProductList);
        } else if (userPath.equals("/wishList")) {
            //TODO
            //please remove later 
            List<String> wl = new ArrayList<String>();
            wl.add("1");
            wl.add("2");
            wl.add("3");
            wl.add("9");
            wl.add("10");
            session.setAttribute("wishListOfProducts", wl);
            @SuppressWarnings("unchecked")
            List<String> wishList = (List<String>) session.getAttribute("wishListOfProducts");

            List<Product> wishListProducts = new ArrayList<Product>();
            try {
                ProductDAO productDAO = new ProductDAOImpl();
                for (String p : wishList) {
                    wishListProducts.add(productDAO.getProductFromID(p));
                }
            } catch (Exception e) {
            }
            request.setAttribute("wishList", wishListProducts);
        } else if (userPath.equals("/recycleProductList")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email1@gmail.com";
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> products = productDAO.getRecycledByOwner(email);
            request.setAttribute("recycleProduct", products);
        } else if (userPath.equals("/orderHistory")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email1@gmail.com";
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            OrderedProductDAO orderedProductDAO = new OrderedProductDAOImpl();
            List<OrderHistory> orderHistorys = orderHistoryDAO.getOrderHistoryFromCustomerID(email);
            for (int i = 0; i < orderHistorys.size(); i++) {
                OrderHistory temp = orderHistorys.get(i);
                orderHistorys.remove(i);
                temp.setOrderedProducts(orderedProductDAO.getOrderHistoryFromID(temp.getId()));
                orderHistorys.add(i, temp);
            }
            request.setAttribute("orderHistory", orderHistorys);
        } else if (userPath.equals("/orderConfirmation")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email1@gmail.com";
            String orderId = (String) request.getAttribute("orderId");
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            OrderedProductDAO orderedProductDAO = new OrderedProductDAOImpl();
            List<OrderHistory> orderHistorys = new ArrayList<OrderHistory>();
            OrderHistory oh = orderHistoryDAO.getOrderHistoryFromID(orderId);
            orderHistorys.add(oh);
            for (int i = 0; i < orderHistorys.size(); i++) {
                OrderHistory temp = orderHistorys.get(i);
                orderHistorys.remove(i);
                temp.setOrderedProducts(orderedProductDAO.getOrderHistoryFromID(temp.getId()));
                orderHistorys.add(i, temp);
            }
            request.setAttribute("orderHistory", orderHistorys);
        } else if (userPath.equals("/home")) {
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> latestProducts = productDAO.getLatest();
            List<Product> latestRecycledProducts = productDAO.getLatestRecycled();
            request.setAttribute("latestRecycledProducts", latestRecycledProducts);
            request.setAttribute("latestProducts", latestProducts);
        } else if (userPath.equals("/addRecycleProduct")) {
            request.setAttribute("categoryId", 1);
            request.setAttribute("success", false);
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
        } else if (userPath.equals("/accountDetails")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email1@gmail.com";
            CustomerDAO customerDAO = new CustomerDAOImpl();
            Customer customerDetails = customerDAO.getCustomerFromID(email);
            request.setAttribute("customerDetails", customerDetails);
        } else if (userPath.equals("/editPassword")) {

            request.setAttribute("success", false);
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
        } else if (userPath.equals("/editCustomer")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email1@gmail.com";
            CustomerDAO customerDAO = new CustomerDAOImpl();
            Customer customer = customerDAO.getCustomerFromID(email);
            CCInfoDAO cCInfoDAO = new CCInfoDAOImpl();
            CCInfo cCInfo = cCInfoDAO.getCcInfoFromID(email);
            request.setAttribute("fname", customer.getFname());
            request.setAttribute("lname", customer.getLname());
            request.setAttribute("phone", customer.getPhone());
            request.setAttribute("fax", customer.getFax());
            request.setAttribute("addressL1", customer.getAddress1());
            request.setAttribute("addressL2", customer.getAddress2());
            request.setAttribute("city", customer.getCity());
            request.setAttribute("postal", customer.getPostal());
            request.setAttribute("country", customer.getCountry());
            request.setAttribute("region", customer.getRegion());
            request.setAttribute("ccNumber", cCInfo.getCcNumber());
            request.setAttribute("ccv", cCInfo.getCcv());
            request.setAttribute("cardHolder", "hhh");
            request.setAttribute("expiryDate", cCInfo.getExpiryDate());

            request.setAttribute("success", false);
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
        } else if (userPath.equals("/cart")) {

            String clear = request.getParameter("clear");

            if ((clear != null) && clear.equals("true")) {

                ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
                cart.clear();
            }
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            userPath = "/cart";

            // if checkout page is requested
        } else if (userPath.equals("/checkout")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            boolean error = false;
            String email = "email1@gmail.com";
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            List<String> ordeConfirmation = null;
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            OrderedProductDAO orderedProductDAO = new OrderedProductDAOImpl();
            try {

                List<Order> orders = new ArrayList<Order>();
                for (ShoppingCartItem scItem : cart.getItems()) {
                    Order o = new Order(scItem.getProduct().getId(), scItem.getQuantity());
                    orders.add(o);
                }
                String discountCode = cart.getDiscount();
                double credit = cart.getCredit();
                ordeConfirmation = orderHistoryDAO.checkout(orders, discountCode, credit, email);
                if ((!error) && (ordeConfirmation != null) && (ordeConfirmation.get(0).equals("success"))) {
                    String orderId = ordeConfirmation.get(1);

                    List<OrderHistory> orderHistorys = new ArrayList<OrderHistory>();
                    OrderHistory oh = orderHistoryDAO.getOrderHistoryFromID(orderId);
                    orderHistorys.add(oh);
                    for (int i = 0; i < orderHistorys.size(); i++) {
                        OrderHistory temp = orderHistorys.get(i);
                        orderHistorys.remove(i);
                        temp.setOrderedProducts(orderedProductDAO.getOrderHistoryFromID(temp.getId()));
                        orderHistorys.add(i, temp);
                    }
                    cart = null;
                    session.setAttribute("cart", cart);
                    request.setAttribute("orderHistory", orderHistorys);
                    userPath = "/orderConfirmation";
                } else {
                    error = true;
                    request.setAttribute("error", error);
                    if (ordeConfirmation != null) {
                        request.setAttribute("errorMessage", ordeConfirmation.get(1));
                    } else {
                        request.setAttribute("errorMessage", "checkout failed");
                    }
                    userPath = "/cart";

                }
            } catch (Exception e) {
                error = true;
                request.setAttribute("error", error);
                request.setAttribute("errorMessage", e.getMessage());
                userPath = "/cart";
            }

        }
        String url = "/WEB-INF/view/clientSideView/" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String userPath = request.getServletPath();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (userPath.equals("/productList")) {
            ProductDAO productDAO = new ProductDAOImpl();
            List<Category> categorysChecked = new ArrayList<Category>();
            List<String> brands = productDAO.listAllBrands();
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            List<Category> categorys = categoryDAO.getAll();
            String[] brandsChecked = request.getParameterValues("brand");
            String[] catChecked = request.getParameterValues("category");
            List<Integer> categoryChecked = new ArrayList<Integer>();
            List<String> brandsChecList = new ArrayList<String>();
            List<Product> products = null;
            if (!(brandsChecked == null && catChecked == null)) {
                if (!(brandsChecked == null)) {
                    for (String brandsChecked1 : brandsChecked) {
                        brandsChecList.add(brandsChecked1);
                    }
                }
                if (!(catChecked == null)) {
                    for (String catChecked1 : catChecked) {
                        categoryChecked.add(Integer.parseInt(catChecked1));
                    }
                }
                products = productDAO.getByFilter(brandsChecList, categoryChecked);
                for (String b : brandsChecList) {
                    if (brands.contains(b)) {
                        brands.remove(b);
                    }
                }

                for (Integer c : categoryChecked) {
                    Category cat = categoryDAO.getCategoryFromID(c);
                    if (categorys.contains(cat)) {
                        categorysChecked.add(cat);
                        categorys.remove(cat);
                    }
                }
            } else {
                products = productDAO.getAll();
            }

            request.setAttribute("brands", brands);
            request.setAttribute("categoriesChecked", categorysChecked);
            request.setAttribute("brandsChecked", brandsChecList);
            request.setAttribute("categoriesUnChecked", categorys);
            request.setAttribute("products", products);

        } else if (userPath.equals("/productDetails")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email1@gmail.com";
            boolean success = true;
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            String reviewMessage = request.getParameter("review");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String productId = request.getParameter("productId");
            ProductDAO productDAO = new ProductDAOImpl();
            ReviewDAO reviewDAO = new ReviewDAOImpl();

            CustomerDAO customerDAO = new CustomerDAOImpl();
            Product product = productDAO.getProductFromID(productId);
            String[] relatedProducts = productDAO.getRelated(productId);
            Product bestProduct = productDAO.getBestSelling();
            List<Product> relProducts = new ArrayList<Product>();
            for (String pid : relatedProducts) {
                relProducts.add(productDAO.getProductFromID(pid));
            }

            try {
                Customer customer = customerDAO.getCustomerFromID(email);
                Review submitReview = new Review(customer, product, reviewMessage, "", rating);

                if (!(reviewDAO.addReview(submitReview))) {
                    throw new Exception("Cannot review the same product again.");
                }
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
                success = false;
            }

            int ratingRecived = reviewDAO.getAvgStarFromProductID(productId);
            int ratingLeft = 5 - ratingRecived;
            List<Review> reviews = reviewDAO.getReviewFromProductID(productId);
            request.setAttribute("reviews", reviews);
            request.setAttribute("success", success);
            request.setAttribute("product", product);
            request.setAttribute("bestProduct", bestProduct);
            request.setAttribute("Relatedproducts", relProducts);
            request.setAttribute("ratingRecived", ratingRecived);
            request.setAttribute("ratingLeft", ratingLeft);
        } else if (userPath.equals("/editPassword")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            boolean success = true;
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            String email = "email1@gmail.com";
            String pass1 = request.getParameter("password");
            String pass2 = request.getParameter("rePassword");
            if (pass1.equals(pass2)) {
                //TODO add salt hash 

                try {
                    LoginDAO loginDAO = new LoginDAOImpl();
                    if (!(loginDAO.updatePass(email, pass1))) {
                        throw new Exception("Cannot update password again.");
                    }
                } catch (Exception e) {
                    request.setAttribute("error", true);
                    request.setAttribute("errorMessage", e.getMessage());
                    success = false;
                }
                request.setAttribute("success", success);

            } else {
                request.setAttribute("success", false);
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", "Password does not match");
            }
            //have to edit 
        } else if (userPath.equals("/editCustomer")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            boolean success = true;
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            String email = "email1@gmail.com";
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String phone = request.getParameter("phone");
            String fax = request.getParameter("fax");
            String address1 = request.getParameter("addressL1");
            String address2 = request.getParameter("addressL2");
            String city = request.getParameter("city");
            String postal = request.getParameter("postal");
            String country = request.getParameter("country");
            String region = request.getParameter("region");
            String ccNumber = request.getParameter("ccNumber");
            String ccvS = request.getParameter("ccv");
            String cardHolder = request.getParameter("cardHolder");
            String expiryDate = request.getParameter("expiryDate");
            CCInfo ccInfo = null;

//            CCInfo ccInfo = new CCInfo(ccNumber, lname, expiryDate, 0);
            try {
                int ccv = Integer.parseInt(ccvS);
                CustomerDAO customerDAO = new CustomerDAOImpl();
                Customer customer = new Customer(email, fname, lname, phone, fax, address1, address2, city, postal, country, region, ccNumber, 0);
                CCInfoDAO cCInfoDAO = new CCInfoDAOImpl();
                CCInfo temp = cCInfoDAO.getCcInfoFromID(email);
                ccInfo = new CCInfo(ccNumber, cardHolder, email, expiryDate, ccv);
                cCInfoDAO.updateCcInfo(ccInfo);
                if (!(customerDAO.updateCustomer(customer))) {
                    cCInfoDAO.updateCcInfo(temp);
                    throw new Exception("Cannot update customer details.");
                }
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
                success = false;
            }
            request.setAttribute("success", success);

        } else if (userPath.equals("/updateCart")) {

            // get input from request
            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");

            boolean invalidEntry = Validator.validateQuantity(productId, quantity);

            if (!invalidEntry) {
                ProductDAO productDAO = new ProductDAOImpl();
                Product product = productDAO.getProductFromID(productId);
                cart.update(product, quantity);
                session.setAttribute("cart", cart);
            }
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            userPath = "/cart";
        } else if (userPath.equals("/addCartList")) {

            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            if (cart == null) {

                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }

            // get user input from request
            String productId = request.getParameter("productId");

            if (!productId.isEmpty()) {

                ProductDAO productDAO = new ProductDAOImpl();
                Product product = productDAO.getProductFromID(productId);
                cart.addItem(product);
                session.setAttribute("cart", cart);
            }
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            userPath = "/cart";
        } else if (userPath.equals("/removeCartItem")) {

            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            if (!(cart == null)) {
                // get user input from request
                String productId = request.getParameter("productId");

                if (!productId.isEmpty()) {

                    ProductDAO productDAO = new ProductDAOImpl();
                    Product product = productDAO.getProductFromID(productId);
                    cart.removeItem(product);
                    session.setAttribute("cart", cart);
                }
                request.setAttribute("error", false);
                request.setAttribute("errorMessage", null);
                userPath = "/cart";
            }

        } else if (userPath.equals("/updateCoupon")) {

            // if user is adding item to cart for first time
            // create cart object and attach it to user session
            if (!(cart == null)) {
                // get user input from request
                String discount = request.getParameter("discount");
                String creditS = request.getParameter("credit");
                double credit = 0.0d;
                try {
                    credit = Double.parseDouble(creditS);
                } catch (Exception e) {
                    request.setAttribute("error", true);
                    request.setAttribute("errorMessage", e.getMessage());

                }
                if (credit > -1) {
                    cart.updateDisCre(discount, credit);
                    session.setAttribute("cart", cart);
                }
                userPath = "/cart";
            }

        }
        String url = "/WEB-INF/view/clientSideView/" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
