/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.controller;

import com.jf.pojos.Comment;
import com.jf.pojos.Rating;
import com.jf.service.RatingService;
import com.jf.service.UserService;
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
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userDetailService;

    @PostMapping("/rating")
    public String addOrUpdate(Model model,
            @ModelAttribute(name = "rating") @Valid Rating rating,
            BindingResult result) {
        if (!result.hasErrors()) {
            if (ratingService.addOrUpdate(rating)) {
                return "redirect:/user?sender=" + rating.getSender() 
                        + "&receiver=" + rating.getReceiver();   
            }
        }
        model.addAttribute("errMsg", "Occured error. Please try again!");
        model.addAttribute("user", userDetailService.getUserDetail(rating.getSender(), rating.getReceiver()));
        model.addAttribute("comment", new Comment());
        model.addAttribute("rating", rating);
        return "user";
    }

}
