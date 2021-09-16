/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.pojos.Comment;
import com.jf.pojos.Rating;
import com.jf.pojos.User;
import com.jf.service.impl.CommentServiceImpl;
import com.jf.service.impl.UserServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author truongtn
 */
@Controller
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserServiceImpl userDetailService;
    
    
    @PostMapping("comment")
    public String sendComment(Model model, 
            @ModelAttribute(name = "comment") @Valid Comment comment,
            BindingResult result){
        
        if(!result.hasErrors()){
            if(commentService.add(comment)){
                return "redirect:/user?sender=" + comment.getSender() 
                        + "&receiver=" + comment.getReceiver();
            }
        }
        

        model.addAttribute("errMsg", "Comment fail! Please try again");
        model.addAttribute("user", userDetailService.getUserDetailByUsername(comment.getReceiver()));
        model.addAttribute("comment", comment);
        model.addAttribute("rating", new Rating());

        return "user";
    }
    
}
