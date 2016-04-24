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
public class CCInfo
{
    private String ccNumber;
    private String ccHolder;
    private String ccEmail;
    private String expiryDate;
    private int ccv;
    
    public CCInfo()
    {
    }

    public CCInfo(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }

    public CCInfo(String ccNumber, String ccHolder, String ccEmail, String expiryDate, int ccv)
    {
        this.ccNumber = ccNumber;
        this.ccHolder = ccHolder;
        this.ccEmail = ccEmail;
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

    public String getCcHolder()
    {
        return ccHolder;
    }

    public void setCcHolder(String ccHolder)
    {
        this.ccHolder = ccHolder;
    }

    public String getCcEmail()
    {
        return ccEmail;
    }

    public void setCcEmail(String ccEmail)
    {
        this.ccEmail = ccEmail;
    }

    public String getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate)
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
