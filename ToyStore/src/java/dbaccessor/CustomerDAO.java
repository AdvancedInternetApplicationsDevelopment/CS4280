/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.CCInfo;
import model.Customer;

/**
 *
 * @author Ninad
 */

public interface CustomerDAO
{
    public List<Customer> getAll();
    
    public Customer getCustomerFromID(String email);
    
    public int getNoOfUsers();
    
    public List<Customer> getByFilter(String email, String fname, String lname,
            String phone, String fax, String city, String postal, String country,
            String region);
    
    public String getQueryFromFilter(String email, String fname, String lname,
            String phone, String fax, String city, String postal, String country,
            String region);
    
    public boolean addCustomer(Customer customer, CCInfo ccinfo);
    
    public boolean updateCustomer(Customer customer);
    
    public double getCredits(String email);
    
    public boolean updateCredits(String email, double credit);
    
}