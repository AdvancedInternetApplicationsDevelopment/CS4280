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
import model.Category;

/**
 *
 * @author Ninad
 */

public class CategoryDAOImpl implements CategoryDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public CategoryDAOImpl()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/toy");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Category> getAll()
    {
        List<Category> ret = new ArrayList<Category>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM category;").executeQuery();
            while(this.rs.next())
            {
                Category cat = new Category();
                cat.setId(this.rs.getInt("id"));
                cat.setName(this.rs.getString("name"));
                ret.add(cat);
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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Category getCategoryFromID(int id)
    {
        Category ret = null;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category"
                    + " WHERE id = ?;");
            ps.setInt(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                Category cat = new Category();
                cat.setId(this.rs.getInt("id"));
                cat.setName(this.rs.getString("name"));
                ret = cat;
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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getCategoryIDFromName(String name)
    {
        int ret = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category"
                    + " WHERE name LIKE ?;");
            ps.setString(1, "%" + name + "%");
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                ret = this.rs.getInt("id");
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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public boolean categoryExists(String category)
    {
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category"
                    + " WHERE name LIKE ?;");
            ps.setString(1, "%" + category + "%");
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                return true;
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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addCategory(String category)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO category "
                    + "(name) "
                    + "VALUES(?)");
            ps.setString(1, category);
            
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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}