/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Review;

/**
 *
 * @author Ninad
 */

public class ReviewDAOImpl implements ReviewDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public ReviewDAOImpl()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/toy");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Review> getAll()
    {
        List<Review> ret = new ArrayList<Review>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM review;").executeQuery();
            while(this.rs.next())
            {
                Review review = new Review();
                CustomerDAO customerDAO = new CustomerDAOImpl();
                review.setCustomer(customerDAO.getCustomerFromID(this.rs.getString("customer_id")));
                ProductDAO productDAO = new ProductDAOImpl();
                review.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                review.setComments(this.rs.getString("comments"));
                review.setAdminReply(this.rs.getString("admin_reply"));
                ret.add(review);
            }
            
            if (rs != null)
            {
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Review getReviewFromID(String customer_id, String product_id)
    {
        Review ret = null;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM review"
                    + " WHERE customer_id = ? AND product_id = ?;");
            ps.setString(1, customer_id);
            ps.setString(2, product_id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Review review = new Review();
                CustomerDAO customerDAO = new CustomerDAOImpl();
                review.setCustomer(customerDAO.getCustomerFromID(this.rs.getString("customer_id")));
                ProductDAO productDAO = new ProductDAOImpl();
                review.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                review.setComments(this.rs.getString("comments"));
                review.setStar(this.rs.getInt("star"));
                ret = review;
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getAvgStarFromProductID(String product_id)
    {
        int ret = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT AVG(star)"
                    + " FROM review WHERE product_id = ? ;");
            ps.setString(1, product_id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getInt("AVG(star)");
            }
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public boolean addReview(Review review)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO review "
                    + "(customer_id, product_id, comments, star) "
                    + "VALUES(?, ?, ?, ?);");
            ps.setString(1, review.getCustomer().getEmail());
            ps.setString(2, review.getProduct().getId());
            ps.setString(3, review.getComments());
            ps.setInt(4, review.getStar());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean addAdminReply(Review review)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE review "
                    + "SET admin_reply = ? "
                    + "WHERE customer_id = ? AND product_id = ? ;");
            ps.setString(1, review.getAdminReply());
            ps.setString(2, review.getCustomer().getEmail());
            ps.setString(3, review.getProduct().getId());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean deleteReview(Review review)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM review "
                    + "WHERE customer_id = ? AND product_id = ? ;");
            ps.setString(1, review.getCustomer().getEmail());
            ps.setString(2, review.getProduct().getId());
            
            rows = ps.executeUpdate();
            
            if (rs != null)
            {
                ps.close();
                rs.close();
            }
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ReviewDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}
