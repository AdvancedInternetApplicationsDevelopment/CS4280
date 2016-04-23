/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccessor;

import java.util.*;
import model.Review;

/**
 *
 * @author Ninad
 */

public interface ReviewDAO
{
    public List<Review> getAll();
    
    public Review getReviewFromID(String customer_id, String product_id);
    
    public int getAvgStarFromProductID(String product_id);
    
    public boolean addReview(Review review);
    
    public boolean addAdminReply(Review review);
    
    public boolean deleteReview(Review review);

    public List<Review> getReviewFromProductID(String productId);
    
}
