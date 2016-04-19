/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;
import model.Order;
import model.OrderedProduct;

/**
 *
 * @author Ninad
 */

public interface OrderedProductDAO
{
    public List<OrderedProduct> getAll();
    
    public OrderedProduct getOrderHistoryFromID(String order_id, String product_id);
    
    public int getTotalQuantityFromProductID(String product_id);
    
    public int getTotalQuantityOfProductSold();
    
    public int addOrder(String orderID, List<Order> order);
    
    public List<OrderedProduct> getByFilter(String productId, String orderId);
    
    public String getQueryFromFilter(String productId, String orderId);
    
}
