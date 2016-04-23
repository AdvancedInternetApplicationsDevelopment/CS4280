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
public class Login
{
    private String idlogin;
    private String idpass;

    public Login()
    {
        idlogin = "";
        idpass = "";
    }

    public Login(String idlogin)
    {
        this.idlogin = idlogin;
    }

    public Login(String idlogin, String idpass)
    {
        this.idlogin = idlogin;
        this.idpass = idpass;
    }

    public String getIdlogin()
    {
        return idlogin;
    }

    public void setIdlogin(String idlogin)
    {
        this.idlogin = idlogin;
    }

    public String getIdpass()
    {
        return idpass;
    }

    public void setIdpass(String idpass)
    {
        this.idpass = idpass;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idlogin != null ? idlogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login))
        {
            return false;
        }
        Login other = (Login) object;
        if ((this.idlogin == null && other.idlogin != null) || (this.idlogin != null && !this.idlogin.equals(other.idlogin)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Login[ idlogin=" + idlogin + " ]";
    }
    
}
