/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Ninad
 */
public class CCInfo
{
    private String ccNumber;
    private String ccName;
    private Date expiryDate;
    private int ccv;
    
    public CCInfo()
    {
    }

    public CCInfo(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }

    public CCInfo(String ccNumber, String ccName, Date expiryDate, int ccv)
    {
        this.ccNumber = ccNumber;
        this.ccName = ccName;
        this.expiryDate = expiryDate;
        this.ccv = ccv;
    }

    public String getCcNumber()
    {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }

    public String getCcName()
    {
        return ccName;
    }

    public void setCcName(String ccName)
    {
        this.ccName = ccName;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate)
    {
        this.expiryDate = expiryDate;
    }

    public int getCcv()
    {
        return ccv;
    }

    public void setCcv(int ccv)
    {
        this.ccv = ccv;
    }

    
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (ccNumber != null ? ccNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CCInfo))
        {
            return false;
        }
        CCInfo other = (CCInfo) object;
        if ((this.ccNumber == null && other.ccNumber != null) || (this.ccNumber != null && !this.ccNumber.equals(other.ccNumber)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.CCInfo[ ccNumber=" + ccNumber + " ]";
    }
    
}
