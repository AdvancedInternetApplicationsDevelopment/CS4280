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
import model.OrderedProduct;

/**
 *
 * @author Ninad
 */

public class OrderedProductDAO
{
    Connection conn;
    ResultSet rs;
    
    public OrderedProductDAO()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","localdb","Localdb123");
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<OrderedProduct> getAll()
    {
        List<OrderedProduct> ret = new ArrayList<OrderedProduct>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM mydb.ordered_product;").executeQuery();
            while(this.rs.next())
            {
                OrderedProduct order = new OrderedProduct();
                OrderHistoryDAO orderHistoryDAO;
                orderHistoryDAO = new OrderHistoryDAO();
                order.setOrderHistory(orderHistoryDAO.getOrderHistoryFromID(this.rs.getString("order_id")));
                ProductDAO productDAO = new ProductDAO();
                order.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setQuantity(this.rs.getInt("quantity"));
                ret.add(order);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public OrderedProduct getOrderHistoryFromID(String order_id, String product_id)
    {
        OrderedProduct ret = new OrderedProduct();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM mydb.ordered_product"
                    + "WHERE order_id = ? AND product_id = ?;");
            ps.setString(1, order_id);
            ps.setString(2, product_id);
            while(this.rs.next())
            {
                OrderedProduct order = new OrderedProduct();
                OrderHistoryDAO orderHistoryDAO;
                orderHistoryDAO = new OrderHistoryDAO();
                order.setOrderHistory(orderHistoryDAO.getOrderHistoryFromID(this.rs.getString("order_id")));
                ProductDAO productDAO = new ProductDAO();
                order.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setQuantity(this.rs.getInt("quantity"));
                ret = order;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
}