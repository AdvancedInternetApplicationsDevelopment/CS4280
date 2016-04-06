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
public class Discount
{
    private String discountCode;
    private int amount;

    public Discount()
    {
    }

    public Discount(String discountCode)
    {
        this.discountCode = discountCode;
    }

    public Discount(String discountCode, int amount)
    {
        this.discountCode = discountCode;
        this.amount = amount;
    }

    public String getDiscountCode()
    {
        return discountCode;
    }

    public void setDiscountCode(String discountCode)
    {
        this.discountCode = discountCode;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (discountCode != null ? discountCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount))
        {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.discountCode == null && other.discountCode != null) || (this.discountCode != null && !this.discountCode.equals(other.discountCode)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Discount[ discountCode=" + discountCode + " ]";
    }
    
}
