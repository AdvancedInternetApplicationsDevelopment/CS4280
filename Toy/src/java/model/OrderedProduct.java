/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ninad
 */
public class OrderedProduct
{
    private OrderHistory orderHistory;
    private Product product;
    private int quantity;

    public OrderedProduct()
    {
    }

    public OrderedProduct(OrderHistory orderHistory, Product product, int quantity)
    {
        this.orderHistory = orderHistory;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderedProduct(String orderId, String productId)
    {
        this.orderHistory = new OrderHistory(orderId);
        this.product = new Product(productId);
    }
    
    public OrderHistory getOrderHistory()
    {
        return orderHistory;
    }

    public void setOrderHistory(OrderHistory orderHistory)
    {
        this.orderHistory = orderHistory;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }
    
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (orderHistory.getId() != null ? orderHistory.getId().hashCode() : 0);
        hash += (product.getId() != null ? product.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedProduct))
        {
            return false;
        }
        OrderedProduct other = (OrderedProduct) object;
        if ((this.orderHistory.getId() == null && other.getOrderHistory().getId() != null)
                || (this.orderHistory.getId() != null && !this.orderHistory.getId().equals(other.getOrderHistory().getId())))
        {
            return false;
        }
        else if ((this.product.getId() == null && other.getProduct().getId() != null)
                || (this.product.getId() != null && !this.product.getId().equals(other.getProduct().getId())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.OrderedProduct[ orderId=" + orderHistory.getId() + ", productId=" + product.getId() + " ]";
    }
    
}
