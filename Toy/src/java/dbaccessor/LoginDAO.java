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
import model.Login;

/**
 *
 * @author Ninad
 */

public class LoginDAO
{
    Connection conn;
    ResultSet rs;
    
    public LoginDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Login> getAll()
    {
        List<Login> ret = new ArrayList<Login>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM mydb.login;").executeQuery();
            while(this.rs.next())
            {
                Login login = new Login();
                login.setIdlogin(this.rs.getString("idlogin"));
                login.setIdpass(this.rs.getString("idpass"));
                ret.add(login);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Login getLoginFromID(String idlogin)
    {
        Login ret = new Login();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mydb.login"
                    + "WHERE idlogin = ?;");
            ps.setString(1, idlogin);
            while(this.rs.next())
            {
                Login login = new Login();
                login.setIdlogin(this.rs.getString("idlogin"));
                login.setIdpass(this.rs.getString("idpass"));
                ret = login;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}