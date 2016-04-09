/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Ninad
 */
public class OrderHistory
{
    private String id;
    private Customer customerId;
    private Integer amount;
    private Timestamp dateCreated;
    private int discount;
    private int credit;
    
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
            Timestamp dateCreated, int discount, int credit)
    {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.discount = discount;
        this.credit = credit;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
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
    
    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public Timestamp getDateCreated()
    {
        return dateCreated;
    }

    public int getDiscount()
    {
        return discount;
    }

    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    public void setDateCreated(Timestamp dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public int getCredit()
    {
        return credit;
    }

    public void setCredit(int credit)
    {
        this.credit = credit;
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
