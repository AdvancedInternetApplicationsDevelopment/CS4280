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
import model.Discount;

/**
 *
 * @author Ninad
 */

public class DiscountDAO
{
    Connection conn;
    ResultSet rs;
    
    public DiscountDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Discount> getAll()
    {
        List<Discount> ret = new ArrayList<Discount>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM discount;").executeQuery();
            while(this.rs.next())
            {
                Discount discount = new Discount();
                discount.setDiscountCode(this.rs.getString("discount_code"));
                discount.setAmount(this.rs.getDouble("amount"));
                ret.add(discount);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Discount getDiscountFromID(String code)
    {
        Discount ret = new Discount();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM discount"
                    + " WHERE discount_code = ?;");
            ps.setString(1, "%" + code + "%");
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Discount discount = new Discount();
                discount.setDiscountCode(this.rs.getString("discount_code"));
                discount.setAmount(this.rs.getDouble("amount"));
                ret = discount;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public boolean addDiscount(Discount discount)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO discount "
                    + "(discount_code, amount) "
                    + "VALUES(?, ?)");
            ps.setString(1, discount.getDiscountCode());
            ps.setDouble(2, discount.getAmount());
            rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean deleteDiscount(Discount discount)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("DELETE discount "
                    + " WHERE discount_code = ?");
            ps.setString(1, discount.getDiscountCode());
            rows = ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}