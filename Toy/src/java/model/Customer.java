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
public class Customer
{
    private String email;
    private String name;
    private String phone;
    private String address;
    private String cityRegion;
    private String ccNumber;
    private Login login;
    
    public Customer()
    {
        email = "";
        name = "";
        phone = "";
        address = "";
        cityRegion = "";
        ccNumber = "";
    }

    public Customer(String email)
    {
        this.email = email;
    }

    public Customer(String email, String name, String phone, String address, String cityRegion, String ccNumber)
    {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.cityRegion = cityRegion;
        this.ccNumber = ccNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCityRegion()
    {
        return cityRegion;
    }

    public void setCityRegion(String cityRegion)
    {
        this.cityRegion = cityRegion;
    }

    public String getCcNumber()
    {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }

    public Login getLogin()
    {
        return login;
    }

    public void setLogin(Login login)
    {
        this.login = login;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer))
        {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Customer[ email=" + email + " ]";
    }
    
}
