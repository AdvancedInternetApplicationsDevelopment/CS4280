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
import model.Review;

/**
 *
 * @author Ninad
 */

public class ReviewDAO
{
    Connection conn;
    ResultSet rs;
    
    public ReviewDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Review> getAll()
    {
        List<Review> ret = new ArrayList<Review>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM review;").executeQuery();
            while(this.rs.next())
            {
                Review review = new Review();
                CustomerDAO customerDAO = new CustomerDAO();
                review.setCustomer(customerDAO.getCustomerFromID(this.rs.getString("customer_id")));
                ProductDAO productDAO = new ProductDAO();
                review.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                review.setComments(this.rs.getString("comments"));
                ret.add(review);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Review getReviewFromID(String customer_id, String product_id)
    {
        Review ret = new Review();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM review"
                    + " WHERE customer_id = ? AND product_id = ?;");
            ps.setString(1, customer_id);
            ps.setString(2, product_id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Review review = new Review();
                CustomerDAO customerDAO = new CustomerDAO();
                review.setCustomer(customerDAO.getCustomerFromID(this.rs.getString("customer_id")));
                ProductDAO productDAO = new ProductDAO();
                review.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                review.setComments(this.rs.getString("comments"));
                review.setStar(this.rs.getInt("star"));
                ret = review;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getAvgStarFromProductID(String product_id)
    {
        int ret = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT AVG(star)"
                    + " FROM review WHERE product_id = ?;");
            ps.setString(1, product_id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("AVG(star)");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}