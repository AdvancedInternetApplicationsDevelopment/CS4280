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
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.Order;
import model.OrderHistory;

/**
 *
 * @author Ninad
 */

public class OrderHistoryDAO
{
    Connection conn;
    ResultSet rs;
    
    public OrderHistoryDAO()
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
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<OrderHistory> getAll()
    {
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM order_history;").executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAO();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                dao.closeDB();
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                ret.add(order);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public OrderHistory getOrderHistoryFromID(String id)
    {
        OrderHistory ret = new OrderHistory();
        try
        {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM order_history"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAO();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                dao.closeDB();
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                ret = order;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<OrderHistory> getLatest()
    {
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.rs = conn.prepareStatement("SELECT * FROM order_history"
                    + " ORDER BY date_created DESC LIMIT 8;").executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAO();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                dao.closeDB();
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                ret.add(order);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    //TODO: not done yet
    public List<OrderHistory> getByFilter(String customerId, String productId)
    {
        String query = "";
        
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAO();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                dao.closeDB();
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                
                ret.add(order);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public synchronized String checkout(List<Order> order, String discountCode, double credit, String customerId)
    {
        int rows = 0;
        try
        {
            PreparedStatement ps;
            
            //Check if discount code is valid
            double disAmount = 0.0;
            ps = conn.prepareStatement("SELECT amount FROM discount"
                    + " WHERE discount_code = ?;");
            ps.setString(1, discountCode);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                disAmount = this.rs.getDouble("amount");
                if(!(disAmount > 0))
                {
                    return "Error: Dicount Code is not valid.";
                }
            }
            
            double amount = 0.0;
            
            //Check if input quantity is excessive
            ProductDAO productDAO = new ProductDAO();
            for(Order ordered: order)
            {
                String productId = ordered.getProductID();
                int quantity = ordered.getQuantity();
                if(quantity > productDAO.getQuantityById(productId))
                {
                    return ("Error: Entered Product Quantity for " + productDAO.getNameById(productId) + " is excessive.");
                }
                else
                {
                    amount += (productDAO.getPriceById(productId) * quantity);
                }
            }
            productDAO.closeDB();
            
            //Check is account credits are enough for this transaction
            ps = conn.prepareStatement("SELECT credit FROM customer"
                    + " WHERE email = ?;");
            ps.setString(1, customerId);
            this.rs = ps.executeQuery();
            while(this.rs.next())
            {
                double cusCredits = this.rs.getDouble("credit");
                if(credit < cusCredits)
                {
                    cusCredits -= credit;
                    CustomerDAO dao = new CustomerDAO();
                    dao.updateCredits(customerId, cusCredits);
                    dao.closeDB();
                }
                else
                {
                    return "Error: Account Credits is not sufficient.";
                }
            }
            
            amount = amount - disAmount - credit;
            
            ps = conn.prepareStatement("INSERT INTO order_history"
                    + " (id, customer_id, amount, date_created, discount, credit)"
                    + " VALUES (?, ?, ?, ?, ?, ?);");
            String orderID = UUID.randomUUID().toString();
            ps.setString(1, orderID);
            ps.setString(2, customerId);
            ps.setDouble(3, amount);
            Date date = new Date();
            ps.setTimestamp(4, (new Timestamp(date.getTime())));
            ps.setDouble(5, disAmount);
            ps.setDouble(6, credit);
            
            rows = ps.executeUpdate();
            
            if(rows > 0)
            {
                OrderedProductDAO orderedProductDAO = new OrderedProductDAO();
                rows = orderedProductDAO.addOrder(orderID, order);
                orderedProductDAO.closeDB();
            }
            
            if (rows > 0)
            {
                return "success";
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failed to insert";
    }
}