/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.repository;

import com.jf.pojos.Rating;
import com.jf.pojos.User;

/**
 *
 * @author truongtn
 */
public interface RatingRepository {
    Rating rating(User sender, User receiver);
    boolean addOrUpdate(Rating rating);
}
