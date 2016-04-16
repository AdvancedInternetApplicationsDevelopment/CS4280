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
import model.CCInfo;

/**
 *
 * @author Ninad
 */
public class CCInfoDAOImpl implements CCInfoDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public CCInfoDAOImpl()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            this.ds = (DataSource)envCtx.lookup("jdbc/toy");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(CCInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<CCInfo> getAll()
    {
        List<CCInfo> ret = new ArrayList<CCInfo>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM cc_info;").executeQuery();
            while(this.rs.next())
            {
                CCInfo ccInfo = new CCInfo();
                ccInfo.setCcNumber(this.rs.getString("cc_number"));
                ccInfo.setCcName(this.rs.getString("cc_email"));
                ccInfo.setExpiryDate(this.rs.getDate("expiry_date"));
                ccInfo.setCcv(this.rs.getInt("ccv"));
                ret.add(ccInfo);
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
            Logger.getLogger(CCInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public CCInfo getCcInfoFromID(String email)
    {
        CCInfo ret = null;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cc_info"
                    + " WHERE cc_email = ?;");
            ps.setString(1, email);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                CCInfo ccInfo = new CCInfo();
                ccInfo.setCcNumber(this.rs.getString("cc_number"));
                ccInfo.setCcName(this.rs.getString("cc_email"));
                ccInfo.setExpiryDate(this.rs.getDate("expiry_date"));
                ccInfo.setCcv(this.rs.getInt("ccv"));
                ret = ccInfo;
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
            Logger.getLogger(CCInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public boolean addCcInfo(CCInfo ccinfo)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cc_info "
                    + "(cc_number, cc_email, expiry_date, ccv) "
                    + "VALUES(?, ?, ?, ?)");
            ps.setString(1, ccinfo.getCcNumber());
            ps.setString(2, ccinfo.getCcName());
            ps.setDate(3, ccinfo.getExpiryDate());
            ps.setInt(4, ccinfo.getCcv());
            
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
            Logger.getLogger(CCInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
    
    public boolean updateCcInfo(CCInfo ccinfo)
    {
        int rows = 0;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE cc_info SET "
                    + "cc_email = ?, "
                    + "expiry_date = ?, "
                    + "ccv = ? "
                    + " WHERE cc_number = ?;");
            ps.setString(1, ccinfo.getCcName());
            ps.setDate(2, ccinfo.getExpiryDate());
            ps.setInt(3, ccinfo.getCcv());
            ps.setString(4, ccinfo.getCcNumber());
            
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
            Logger.getLogger(CCInfoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rows > 0);
    }
}
