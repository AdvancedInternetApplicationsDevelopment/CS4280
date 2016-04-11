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
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Login> getAll()
    {
        List<Login> ret = new ArrayList<Login>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM login;").executeQuery();
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM login"
                    + " WHERE idlogin = ?;");
            ps.setString(1, idlogin);
            this.rs = ps.executeQuery();
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
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}