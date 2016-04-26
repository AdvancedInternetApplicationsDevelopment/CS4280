/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dbaccessor.CategoryDAOImpl;
import dbaccessor.ProductDAO;
import dbaccessor.ProductDAOImpl;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author suhag
 */
public class uploadProductController extends HttpServlet {

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
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
            boolean success = true;
            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // sets memory threshold - beyond which files are stored in disk
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            // sets temporary location to store files
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);

            // sets maximum size of upload file
            upload.setFileSizeMax(MAX_FILE_SIZE);

            // sets maximum size of request (include file + form data)
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            // this path is relative to application's directory
            String uploadPath = getServletContext().getInitParameter("upload.location");
            //creates a HashMap of all inputs
            HashMap hashMap = new HashMap();
            String productId = UUID.randomUUID().toString();
            try {
                @SuppressWarnings("unchecked")
                List<FileItem> formItems = upload.parseRequest(request);
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        File file = new File(item.getName());
                        File file2 = new File(productId + ".jpg");
                        String filePath = uploadPath + File.separator + file.getName();
                        // saves the file on disk
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        File rename = new File(filePath);
                        boolean flag = rename.renameTo(file2);
                    } else {
                        hashMap.put(item.getFieldName(), item.getString());
                    }
                }
            } catch (FileUploadException ex) {
                Logger.getLogger(uploadProductController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", ex.getMessage());
                success = false;
            } catch (Exception ex) {
                Logger.getLogger(uploadProductController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("error", true);
                request.setAttribute("errorMessage", ex.getMessage());
                success = false;
            }
            String owner = (String) session.getAttribute("customerEmail");
            if (owner == null) {
                owner = "shop";
            }

            String pName = hashMap.get("pName").toString();
            String mNo = hashMap.get("mNo").toString();
            String brand = hashMap.get("brand").toString();
            String description = hashMap.get("description").toString();
            String quantity = hashMap.get("quantity").toString();
            String price = hashMap.get("price").toString();
            String addInfo = hashMap.get("addInfo").toString();
            int categoryId = Integer.parseInt(hashMap.get("category").toString());

            ProductDAO productDAO = new ProductDAOImpl();
            Product product;

            try {
                double priceDouble = Double.parseDouble(price);
                int quantityInt = Integer.parseInt(quantity);
                Category category = (new CategoryDAOImpl()).getCategoryFromID(categoryId);
                if(owner.equals("shop"))
                {
                    product = new Product(productId, pName, mNo, category, quantityInt, priceDouble, brand, description, addInfo, true, true, owner);
                }
                else
                {
                    product = new Product(productId, pName, mNo, category, quantityInt, priceDouble, brand, description, addInfo, false, false, owner);
                }
                

                if (!(productDAO.addProduct(product, category.getName()))) {
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
            String url;
            if (owner.equals("shop")) {
                url = "/WEB-INF/view/adminAddProducts.jsp";
            } else {
                url = "/WEB-INF/view/clientSideView/addRecycleProduct.jsp";
            }
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

}
