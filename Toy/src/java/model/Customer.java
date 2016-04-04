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
    private String fname;
    private String lname;
    private String phone;
    private String fax;
    private String address1;
    private String address2;
    private String city;
    private String postal;
    private String country;
    private String region;
    private String ccNumber;
    private CCInfo ccInfo;
    private Login login;
    
    public Customer()
    {
        this.email = "";
        this.fname = "";
        this.lname = "";
        this.phone = "";
        this.fax = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.postal = "";
        this.country = "";
        this.region = "";
    }

    public Customer(String email)
    {
        this.email = email;
    }
    
    public Customer(String email, String fname, String lname, String phone,
            String fax, String address1, String address2, String city, String postal,
            String country, String region, String ccNumber)
    {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.fax = fax;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postal = postal;
        this.country = country;
        this.region = region;
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

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostal()
    {
        return postal;
    }

    public void setPostal(String postal)
    {
        this.postal = postal;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getCcNumber()
    {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber)
    {
        this.ccNumber = ccNumber;
    }
    
    public CCInfo getCcInfo()
    {
        return ccInfo;
    }

    public void setCcInfo(CCInfo ccInfo)
    {
        this.ccInfo = ccInfo;
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
