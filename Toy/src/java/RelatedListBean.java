/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ninad
 */

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class RelatedListBean
{
    int productprice;
    int productid;
    public RelatedListBean(String product)
    {
        initializeList(product);
    }

    private void initializeList(String product)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","ninad123");
            PreparedStatement statement = conn.prepareStatement("SELECT id, price FROM mydb.product WHERE name = ?");
            statement.setString(1, product);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                productid = rs.getInt("id");
		productprice = rs.getInt("price");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(RelatedListBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(RelatedListBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
