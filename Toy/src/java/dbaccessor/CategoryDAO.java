/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.Category;

/**
 *
 * @author Ninad
 */

public interface CategoryDAO
{
    public List<Category> getAll();
    
    public Category getCategoryFromID(int id);
    
    public int getCategoryIDFromName(String name);
    
    public boolean categoryExists(String category);
    
    public boolean addCategory(String category);
    
}