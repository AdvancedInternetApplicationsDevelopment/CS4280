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
    private Category categoryId;
    private int quantity;
    private boolean available;
    private double price;
    private String brand;
    private String description;
    private String addInfo;
    private Timestamp lastUpdate;
    private boolean new1;
    private boolean approved;
    private String owner;
    
    public Product()
    {
        id = null;
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

    public Product(String id, String name, String modelNum, Category categoryId,
            int quantity, boolean available, double price, String brand,
            String description, String addInfo, Timestamp lastUpdate, boolean new1,
            boolean approved, String owner)
    {
        this.id = id;
        this.name = name;
        this.modelNum = modelNum;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.available = available;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.addInfo = addInfo;
        this.lastUpdate = lastUpdate;
        this.new1 = new1;
        this.approved = approved;
        this.owner = owner;
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

    public Category getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Category categoryId)
    {
        this.categoryId = categoryId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
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

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAddInfo()
    {
        return addInfo;
    }

    public void setAddInfo(String addInfo)
    {
        this.addInfo = addInfo;
    }

    public Timestamp getLastUpdate()
    {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }

    public boolean isNew1()
    {
        return new1;
    }

    public void setNew1(boolean new1)
    {
        this.new1 = new1;
    }

    public boolean isApproved()
    {
        return approved;
    }

    public void setApproved(boolean approved)
    {
        this.approved = approved;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
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
