/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Ninad
 */
public class OrderHistory
{
    private String id;
    private Customer customerId;
    private double amount;
    private Timestamp dateCreated;
    private double discount;
    private double credit;
    private List<OrderedProduct> orderedProducts;
    
    public OrderHistory()
    {
        this.id = "";
        this.amount = 0;
        this.discount = 0;
        this.credit = 0;
    }

    public OrderHistory(String id)
    {
        this.id = id;
    }

    public OrderHistory(String id, Customer customerId, Integer amount,
            int discount, int credit)
    {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.discount = discount;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public Customer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Customer customerId)
    {
        this.customerId = customerId;
    }
    
    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public Timestamp getDateCreated()
    {
        return dateCreated;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(double discount)
    {
        this.discount = discount;
    }

    public void setDateCreated(Timestamp dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public double getCredit()
    {
        return credit;
    }

    public void setCredit(double credit)
    {
        this.credit = credit;
    }

    public List<OrderedProduct> getOrderedProducts()
    {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts)
    {
        this.orderedProducts = orderedProducts;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderHistory))
        {
            return false;
        }
        OrderHistory other = (OrderHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.OrderHistory[ id=" + id + " ]";
    }
    
}
