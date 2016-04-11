/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.OrderHistory;
import model.Product;

/**
 *
 * @author suhag
 */
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
            
            CustomerDAO customerDAO = new CustomerDAO();
            ProductDAO productDAO = new ProductDAO();
            OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
            noOfUsers = (customerDAO).getNoOfUsers();
            pendingApprovals = (productDAO).getAllPending().size();
            productsSold = (orderedProductDAO).getTotalQuantityOfProductSold();
            totalNoOfProducts = (productDAO).getNoOfProducts();
            latestApprovalItem = (productDAO).getLatestPending();
            if(latestApprovalItem.getId() == null)
            {
                latestApprovalItem=null;
            }
            transactions = (orderHistoryDAO).getLatest();
                        
            request.setAttribute("noOfUsers", noOfUsers);
            request.setAttribute("pendingApprovals", pendingApprovals);
            request.setAttribute("productsSold", productsSold);
            request.setAttribute("totalNoOfProducts", totalNoOfProducts);
            request.setAttribute("latestApprovalItem", latestApprovalItem);
            request.setAttribute("transactions", transactions);
            customerDAO.closeDB();
            productDAO.closeDB();
            orderHistoryDAO.closeDB();
            orderedProductDAO.closeDB();
        }
        
        else if (userPath.equals("/adminCustomer")) {
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
            System.out.print(emailID+FName+ LName+ phone+ fax+ city+ postalCode+ country+ region);
            List<Customer> customerSearchList = null;
            if(!( FName.equalsIgnoreCase("") && LName.equalsIgnoreCase("") && emailID.equalsIgnoreCase("") && phone.equalsIgnoreCase("") && fax.equalsIgnoreCase("") && city.equalsIgnoreCase("") && country.equalsIgnoreCase("") && region.equalsIgnoreCase("") && postalCode.equalsIgnoreCase(""))){
                CustomerDAO customerDAO = new CustomerDAO();
                customerSearchList = customerDAO.getByFilter(emailID, FName, LName, phone, fax, city, postalCode, country, region);
                customerDAO.closeDB();
            }
            request.setAttribute("FName", FName);
            request.setAttribute("LName", LName);
            request.setAttribute("emailID",emailID);
            request.setAttribute("phone", phone);
            request.setAttribute("fax",fax);
            request.setAttribute("city",city);
            request.setAttribute("postalCode", postalCode);
            request.setAttribute("country",country);
            request.setAttribute("region", region);
            request.setAttribute("customerSearchList", customerSearchList);
        }
        
        if(userPath.equals("/approveProduct"))
        {
            ProductDAO productDAO = new ProductDAO();
//            productDAO.approveProduct(request.getParameter("productId"));
            userPath = "/adminDashboard";
        }
        
        
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
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
