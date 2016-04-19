/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.Order;
import model.OrderHistory;

/**
 *
 * @author Ninad
 */

public interface OrderHistoryDAO
{
    public List<OrderHistory> getAll();
    
    public OrderHistory getOrderHistoryFromID(String id);
    
    public List<OrderHistory> getOrderHistoryFromCustomerID(String customer_id);
    
    public List<OrderHistory> getLatest();
    
    public List<OrderHistory> getByFilter(String customerId, String orderId);
    
    public String getQueryFromFilter(String customerId, String orderId);
    
    public String checkout(List<Order> order, String discountCode, double credit, String customerId);
    
}