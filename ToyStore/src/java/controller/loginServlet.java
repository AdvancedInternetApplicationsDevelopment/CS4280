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
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CCInfo;
import model.Customer;
import model.Login;
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
        if (userPath.equals("/register")) {
            request.setAttribute("error", false);
            request.setAttribute("errorMessage", null);
        }
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
            String email = request.getParameter("email");
            String pass1 = request.getParameter("password");
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
            char[] pass = pass1.toCharArray();

            try {
                LoginDAO loginDAO = new LoginDAOImpl();
                byte[] salt = SaltHash.getNextSalt();
                byte[] hash = SaltHash.hash(pass, salt);
                Login login;
                login = new Login();
                login.setIdlogin(email);
                login.setIdpass(new String(hash));
                login.setSalt(new String(salt));
                loginDAO.addLogin(login);
                int ccv = Integer.parseInt(ccvS);
                CustomerDAO customerDAO = new CustomerDAOImpl();
                Customer customer = new Customer(email, fname, lname, phone, fax, address1, address2, city, postal, country, region, ccNumber, 0);
                ccInfo = new CCInfo(ccNumber, cardHolder, email, expiryDate, ccv);
                if (!(customerDAO.addCustomer(customer, ccInfo))) {
                    throw new Exception("Cannot add customer details.");
                }
                
                response.sendRedirect("/ToyStore/login");

            } catch (Exception e) {
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", e.getMessage());
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
