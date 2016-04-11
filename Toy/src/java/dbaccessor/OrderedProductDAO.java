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
import model.Order;
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
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<OrderedProduct> getAll()
    {
        List<OrderedProduct> ret = new ArrayList<OrderedProduct>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM ordered_product;").executeQuery();
            while(this.rs.next())
            {
                OrderedProduct order = new OrderedProduct();
                OrderHistoryDAO orderHistoryDAO;
                orderHistoryDAO = new OrderHistoryDAO();
                order.setOrderHistory(orderHistoryDAO.getOrderHistoryFromID(this.rs.getString("order_id")));
                orderHistoryDAO.closeDB();
                ProductDAO productDAO = new ProductDAO();
                order.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                productDAO.closeDB();
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ordered_product"
                    + " WHERE order_id = ? AND product_id = ?;");
            ps.setString(1, order_id);
            ps.setString(2, product_id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                OrderedProduct order = new OrderedProduct();
                OrderHistoryDAO orderHistoryDAO;
                orderHistoryDAO = new OrderHistoryDAO();
                order.setOrderHistory(orderHistoryDAO.getOrderHistoryFromID(this.rs.getString("order_id")));
                orderHistoryDAO.closeDB();
                ProductDAO productDAO = new ProductDAO();
                order.setProduct(productDAO.getProductFromID(this.rs.getString("product_id")));
                productDAO.closeDB();
                order.setQuantity(this.rs.getInt("quantity"));
                ret = order;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getTotalQuantityFromProductID(String product_id)
    {
        int ret = 0;
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT SUM(quantity)"
                    + " FROM ordered_product WHERE product_id = ?;");
            ps.setString(1, product_id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("SUM(quantity)");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public int getTotalQuantityOfProductSold()
    {
        int ret = 0;
        try
        {
            this.rs = conn.prepareStatement("SELECT SUM(quantity) as total"
                    + " FROM ordered_product;").executeQuery();
            while(this.rs.next())
            {
                ret = this.rs.getInt("total");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public int addOrder(String orderID, List<Order> order)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps;
            for(Order ordered: order)
            {
                ps = conn.prepareStatement("INSERT INTO ordered_product "
                        + "(order_id, product_id, quantity) "
                        + "VALUES(?, ?, ?)");
                ps.setString(1, orderID);
                ps.setString(2, ordered.getProductID());
                ps.setInt(3, ordered.getQuantity());
                
                ProductDAO productDAO = new ProductDAO();
                int quantity = productDAO.getQuantityById(ordered.getProductID())
                        - ordered.getQuantity();
                productDAO.updateQuantity(ordered.getProductID(), quantity);
                productDAO.closeDB();

                rows = ps.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderedProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }
}