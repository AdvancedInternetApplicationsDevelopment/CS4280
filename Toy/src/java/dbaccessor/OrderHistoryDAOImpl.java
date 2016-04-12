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

public class OrderHistoryDAOImpl implements OrderHistoryDAO
{
    DataSource ds;
    Connection conn;
    ResultSet rs;
    
    public OrderHistoryDAOImpl()
    {
        try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            ds = (DataSource)envCtx.lookup("jdbc/toy");
        }
        catch (NamingException ex)
        {
            Logger.getLogger(OrderHistoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<OrderHistory> getAll()
    {
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM order_history;").executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAOImpl();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                ret.add(order);
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
            Logger.getLogger(OrderHistoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public OrderHistory getOrderHistoryFromID(String id)
    {
        OrderHistory ret = null;
        try
        {
            this.conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM order_history"
                    + " WHERE id = ?;");
            ps.setString(1, id);
            this.rs = ps.executeQuery();
            if(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAOImpl();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                ret = order;
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
            Logger.getLogger(OrderHistoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<OrderHistory> getLatest()
    {
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement("SELECT * FROM order_history"
                    + " ORDER BY date_created DESC LIMIT 8;").executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAOImpl();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                ret.add(order);
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
            Logger.getLogger(OrderHistoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public List<OrderHistory> getByFilter(String customerId, String orderId)
    {
        String query = getQueryFromFilter(customerId, orderId);
        
        List<OrderHistory> ret = new ArrayList<OrderHistory>();
        try
        {
            this.conn = ds.getConnection();
            this.rs = conn.prepareStatement(query).executeQuery();
            while(this.rs.next())
            {
                OrderHistory order = new OrderHistory();
                order.setId(this.rs.getString("id"));
                CustomerDAO dao = new CustomerDAOImpl();
                order.setCustomerId(dao.getCustomerFromID(this.rs.getString("customer_id")));
                order.setAmount(this.rs.getDouble("amount"));
                order.setDateCreated(this.rs.getTimestamp("date_created"));
                order.setDiscount(this.rs.getDouble("discount"));
                order.setCredit(this.rs.getDouble("credit"));
                
                ret.add(order);
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
            Logger.getLogger(OrderHistoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public String getQueryFromFilter(String customerId, String orderId)
    {
        List<String> query = new ArrayList<String>();
        query.add("SELECT * FROM order_history");
        query.add(" WHERE");
        
        if(!customerId.equalsIgnoreCase(""))
        {
            query.add(" customer_id LIKE '%" + customerId + "%'");
        }
        if(!orderId.equalsIgnoreCase(""))
        {
            query.add(" id LIKE '%" + orderId + "%'");
        }
        
        query.add(";");
        
        String ret = "";
        
        ret += query.get(0) + query.get(1);
        
        for(int i = 2; i <= (query.size() - 2); i++)
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
    
    public synchronized String checkout(List<Order> order, String discountCode,
            double credit, String customerId)
    {
        int rows;
        try
        {
            this.conn = ds.getConnection();
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
            ProductDAO productDAO = new ProductDAOImpl();
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
            
            //Check is account credits are enough for this transaction
            CustomerDAO customerDAO = new CustomerDAOImpl();
            double cusCredits = customerDAO.getCredits(customerId);
            if(credit > cusCredits)
            {
                cusCredits -= credit;
                customerDAO.updateCredits(customerId, cusCredits);
            }
            else
            {
                return "Error: Account Credits is not sufficient.";
            }

            //Add credits to owner if the product is recycled
            for(Order ordered: order)
            {
                String productId = ordered.getProductID();
                if(!productDAO.checkIfNew(productId))
                {
                    String owner = productDAO.getProductFromID(productId).getOwner();
                    double price = productDAO.getPriceById(productId);
                    customerDAO.updateCredits(owner, (customerDAO.getCredits(owner) + price));
                }
            }
            
            //Process transaction
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
                OrderedProductDAO orderedProductDAO = new OrderedProductDAOImpl();
                rows = orderedProductDAO.addOrder(orderID, order);
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
            
            if (rows > 0)
            {
                return "success";
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(OrderHistoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "failed to insert";
    }
}