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
            this.rs = conn.prepareStatement("SELECT * FROM mydb.product;").executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setPrice(this.rs.getInt("price"));
                product.setDescription(this.rs.getString("description"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setNew1(this.rs.getBoolean("new"));
                product.setImage(this.rs.getBytes("image"));
                product.setApproved(this.rs.getBoolean("approved"));
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mydb.product"
                    + "WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Product product = new Product();
                product.setId(this.rs.getString("id"));
                product.setName(this.rs.getString("name"));
                product.setPrice(this.rs.getInt("price"));
                product.setDescription(this.rs.getString("description"));
                product.setLastUpdate(this.rs.getTimestamp("last_update"));
                CategoryDAO categoryDAO = new CategoryDAO();
                product.setCategoryId(categoryDAO.getCategoryFromID(this.rs.getInt("category_id")));
                product.setNew1(this.rs.getBoolean("new"));
                product.setImage(this.rs.getBytes("image"));
                product.setApproved(this.rs.getBoolean("approved"));
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