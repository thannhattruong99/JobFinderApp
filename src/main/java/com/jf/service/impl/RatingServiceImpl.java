/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.Rating;
import com.jf.pojos.User;
import com.jf.repository.RatingRepository;
import com.jf.repository.UserRepository;
import com.jf.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addOrUpdate(Rating rating) {
        User senderResult = userRepository.getUserDetailByUsername(rating.getSender());
        User receiverResult = userRepository.getUserDetailByUsername(rating.getReceiver());

        rating.setCandidate(senderResult);
        rating.setEmployer(receiverResult);

        
        if(ratingRepository.rating(senderResult, receiverResult) != null){
            rating.setId(ratingRepository.rating(senderResult, receiverResult).getId());
        }
        
        return ratingRepository.addOrUpdate(rating);

    }
}
