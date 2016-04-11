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

public class CategoryDAO
{
    Connection conn;
    ResultSet rs;
    
    public CategoryDAO()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource)envCtx.lookup("jdbc/toy");
            this.conn = ds.getConnection();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NamingException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Category> getAll()
    {
        List<Category> ret = new ArrayList<Category>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM category;").executeQuery();
            while(this.rs.next())
            {
                Category cat = new Category();
                cat.setId(this.rs.getInt("id"));
                cat.setName(this.rs.getString("name"));
                ret.add(cat);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Category getCategoryFromID(int id)
    {
        Category ret = new Category();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category"
                    + " WHERE id = ?;");
            ps.setInt(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Category cat = new Category();
                cat.setId(this.rs.getInt("id"));
                cat.setName(this.rs.getString("name"));
                ret = cat;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getCategoryIDFromName(String name)
    {
        int ret = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category"
                    + " WHERE name LIKE ?;");
            ps.setString(1, "%" + name + "%");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("id");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}