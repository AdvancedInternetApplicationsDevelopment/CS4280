/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.Login;

/**
 *
 * @author Ninad
 */

public interface LoginDAO
{
    public List<Login> getAll();
    
    public Login getLoginFromID(String idlogin);
    
    public boolean addLogin(Login login);
    
    public boolean updatePass(String idLogin, String password);
    
}