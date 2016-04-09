/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

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
                product.setPrice(this.rs.getInt("price"));
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
                product.setPrice(this.rs.getInt("price"));
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
                    + " SUM(ordered_product.quantity) AS quantity"
                    + " FROM product, ordered_product"
                    + " WHERE  product.id = ordered_product.product_id"
                    + " GROUP BY product.id ORDER BY amount DESC LIMIT 1");
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
                product.setPrice(this.rs.getInt("price"));
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
                    product.setImage(this.rs.getBytes("image"));
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
                product.setPrice(this.rs.getInt("price"));
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
                product.setPrice(this.rs.getInt("price"));
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
}