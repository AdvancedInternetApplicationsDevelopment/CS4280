/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Product;

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

            request.setAttribute("brands", brands);
            request.setAttribute("products", products);
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

            List<String> brands = productDAO.listAllBrands();
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
            }
            else
            {
                products = productDAO.getAll();
            }

            
            request.setAttribute("brands", brands);
            request.setAttribute("products", products);

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
