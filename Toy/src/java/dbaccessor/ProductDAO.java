/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Ninad
 */

public class ProductDAO
{
    Connection conn;
    ResultSet rs;
    
    public ProductDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeDB()
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Product> getAll()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM product;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getNoOfProducts()
    {
        int ret = 0;
        try
        {
            this.rs = conn.prepareStatement("SELECT COUNT(*)"
                    + " as num FROM product;").executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("num");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getProductFromID(String id)
    {
        Product ret = new Product();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                ret = product;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getBestSelling()
    {
        Product ret = new Product();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT product.name,"
                    + " SUM(ordered_product.quantity) AS totquantity"
                    + " FROM product, ordered_product"
                    + " WHERE  product.id = ordered_product.product_id"
                    + " GROUP BY product.id ORDER BY totquantity DESC LIMIT 1");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                String name = rs.getString("name");
                ret = getProductFromName(name);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public Product getProductFromName(String name)
    {
        Product ret = new Product();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE name LIKE ?;");
            ps.setString(1, "%" + name + "%");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                ret = product;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public ProductImage getImageByID(String id)
    {
        ProductImage ret = new ProductImage();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product"
                        + " WHERE id = ?;");
                ps.setString(1, id);
                this.rs = ps.executeQuery();
                while(this.rs.next())
                {
                    ProductImage product = new ProductImage();
                    product.setId(this.rs.getString("id"));
                    product.setImage(this.rs.getBlob("image"));
                    ret = product;
                }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getAllPending()
    {
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE product.new = 0;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Product getLatestPending()
    {
        Product ret = new Product();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM product"
                    + " WHERE product.new = 1"
                    + " ORDER BY last_update DESC"
                    + " LIMIT 1;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                ret = product;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<Product> getByFilter(String name, String modelNum, int categoryId)
    {
        String query = getQueryFromFilter(name, modelNum, categoryId);
        
        List<Product> ret = new ArrayList<Product>();
        try
        {
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setModelNum(this.rs.getString("model_num"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setQuantity(this.rs.getInt("quantity"));
                product.setAvailable(this.rs.getBoolean("available"));
                product.setPrice(this.rs.getDouble("price"));
                product.setBrand(this.rs.getString("brand"));
                product.setDescription(this.rs.getString("description"));
                product.setAddInfo(this.rs.getString("add_info"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                product.setNew1(this.rs.getBoolean("new"));
                product.setApproved(this.rs.getBoolean("approved"));
                product.setOwner(this.rs.getString("owner"));
                
                ret.add(product);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String getQueryFromFilter(String name, String modelNum, int categoryId)
    {
        List<String> query = new ArrayList<String>();
        query.add("SELECT * FROM product");
        query.add(" WHERE");
        if(!name.equalsIgnoreCase(""))
        {
            query.add(" name LIKE '%" + name + "%'");
        }
        if(!modelNum.equalsIgnoreCase(""))
        {
            query.add(" model_num LIKE '%" + modelNum + "%'");
        }
        if(categoryId  != 0)
        {
            query.add(" category_id = '" + categoryId + "'");
        }
        
        query.add(";");
        
        String ret = "";
        
        ret += query.get(0) + query.get(1);
        
        for(int i = 2; i < (query.size() - 2); i++)
        {
            ret += query.get(i);
            if(!(query.get(i + 1).equalsIgnoreCase(";")))
            {
                ret += " AND";
            }
        }
        
        ret += query.get(query.size() - 1);
        
        return ret;
    }
    
    public boolean addProduct(Product product, Blob image)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO product "
                    + "(id, name, modelNum, categoryId, quantity, available, price, "
                    + "brand, description, addInfo, image, lastUpdate, new, approved) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, product.getName());
            ps.setString(3, product.getModelNum());
            ps.setInt(4, product.getCategoryId().getId());
            ps.setInt(5, product.getQuantity());
            ps.setBoolean(6, product.isAvailable());
            ps.setDouble(7, product.getPrice());
            ps.setString(8, product.getBrand());
            ps.setString(9, product.getDescription());
            ps.setString(10, product.getAddInfo());
            ps.setBlob(11, image);
            ps.setTimestamp(12, product.getLastUpdate());
            ps.setBoolean(13, product.isNew1());
            ps.setBoolean(14, product.isApproved());
            ps.setString(15, product.getOwner());
            
            rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updateProduct(Product product)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET "
                    + "name = ?, "
                    + "modelNum = ?, "
                    + "categoryId = ?, "
                    + "quantity = ?, "
                    + "available = ?, "
                    + "price = ?, "
                    + "brand = ?, "
                    + "description = ?, "
                    + "addInfo = ?, "
                    + "lastUpdate = ?, "
                    + "new = ?, "
                    + "approved = ?, "
                    + "owner = ?"
                    + " WHERE id = ?;");
            ps.setString(1, product.getName());
            ps.setString(2, product.getModelNum());
            ps.setInt(3, product.getCategoryId().getId());
            ps.setInt(4, product.getQuantity());
            ps.setBoolean(5, product.isAvailable());
            ps.setDouble(6, product.getPrice());
            ps.setString(7, product.getBrand());
            ps.setString(8, product.getDescription());
            ps.setString(9, product.getAddInfo());
            ps.setTimestamp(10, product.getLastUpdate());
            ps.setBoolean(11, product.isNew1());
            ps.setBoolean(12, product.isApproved());
            ps.setString(13, product.getOwner());
            ps.setString(14, product.getId());
            
            rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updateImageByID(String id, Blob image)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("UPDATE product SET"
                        + " image = ? WHERE id = ?;");
                ps.setBlob(1, image);
                ps.setString(2, id);
                rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public String[] getRelated(String productId)
    {
        /*SELECT DISTINCT(product_id) FROM ordered_product WHERE order_id IN
            (SELECT order_id FROM order_history WHERE customer_id IN
            (SELECT customer_id FROM order_history WHERE order_id IN
            (SELECT order_id FROM ordered_product WHERE product_id = "1"))) LIMIT 4;
           */
        String[] related = {"", "", "", ""};
        return related;
    }
}