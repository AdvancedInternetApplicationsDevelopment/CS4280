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
public class Review
{
    private Customer customer;
    private Product product;
    private String comments;
    private String adminReply;
    private int star;

    public Review()
    {
        
    }
    
    public Review(Customer customer, Product product, String comments, String adminReply)
    {
        this.customer = customer;
        this.product = product;
        this.comments = comments;
        this.adminReply = adminReply;
    }
    public Review(Customer customer, Product product, String comments, String adminReply, int star)
    {
        this.customer = customer;
        this.product = product;
        this.comments = comments;
        this.adminReply = adminReply;
        this.star = star;
    }
    
    public Review(String customerID, String productId)
    {
        this.customer = new Customer(customerID);
        this.product = new Product(productId);
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getAdminReply()
    {
        return adminReply;
    }

    public void setAdminReply(String adminReply)
    {
        this.adminReply = adminReply;
    }

    public int getStar()
    {
        return star;
    }

    public void setStar(int star)
    {
        this.star = star;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (customer.getEmail() != null ? customer.getEmail().hashCode() : 0);
        hash += (product.getId() != null ? product.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review))
        {
            return false;
        }
        Review other = (Review) object;
        if ((this.customer.getEmail() == null && other.getCustomer().getEmail() != null)
                || (this.customer.getEmail() != null && !this.customer.getEmail().equals(other.getCustomer().getEmail())))
        {
            return false;
        }
        else if ((this.product.getId() == null && other.getProduct().getId() != null)
                || (this.product.getId() != null && !this.product.getId().equals(other.getProduct().getId())))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "model.Review[ customerId=" + customer.getEmail() + ", productId=" + product.getId() + " ]";
    }
    
}
