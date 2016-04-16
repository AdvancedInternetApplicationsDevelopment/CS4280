/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.http.Part;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import model.Category;
import model.Customer;
import model.Discount;
import model.OrderHistory;
import model.Product;
import model.Review;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author suhag
 */
@MultipartConfig
public class adminControllerServlet extends HttpServlet {

    private String userPath;
    private int noOfUsers;
    private int pendingApprovals;
    private int productsSold;
    private int totalNoOfProducts;
    private Product latestApprovalItem;
    private List<OrderHistory> transactions;

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
        userPath = request.getServletPath();

        if (userPath.equals("/adminDashboard")) {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            ProductDAO productDAO = new ProductDAOImpl();
            OrderedProductDAO orderedProductDAO = new OrderedProductDAOImpl();
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            noOfUsers = (customerDAO).getNoOfUsers();
            pendingApprovals = (productDAO).getAllPending().size();
            productsSold = (orderedProductDAO).getTotalQuantityOfProductSold();
            totalNoOfProducts = (productDAO).getNoOfProducts();
            latestApprovalItem = (productDAO).getLatestPending();
            transactions = (orderHistoryDAO).getLatest();

            request.setAttribute("noOfUsers", noOfUsers);
            request.setAttribute("pendingApprovals", pendingApprovals);
            request.setAttribute("productsSold", productsSold);
            request.setAttribute("totalNoOfProducts", totalNoOfProducts);
            request.setAttribute("latestApprovalItem", latestApprovalItem);
            request.setAttribute("transactions", transactions);
        } else if (userPath.equals("/adminCustomer")) {
            List<Customer> customerSearchList = null;
            request.setAttribute("FName", "");
            request.setAttribute("LName", "");
            request.setAttribute("emailID", "");
            request.setAttribute("phone", "");
            request.setAttribute("fax", "");
            request.setAttribute("city", "");
            request.setAttribute("postalCode", "");
            request.setAttribute("country", "");
            request.setAttribute("region", "");
            request.setAttribute("customerSearchList", customerSearchList);
        } else if (userPath.equals("/adminTransactions")) {
            List<OrderHistory> orderHistorys = null;
            request.setAttribute("transactions", orderHistorys);
            request.setAttribute("orderNo", "");
            request.setAttribute("userId", "");
        } else if (userPath.equals("/adminProducts")) {
            List<Product> products = null;
            request.setAttribute("pName", "");
            request.setAttribute("mNo", "");
            request.setAttribute("categoryId", -1);
            request.setAttribute("products", products);
        } else if (userPath.equals("/adminRecycledProducts")) {
            List<Product> products = null;
            request.setAttribute("pName", "");
            request.setAttribute("mNo", "");
            request.setAttribute("categoryId", -1);
            request.setAttribute("owner", "");
            request.setAttribute("products", products);
        } else if (userPath.equals("/adminApprovals")) {
            ProductDAO productDAO = new ProductDAOImpl();
            List<Product> approveProducts = productDAO.getAllPending();
            request.setAttribute("approveProducts", approveProducts);
        } else if (userPath.equals("/adminViewRecycled")) {
            String productId = request.getParameter("productId");
            ProductDAO productDAO = new ProductDAOImpl();
            Product product = productDAO.getProductFromID(productId);
            request.setAttribute("product", product);
        } else if (userPath.equals("/adminEditProducts")) {
            String productId = request.getParameter("productId");
            ProductDAO productDAO = new ProductDAOImpl();
            Product product = productDAO.getProductFromID(productId);
            if (product == null) {
                userPath = "/admin404";
            } else {
                request.setAttribute("pName", product.getName());
                request.setAttribute("mNo", product.getModelNum());
                request.setAttribute("brand", product.getBrand());
                request.setAttribute("description", product.getDescription());
                request.setAttribute("quantity", product.getQuantity());
                request.setAttribute("price", product.getPrice());
                request.setAttribute("addInfo", product.getAddInfo());
                request.setAttribute("categoryId", product.getCategoryId().getId());
                request.setAttribute("productId", product.getId());
                request.setAttribute("success", false);
                request.setAttribute("error", false);
                request.setAttribute("errorMessage", null);
            }
        } else if (userPath.equals("/adminAddProducts")) {
            request.setAttribute("categoryId", 1);
            request.setAttribute("success", false);
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
        } else if (userPath.equals("/adminDiscount")) {
            request.setAttribute("success", false);
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);

