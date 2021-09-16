/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.service.impl;

import com.jf.pojos.Comment;
import com.jf.pojos.User;
import com.jf.repository.impl.CommentRepositoryImpl;
import com.jf.repository.impl.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author truongtn
 */
@Service
public class CommentServiceImpl{
    @Autowired
    private CommentRepositoryImpl commentRepository;
    @Autowired
    private UserRepositoryImpl userRepository;
    
    public boolean add(Comment comment){
        User sender = userRepository.getUserDetailByUsername(comment.getSender());
        User receiver = userRepository.getUserDetailByUsername(comment.getReceiver());
        
        comment.setCandidate(sender);
        comment.setEmployer(receiver);
        
        return commentRepository.add(comment);
    }
    
}
