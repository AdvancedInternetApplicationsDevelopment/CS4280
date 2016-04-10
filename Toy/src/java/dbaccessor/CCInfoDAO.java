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
import model.CCInfo;

/**
 *
 * @author Ninad
 */
class CCInfoDAO
{
    Connection conn;
    ResultSet rs;
    
    public CCInfoDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
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
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<CCInfo> getAll()
    {
        List<CCInfo> ret = new ArrayList<CCInfo>();
        try
        {
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
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public CCInfo getCcInfoFromID(String email)
    {
        CCInfo ret = new CCInfo();
        try
        {
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
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CCInfoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}
