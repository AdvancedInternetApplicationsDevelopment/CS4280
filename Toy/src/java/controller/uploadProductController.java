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
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            // if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

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

        Map<String, String> formData = new HashMap<String, String>();
        InputStream fileContent = null; // input stream of the upload file
        boolean success = true;
        ProductDAO productDAO = new ProductDAOImpl();
        Product product;
        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        File file = new File(item.getName());
                        String fileName = file.getName();
                        fileContent = item.getInputStream();
                        // gets absolute path of the web application
                        String appPath = request.getServletContext().getRealPath("");
                        // constructs path of the directory to save uploaded file
                        String savePath = appPath + File.separator + SAVE_DIR;

                        // creates the save directory if it does not exists
                        File fileSaveDir = new File(savePath);
                        if (!fileSaveDir.exists()) {
                            fileSaveDir.mkdir();
                        }
                        
                    } else {
                        String itemName = item.getFieldName();
                        String itemVal = item.getString();
                        formData.put(itemName, itemVal);
                    }
                }
            }
            // saves the file on disk
        } catch (Exception ex) {
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", ex.getMessage());
            success = false;
            return;
        }
        String productId = null;
        String pName = formData.get("pName");
        String mNo = formData.get("mNo");
        String brand = formData.get("brand");
        String description = formData.get("description");
        String quantity = formData.get("quantity");
        String price = formData.get("price");
        String addInfo = formData.get("addInfo");
        int categoryId = Integer.parseInt(formData.get("category"));

        try {
            double priceDouble = Double.parseDouble(price);
            int quantityInt = Integer.parseInt(quantity);
            Category category = (new CategoryDAOImpl()).getCategoryFromID(categoryId);
            product = new Product(productId, pName, mNo, category, quantityInt, true, priceDouble, brand, description, addInfo, true, true, "shop");

            if (!(productDAO.addProduct(product, null, category.getName(), false, "shop"))) {
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
        // redirects client to message page
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
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
        processRequest(request, response);
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
