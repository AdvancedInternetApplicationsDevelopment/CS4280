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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        userPath = request.getServletPath();
        
        if (userPath.equals("/adminDashboard")) {
            
            noOfUsers = (new CustomerDAO()).getNoOfUsers();
            pendingApprovals = (new ProductDAO()).getAllPending().size();
            productsSold = (new OrderedProductDAO()).getTotalQuantityOfProductSold();
            totalNoOfProducts = (new ProductDAO()).getNoOfProducts();
            latestApprovalItem = (new ProductDAO()).getLatestPending();
            System.out.println(latestApprovalItem);
            if(latestApprovalItem.getId() == null)
            {
                System.out.println("its null bro ");
                latestApprovalItem=null;
            }
            transactions = (new OrderHistoryDAO()).getLatest();
                        
            request.setAttribute("noOfUsers", noOfUsers);
            request.setAttribute("pendingApprovals", pendingApprovals);
            request.setAttribute("productsSold", productsSold);
            request.setAttribute("totalNoOfProducts", totalNoOfProducts);
            request.setAttribute("latestApprovalItem", latestApprovalItem);
            request.setAttribute("transactions", transactions);
        }
        
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException ex) {
            String urlNotFound = "/WEB-INF/view/admin404.jsp";
            request.getRequestDispatcher(urlNotFound).forward(request, response);
        } catch (IOException ex) {
            String urlNotFound = "/WEB-INF/view/admin404.jsp";
            request.getRequestDispatcher(urlNotFound).forward(request, response);
        }
    }
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
        processRequest(request,response);
        
        
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
        doGet(request, response);
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
