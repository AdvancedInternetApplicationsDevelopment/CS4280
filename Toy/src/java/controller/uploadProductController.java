/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.CategoryDAOImpl;
import dbaccessor.ProductDAO;
import dbaccessor.ProductDAOImpl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import model.Category;
import model.Product;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author suhag
 */
public class uploadProductController extends HttpServlet {

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "productImages";

   

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
        if (userPath.equals("/uploadProduct")) {

            String productId= null;
            String pName = request.getParameter("pName");
            String mNo = request.getParameter("mNo");
            String brand = request.getParameter("brand");
            String description = request.getParameter("description");
            String quantity = request.getParameter("quantity");
            String price = request.getParameter("price");
            String addInfo = request.getParameter("addInfo");
            int categoryId = Integer.parseInt(request.getParameter("category"));
            String owner = (String) session.getAttribute("customerEmail");
            boolean success = true;
            ProductDAO productDAO = new ProductDAOImpl();
            Product product;
            

            InputStream inputStream = null; // input stream of the upload file

            // obtains the upload file part in this multipart request
            
            Blob image = null;
            try {
                image = new SerialBlob(IOUtils.toByteArray(inputStream));
            } catch (SQLException ex) {
                Logger.getLogger(adminControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                double priceDouble = Double.parseDouble(price);
                int quantityInt = Integer.parseInt(quantity);
                Category category = (new CategoryDAOImpl()).getCategoryFromID(categoryId);
                product = new Product(productId, pName, mNo, category, quantityInt, priceDouble, brand, description, addInfo, true, true, owner);

                if (!(productDAO.addProduct(product ,category.getName()))) {
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
            request.setAttribute("success", success);
            if (success == true) {
                request.setAttribute("error", false);
                request.setAttribute("errorMessage", null);
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
