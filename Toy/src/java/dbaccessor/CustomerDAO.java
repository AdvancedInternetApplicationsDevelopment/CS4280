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
import model.Customer;

/**
 *
 * @author Ninad
 */

public class CustomerDAO
{
    Connection conn;
    ResultSet rs;
    
    public CustomerDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<Customer> getAll()
    {
        List<Customer> ret = new ArrayList<Customer>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM customer;").executeQuery();
            while(this.rs.next())
            {
                Customer cus = new Customer();
                cus.setEmail(this.rs.getString("email"));
                cus.setFname(this.rs.getString("fname"));
                cus.setLname(this.rs.getString("lname"));
                cus.setPhone(this.rs.getString("phone"));
                cus.setFax(this.rs.getString("fax"));
                cus.setAddress1(this.rs.getString("address1"));
                cus.setAddress2(this.rs.getString("address2"));
                cus.setCity(this.rs.getString("city"));
                cus.setPostal(this.rs.getString("postal"));
                cus.setCountry(this.rs.getString("country"));
                cus.setRegion(this.rs.getString("region"));
                cus.setCcNumber(this.rs.getString("cc_number"));
                cus.setCredits(this.rs.getInt("credits"));
                CCInfoDAO ccInfoDAO = new CCInfoDAO();
                cus.setCcInfo(ccInfoDAO.getCcInfoFromID(this.rs.getString("email")));
                LoginDAO loginDAO = new LoginDAO();
                cus.setLogin(loginDAO.getLoginFromID(this.rs.getString("email")));
                ret.add(cus);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Customer getCustomerFromID(String email)
    {
        Customer ret = new Customer();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM customer"
                    + " WHERE email = ?;");
            ps.setString(1, email);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                Customer cus = new Customer();
                cus.setEmail(this.rs.getString("email"));
                cus.setFname(this.rs.getString("fname"));
                cus.setLname(this.rs.getString("lname"));
                cus.setPhone(this.rs.getString("phone"));
                cus.setFax(this.rs.getString("fax"));
                cus.setAddress1(this.rs.getString("address1"));
                cus.setAddress2(this.rs.getString("address2"));
                cus.setCity(this.rs.getString("city"));
                cus.setPostal(this.rs.getString("postal"));
                cus.setCountry(this.rs.getString("country"));
                cus.setRegion(this.rs.getString("region"));
                cus.setCcNumber(this.rs.getString("cc_number"));
                cus.setCredits(this.rs.getInt("credits"));
                CCInfoDAO ccInfoDAO = new CCInfoDAO();
//                cus.setCcInfo(ccInfoDAO.getCcInfoFromID(this.rs.getString("email")));
//                LoginDAO loginDAO = new LoginDAO();
//                cus.setLogin(loginDAO.getLoginFromID(this.rs.getString("email")));
                ret = cus;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getNoOfUsers()
    {
        int ret = 0;
        try
        {
            this.rs = conn.prepareStatement("SELECT COUNT(*)"
                    + " as num FROM customer;").executeQuery();
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
    
    public List<Customer> getByFilter(String email, String fname, String lname,
            String phone, String fax, String city, String postal, String country,
            String region)
    {
        String query = getQueryFromFilter(email, fname, lname,
            phone, fax, city, postal, country,
            region);
        
        List<Customer> ret = new ArrayList<Customer>();
        try
        {
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                Customer cus = new Customer();
                cus.setEmail(this.rs.getString("email"));
                cus.setFname(this.rs.getString("fname"));
                cus.setLname(this.rs.getString("lname"));
                cus.setPhone(this.rs.getString("phone"));
                cus.setFax(this.rs.getString("fax"));
                cus.setAddress1(this.rs.getString("address1"));
                cus.setAddress2(this.rs.getString("address2"));
                cus.setCity(this.rs.getString("city"));
                cus.setPostal(this.rs.getString("postal"));
                cus.setCountry(this.rs.getString("country"));
                cus.setRegion(this.rs.getString("region"));
                cus.setCcNumber(this.rs.getString("cc_number"));
                cus.setCredits(this.rs.getInt("credits"));
                CCInfoDAO ccInfoDAO = new CCInfoDAO();
                cus.setCcInfo(ccInfoDAO.getCcInfoFromID(this.rs.getString("email")));
                LoginDAO loginDAO = new LoginDAO();
                cus.setLogin(loginDAO.getLoginFromID(this.rs.getString("email")));
                ret.add(cus);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String getQueryFromFilter(String email, String fname, String lname,
            String phone, String fax, String city, String postal, String country,
            String region)
    {
        List<String> query = new ArrayList<String>();
        query.add("SELECT * FROM customer");
        query.add(" WHERE");
        if(!email.equalsIgnoreCase(""))
        {
            query.add(" email LIKE '%" + email + "%'");
        }
        if(!fname.equalsIgnoreCase(""))
        {
            query.add(" fname LIKE '%" + fname + "%'");
        }
        if(!lname.equalsIgnoreCase(""))
        {
            query.add(" lname LIKE '%" + lname + "%'");
        }
        if(!phone.equalsIgnoreCase(""))
        {
            query.add(" phone LIKE '%" + phone + "%'");
        }
        if(!fax.equalsIgnoreCase(""))
        {
            query.add(" fax LIKE '%" + fax + "%'");
        }
        if(!city.equalsIgnoreCase(""))
        {
            query.add(" city LIKE '%" + city + "%'");
        }
        if(!postal.equalsIgnoreCase(""))
        {
            query.add(" postal LIKE '%" + postal + "%'");
        }
        if(!country.equalsIgnoreCase(""))
        {
            query.add(" country LIKE '%" + country + "%'");
        }
        if(!region.equalsIgnoreCase(""))
        {
            query.add(" region LIKE '%" + region + "%'");
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
}