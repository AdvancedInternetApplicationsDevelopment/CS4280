/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Customer;
import model.OrderHistory;
import model.Product;
import model.Review;

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
            String email = "email3@yahoo.com";
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> products = productDAO.getRecycledByOwner(email);
            request.setAttribute("recycleProduct", products);
        } else if (userPath.equals("/orderHistory")) {
            //TODO convert the to session variable 
//            String email = (String) session.getAttribute("customerEmail");
            String email = "email3@yahoo.com";
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
            String email = "email3@yahoo.com";
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
            String email = "email3@yahoo.com";
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
