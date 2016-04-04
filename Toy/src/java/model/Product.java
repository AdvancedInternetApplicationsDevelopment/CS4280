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
public class Product
{
    private String id;
    private String name;
    private String modelNum;
    private String description;
    private boolean available;
    private double price;
    private String addInfo;
    private byte[] image;
    private Timestamp lastUpdate;
    private boolean new1;
    private boolean approved;
    private Category categoryId;
    
    public Product()
    {
        id = "";
        name = "";
        modelNum = "";
        description = "";
        available = false;
        price = 0.0;
        addInfo = "";
        new1 = false;
        approved = false;
    }

    public Product(String id)
    {
        this.id = id;
    }
    
    public Product(String id, String name, int price, Timestamp lastUpdate)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.lastUpdate = lastUpdate;
    }
    
    public Product(String id, String name, int price, String description,
            Timestamp lastUpdate, boolean new1, byte[] image, boolean approved, Category categoryId)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.new1 = new1;
        this.image = image;
        this.approved = approved;
        this.categoryId = categoryId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getModelNum()
    {
        return modelNum;
    }

    public void setModelNum(String modelNum)
    {
        this.modelNum = modelNum;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean available)
    {
        this.available = available;
    }
    
    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public String getAddInfo()
    {
        return addInfo;
    }

    public void setAddInfo(String addInfo)
    {
        this.addInfo = addInfo;
    }
    
    public byte[] getImage()
    {
        return image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }
    
    public Timestamp getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public boolean getNew1()
    {
        return new1;
    }

    public void setNew1(boolean new1)
    {
        this.new1 = new1;
    }
    
    public boolean getApproved()
    {
        return approved;
    }

    public void setApproved(boolean approved)
    {
        this.approved = approved;
    }

    public Category getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Category categoryId)
    {
        this.categoryId = categoryId;
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
        if (!(object instanceof Product))
        {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Product[ id=" + id + " ]";
    }
    
}
