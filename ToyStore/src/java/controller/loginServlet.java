/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.CustomerDAO;
import dbaccessor.CustomerDAOImpl;
import dbaccessor.LoginDAO;
import dbaccessor.LoginDAOImpl;
import dbaccessor.ProductDAO;
import dbaccessor.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.Login;
import model.Product;
import saltHash.SaltHash;

/**
 *
 * @author suhag
 */
public class loginServlet extends HttpServlet {

    String userPath;
    HttpSession session;

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

        userPath = request.getServletPath();
        String url = "/WEB-INF/" + userPath + ".jsp";
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
        if (userPath.equals("/login")) {
            String email = request.getParameter("userName");
            String pass1 = request.getParameter("password");
            char[] pass = pass1.toCharArray();
            boolean validate = true;
            try {
                LoginDAO loginDAO = new LoginDAOImpl();
                Login login = loginDAO.getLoginFromID(email);
                if (login != null) {
                    byte[] salt = (login.getSalt()).getBytes();
                    char[] tempPass = pass1.toCharArray();
                    byte[] hash = (login.getIdpass()).getBytes();
//                    validate = SaltHash.isExpectedPassword(pass, salt, hash);
                    if (!(validate)) {
                        response.sendRedirect("/ToyStore/loginErrorCustomer");
                    } else {
                        session = request.getSession(true);
                        CustomerDAO customerDAO = new CustomerDAOImpl();
                        Customer customer = customerDAO.getCustomerFromID(email);
                        session.setAttribute("customerFname", customer.getFname());
                        session.setAttribute("customerEmail", customer.getEmail());
                        session.setAttribute("wishListOfProducts", null);
                        session.setAttribute("compareProducts", null);
                        response.sendRedirect("/ToyStore/home");
                    }
                } else {
                    response.sendRedirect("/ToyStore/loginErrorCustomer");
                }

            } catch (Exception e) {
                response.sendRedirect("/ToyStore/loginErrorCustomer");
            }

        }
        if (userPath.equals("/register")) {
            String email = request.getParameter("userName");
            String pass1 = request.getParameter("password");
            char[] pass = pass1.toCharArray();

            boolean validate = false;
            try {
                LoginDAO loginDAO = new LoginDAOImpl();
                Login login = loginDAO.getLoginFromID(email);
                if (login != null) {
                    byte[] salt = (login.getSalt()).getBytes();
                    char[] tempPass = pass1.toCharArray();
                    byte[] hash = (login.getIdpass()).getBytes();
                    validate = SaltHash.isExpectedPassword(pass, salt, hash);
                    if (!(validate)) {
                        response.sendRedirect("/ToyStore/loginErrorCustomer");
                    } else {
                        session = request.getSession(true);
                        CustomerDAO customerDAO = new CustomerDAOImpl();
                        Customer customer = customerDAO.getCustomerFromID(email);
                        session.setAttribute("customerFname", customer.getFname());
                        session.setAttribute("customerEmail", customer.getEmail());
                        session.setAttribute("wishListOfProducts", null);
                        session.setAttribute("compareProducts", null);
                        ProductDAO productDAO = new ProductDAOImpl();
                        List<Product> latestProducts = productDAO.getLatest();
                        List<Product> latestRecycledProducts = productDAO.getLatestRecycled();
                        request.setAttribute("latestRecycledProducts", latestRecycledProducts);
                        request.setAttribute("latestProducts", latestProducts);
                        response.sendRedirect("/ToyStore/home");
                    }
                } else {
                    response.sendRedirect("/ToyStore/loginErrorCustomer");
                }

            } catch (Exception e) {
                response.sendRedirect("/ToyStore/loginErrorCustomer");
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

}