            DiscountDAO discountDAO = new DiscountDAOImpl();
            List<Discount> discounts = discountDAO.getAll();
            request.setAttribute("discounts", discounts);
        }
        else if (userPath.equals("/adminInbox")) {
            request.setAttribute("success", false);
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);

            ReviewDAO reviewDAO = new ReviewDAOImpl();
            List<Review> reviews = reviewDAO.getAll();
            request.setAttribute("reviews", reviews);
        }

        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException ex) {
//            String urlNotFound = "/WEB-INF/view/admin404.jsp";
//            request.getRequestDispatcher(urlNotFound).forward(request, response);
            ex.printStackTrace();
        } catch (IOException ex) {
//            String urlNotFound = "/WEB-INF/view/admin404.jsp";
//            request.getRequestDispatcher(urlNotFound).forward(request, response);
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
        userPath = request.getServletPath();
        if (userPath.equals("/adminCustomer")) {

            String FName = (String) request.getParameter("FName");
            String LName = (String) request.getParameter("LName");
            String emailID = (String) request.getParameter("emailID");
            String phone = (String) request.getParameter("phone");
            String fax = (String) request.getParameter("fax");
            String city = (String) request.getParameter("city");
            String postalCode = (String) request.getParameter("postalCode");
            String country = (String) request.getParameter("country");
            String region = (String) request.getParameter("region");
            System.out.print(emailID + FName + LName + phone + fax + city + postalCode + country + region);
            List<Customer> customerSearchList = null;
            if (!(FName.equalsIgnoreCase("") && LName.equalsIgnoreCase("") && emailID.equalsIgnoreCase("") && phone.equalsIgnoreCase("") && fax.equalsIgnoreCase("") && city.equalsIgnoreCase("") && country.equalsIgnoreCase("") && region.equalsIgnoreCase("") && postalCode.equalsIgnoreCase(""))) {
                CustomerDAO customerDAO = new CustomerDAOImpl();
                customerSearchList = customerDAO.getByFilter(emailID, FName, LName, phone, fax, city, postalCode, country, region);
            }
            request.setAttribute("FName", FName);
            request.setAttribute("LName", LName);
            request.setAttribute("emailID", emailID);
            request.setAttribute("phone", phone);
            request.setAttribute("fax", fax);
            request.setAttribute("city", city);
            request.setAttribute("postalCode", postalCode);
            request.setAttribute("country", country);
            request.setAttribute("region", region);
            request.setAttribute("customerSearchList", customerSearchList);
        } else if (userPath.equals("/approveProduct")) {
            ProductDAO productDAO = new ProductDAOImpl();
            productDAO.approveRecycled(request.getParameter("productId"));
            userPath = "/adminDashboard";
        } else if (userPath.equals("/adminTransactions")) {
            String customerId = request.getParameter("userId");
            String orderNo = request.getParameter("orderNo");
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            List<OrderHistory> orderHistory = orderHistoryDAO.getByFilter(customerId, orderNo);
            request.setAttribute("transactions", orderHistory);
            request.setAttribute("orderNo", orderNo);
            request.setAttribute("userId", customerId);
        } else if (userPath.equals("/adminProducts")) {
            List<Product> products = null;
            String pName = request.getParameter("pName");
            String mNo = request.getParameter("mNo");
            int categoryId = Integer.parseInt(request.getParameter("category"));
            ProductDAO productDAO = new ProductDAOImpl();
            products = productDAO.getByFilter(pName, mNo, categoryId);
            request.setAttribute("pName", pName);
            request.setAttribute("mNo", mNo);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("products", products);

        } else if (userPath.equals("/adminRecycledProducts")) {
            List<Product> products = null;
            String pName = request.getParameter("pName");
            String mNo = request.getParameter("mNo");
            String owner = request.getParameter("owner");
            int categoryId = Integer.parseInt(request.getParameter("category"));
            ProductDAO productDAO = new ProductDAOImpl();
            products = productDAO.getRecycledByFilter(pName, owner, categoryId);
            request.setAttribute("pName", pName);
            request.setAttribute("mNo", mNo);
            request.setAttribute("owner", owner);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("products", products);

        } else if (userPath.equals("/adminEditProducts")) {

            boolean success = true;
            ProductDAO productDAO = new ProductDAOImpl();
            Product product;
            String productId = request.getParameter("productId");
            String pName = request.getParameter("pName");
            String mNo = request.getParameter("mNo");
            String brand = request.getParameter("brand");
            String description = request.getParameter("description");
            String quantity = request.getParameter("quantity");
            String price = request.getParameter("price");
            String addInfo = request.getParameter("addInfo");
            int categoryId = Integer.parseInt(request.getParameter("category"));

            try {
                double priceDouble = Double.parseDouble(price);
                int quantityInt = Integer.parseInt(quantity);
                Category category = (new CategoryDAOImpl()).getCategoryFromID(categoryId);
                product = new Product(productId, pName, mNo, category, quantityInt, true, priceDouble, brand, description, addInfo, true, true, "shop");
                if (!(productDAO.updateProduct(product))) {
                    throw new Exception("update unsuccessful");
                }
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
                success = false;
            }

            request.setAttribute("pName", pName);
            request.setAttribute("mNo", mNo);
            request.setAttribute("brand", brand);
            request.setAttribute("description", description);
            request.setAttribute("quantity", quantity);
            request.setAttribute("price", price);
            request.setAttribute("addInfo", addInfo);
            request.setAttribute("categoryId", categoryId);
            request.setAttribute("productId", productId);
            request.setAttribute("success", success);
            if (success == true) {
                request.setAttribute("error", false);
                request.setAttribute("errorMessage", null);
            }

        } else if (userPath.equals("/adminDiscount")) {
            boolean success = true;
            String amount = request.getParameter("amount");
            try {
                double amountDouble = Double.parseDouble(amount);
                DiscountDAO discountDAO = new DiscountDAOImpl();
                Discount discount = new Discount("", amountDouble);
                if (!(discountDAO.addDiscount(discount))) {
                    throw new Exception("create discount unsuccessful. database error");
                }
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
                success = false;
            }

            DiscountDAO discountDAO = new DiscountDAOImpl();
            List<Discount> discounts = discountDAO.getAll();
            request.setAttribute("discounts", discounts);

            if (success == true) {
                request.setAttribute("success", success);
                request.setAttribute("error", false);
                request.setAttribute("errorMessage", null);
            }

        } else if (userPath.equals("/adminDiscountDelete")) {
            boolean error = false;
            String discountCode = request.getParameter("discountCode");
            request.setAttribute("error", error);
            request.setAttribute("errorMessage", null);
            DiscountDAO discountDAO = new DiscountDAOImpl();

            try {
                Discount discount = discountDAO.getDiscountFromID(discountCode);
                if (!(discountDAO.deleteDiscount(discount))) {
                    throw new Exception("delete discount unsuccessful. database error");
                }
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
                error = true;
            }
            List<Discount> discounts = discountDAO.getAll();
            request.setAttribute("discounts", discounts);

            request.setAttribute("success", false);
            if(error ==true)
            {
                userPath = "/adminDiscount";
            }
        }else if (userPath.equals("/adminInbox")) {
            boolean success = true;
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
            String adminReply = request.getParameter("adminReplyform");
            String customerId = request.getParameter("customerId");
            String productId = request.getParameter("productId");
            ReviewDAO reviewDAO = new ReviewDAOImpl();
            try {
                Review review = reviewDAO.getReviewFromID(customerId, productId);
                review.setAdminReply(adminReply);
                if (!(reviewDAO.addAdminReply(review))) {
                    throw new Exception("reply unsuccessful. database error");
                }
            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
                success = false;
            }
            List<Review> reviews = reviewDAO.getAll();
            request.setAttribute("reviews", reviews);
            
            request.setAttribute("success", success);
            
        }

        if (userPath.equals("/adminDiscountDelete")) {
            response.sendRedirect("/ToyStore/adminDiscount");
        } else {
            String url = "/WEB-INF/view" + userPath + ".jsp";
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (ServletException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ServletContext application = getServletContext();
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        List<Category> categories = categoryDAO.getAll();
        application.setAttribute("categories", categories);
        
    }

}
