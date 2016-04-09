/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;

/**
 *
 * @author Ninad
 */
public class ProductImage
{
    private String id;
    private Blob image;

    public ProductImage(String id, Blob image)
    {
        this.id = id;
        this.image = image;
    }

    public ProductImage()
    {
        this.id = "";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Blob getImage()
    {
        return image;
    }

    public void setImage(Blob image)
    {
        this.image = image;
    }
}
