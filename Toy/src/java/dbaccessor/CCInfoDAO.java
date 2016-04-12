/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;

import model.CCInfo;

/**
 *
 * @author Ninad
 */
public interface CCInfoDAO
{
    public List<CCInfo> getAll();
    
    public CCInfo getCcInfoFromID(String email);
    
    public boolean addCcInfo(CCInfo ccinfo);
    
    public boolean updateCcInfo(CCInfo ccinfo);
}
