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
    private Integer amount;
    private Timestamp dateCreated;
    private Customer customerId;
    public OrderHistory()
    {
        id = "";
        amount = 0;
    }

    public OrderHistory(String id)
    {
        this.id = id;
    }

    public OrderHistory(String id, Integer amount, Timestamp dateCreated, Customer customerId)
    {
        this.id = id;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.customerId = customerId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    public void setDateCreated(Timestamp dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Customer getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Customer customerId)
    {
        this.customerId = customerId;
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
