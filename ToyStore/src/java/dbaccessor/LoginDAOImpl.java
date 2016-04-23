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

public class LoginDAOImpl implements LoginDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public LoginDAOImpl()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/toy");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Login> getAll()
    {
        List<Login> ret = new ArrayList<Login>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM login;").executeQuery();
            while(this.rs.next())
            {
                Login login = new Login();
                login.setIdlogin(this.rs.getString("idlogin"));
                login.setIdpass(this.rs.getString("idpass"));
                ret.add(login);
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
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public Login getLoginFromID(String idlogin)
    {
        Login ret = null;
        try
        {
            this.conn = ds.getConnection();
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
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public boolean addLogin(Login login)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO login "
                    + "(idlogin, idpass) "
                    + "VALUES(?, ?);");
            ps.setString(1, login.getIdlogin());
            ps.setString(2, login.getIdpass());
            
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
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updatePass(String idLogin)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE login SET "
                    + "idpass = ? "
                    + " WHERE idlogin = ?;");
            ps.setString(1, idLogin);
            
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
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}