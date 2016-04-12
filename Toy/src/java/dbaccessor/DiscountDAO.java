/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.Discount;

/**
 *
 * @author Ninad
 */

public interface DiscountDAO
{
    public List<Discount> getAll();
    
    public Discount getDiscountFromID(String code);
    
    public boolean addDiscount(Discount discount);
    
    public boolean deleteDiscount(Discount discount);
    
}